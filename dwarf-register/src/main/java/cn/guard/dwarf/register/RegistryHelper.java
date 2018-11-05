package cn.guard.dwarf.register;

public class RegistryHelper {

    private static String registCenterUrl = null;

    public RegistryHelper() {
    }

    public static void init(String url) {
        registCenterUrl = url;
    }

}
