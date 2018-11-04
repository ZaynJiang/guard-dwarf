package cn.guard.dwarf.register;

import cn.guard.dwarf.register.conf.DwarfConf;
import com.google.common.base.Preconditions;

public class Container {

    /**
     * 启动容器
     * @param args 启动参数
     */
    public static void start(String[] args) {
        DwarfConf dwarfConf = getDwarfConfig(args);
        Preconditions.checkNotNull(dwarfConf, "getConfiguration error, dwarfConf is null");
        Preconditions.checkNotNull(dwarfConf.getGeneralConf(), "commonConf should not be null");
    }

    public static DwarfConf getDwarfConfig(String[] args) {
        return null;
    }

}
