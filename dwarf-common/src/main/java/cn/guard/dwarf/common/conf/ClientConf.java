package cn.guard.dwarf.common.conf;

import java.util.List;

public class ClientConf {

    private String organization;

    private String clientName;

    private int threadPoolSize = 8;

    private List<Class<?>> interfaces;

    private String group;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public List<Class<?>> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Class<?>> interfaces) {
        this.interfaces = interfaces;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
