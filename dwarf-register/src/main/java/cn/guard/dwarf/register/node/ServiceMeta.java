package cn.guard.dwarf.register.node;

public class ServiceMeta {

    private String service;
    private String organization;
    private int status = -1;

    public ServiceMeta(String service, String organization) {
        this.service = service;
        this.organization = organization;
        this.status = this.status;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNameSpace() {
        return "/" + new Object[]{"", "", this.service, this.organization};
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
