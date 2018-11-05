package cn.guard.dwarf.register.conf;

import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.List;

public class DwarfConf {

    private GeneralConf generalConf;

    private List<ClientConf> clientConfs;

    public DwarfConf() {
    }

    public GeneralConf getGeneralConf() {
        return this.generalConf;
    }

    public void setGeneralConf(GeneralConf generalConf) {
        this.generalConf = generalConf;
    }

    public void setDefaultClientGroup() {

        if (!CollectionUtils.isEmpty(this.clientConfs)) {
            Iterator var1 = this.clientConfs.iterator();

            while(var1.hasNext()) {
                ClientConf clientConf = (ClientConf)var1.next();
                if (clientConf.getOrganization() == null) {
                    clientConf.setOrganization(getGeneralConf().getOrganization());
                }
            }

        }
    }

    public void setClientConfs(List<ClientConf> clientConfs) {
        this.clientConfs = clientConfs;
    }

    public List<ClientConf> getClientConfs() {
        return this.clientConfs;
    }
}
