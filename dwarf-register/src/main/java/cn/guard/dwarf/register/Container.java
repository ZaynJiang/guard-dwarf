package cn.guard.dwarf.register;

import cn.guard.dwarf.common.exception.RpcException;
import cn.guard.dwarf.common.utils.JsonUtils;
import cn.guard.dwarf.register.conf.DwarfConf;
import com.google.common.base.Preconditions;

import java.io.File;
import java.nio.file.Files;

public class Container {

    /**
     * 启动容器
     * @param args 启动参数
     */
    public static void start(String[] args) {
        DwarfConf dwarfConf = getDwarfConfig(args);
        Preconditions.checkNotNull(dwarfConf, "getConfiguration error, dwarfConf is null");
        Preconditions.checkNotNull(dwarfConf.getGeneralConf(), "generalConf should not be null");
        startContainer(dwarfConf);
    }


    public static void startContainer(DwarfConf dwarfConf) {
        try {
            RegistryHelper.init(dwarfConf.getGeneralConf().getRegisterUrl());
        } catch (Throwable e) {
            throw new RpcException(e);
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
