package cn.guard.dwarf.register.conf;

import java.util.Iterator;
import java.util.List;

public class DwarfConf {

    private GeneralConf generalConf;

    public DwarfConf() {
    }

    public GeneralConf getGeneralConf() {
        return this.generalConf;
    }

    public void setCommonConf(GeneralConf generalConf) {
        this.generalConf = generalConf;
    }
}
