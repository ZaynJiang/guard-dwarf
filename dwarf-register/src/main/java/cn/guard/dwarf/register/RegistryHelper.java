package cn.guard.dwarf.register;

import cn.guard.dwarf.common.conf.ClientConf;

import java.util.List;
import java.util.Map;

public class RegistryHelper {

    private static String registCenterUrl = null;

    public RegistryHelper() {
    }

    public static void init(String url) {
        registCenterUrl = url;
    }



    public static void startServerInterfacesWatcher(String serviceName, String group) {

    }

    public static void addClientWatcher(ClientConf clientConf) {
    }

    public static List<Map<String, Object>> getAvailableServiceMaps(String clientName) {
        return  null;
    }

    public static int getLbStrategy(String serviceName) {
        return 0;
    }
}
