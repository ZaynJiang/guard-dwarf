package cn.guard.dwarf.register.conf;

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
