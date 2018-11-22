package cn.guard.dwarf.register;

import cn.guard.dwarf.common.conf.ClientConf;
import cn.guard.dwarf.common.conf.DwarfConf;
import cn.guard.dwarf.common.exception.DwarfContainerStartException;
import cn.guard.dwarf.common.exception.RpcException;
import cn.guard.dwarf.common.thread.ThreadPools;
import cn.guard.dwarf.common.utils.JsonUtils;
import cn.guard.dwarf.rpc.resolver.DwarfNameResolverProvider;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

public class Container {

    private static volatile boolean containerState = false;


    /**
     * 启动容器, market版本
     * @param args 启动参数
     */
    public static void start(String[] args) {
        DwarfConf dwarfConf = getDwarfConfig(args);
        Preconditions.checkNotNull(dwarfConf, "getConfiguration error, dwarfConf is null");
        Preconditions.checkNotNull(dwarfConf.getGeneralConf(), "generalConf should not be null");
        startContainer(dwarfConf);
    }


    public static void startContainer(DwarfConf dwarfConf) {
        checkContainerStatus();
        try {
            RegistryHelper.init(dwarfConf.getGeneralConf().getRegisterUrl());
            initClients(dwarfConf);
        } catch (Throwable e) {
            throw new RpcException(e);
        }
    }

    public static void initClients(DwarfConf dwarfConf) throws Exception {
        List<ClientConf> clientConfList = dwarfConf.getClientConfs();
        if (clientConfList != null && !clientConfList.isEmpty()) {
            Iterator var2 = clientConfList.iterator();
            while(var2.hasNext()) {
                ClientConf clientConf = (ClientConf)var2.next();
                String serviceName = clientConf.getClientName();
                ThreadPools.registerPool(serviceName, clientConf.getThreadPoolSize());

                if (CollectionUtils.isEmpty(clientConf.getInterfaces())) {
                    RegistryHelper.startServerInterfacesWatcher(serviceName, clientConf.getGroup());
                }

                RegistryHelper.addClientWatcher(clientConf);
                DwarfNameResolverProvider dwarfNameResolverProvider = new DwarfNameResolverProvider(RegistryHelper.getLbStrategy(serviceName), RegistryHelper.getAvailableServiceMaps(clientConf.getClientName()));
            }
        }
    }

    private static void checkContainerStatus() {
        if (containerState) {
            throw new DwarfContainerStartException("dwarf container already running");
        } else {
            containerState = true;
        }
    }


    public static DwarfConf getDwarfConfig(String[] args) {
        try {
            String dwarfConfigurationFile = args != null && args.length > 0 ? args[0] : "conf/dwarf.json";
            DwarfConf dwarfConf = getConfiguration(dwarfConfigurationFile);
            dwarfConf.setDefaultClientGroup();
            return dwarfConf;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static DwarfConf getConfiguration(String dwarfConfigFileName) {
        File configFile = new File(dwarfConfigFileName);
        if (!configFile.exists()) {
            throw new RpcException("configFile: " + dwarfConfigFileName + "is not exists");
        } else {
            try {
                String fileContent = new String(Files.readAllBytes(configFile.toPath()));
                return (DwarfConf) JsonUtils.parseObject(fileContent, DwarfConf.class);
            } catch (Throwable throwable) {
                throw new RpcException("read configFile:" + dwarfConfigFileName + " error!", throwable);
            }
        }
    }

}
