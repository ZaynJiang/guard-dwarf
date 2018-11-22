package cn.guard.dwarf.rpc.resolver;

import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

import java.util.ArrayList;
import java.util.List;

public class DwarfNameResolver extends NameResolver {
    private List<EquivalentAddressGroup> servers = new ArrayList();
    private NameResolver.Listener listener;
    private int lbStrategy;

    DwarfNameResolver() {
    }

    void init(int lbStrategy, List<EquivalentAddressGroup> initServices) {
        this.lbStrategy = lbStrategy;
        this.servers = initServices;
    }

    public String getServiceAuthority() {
        return "";
    }

    public void start(NameResolver.Listener listener) {
        this.listener = listener;
        this.resolve(this.lbStrategy);
    }

    public void shutdown() {
    }

    private void resolve(int lbStrategy) {}

    public void refresh() {
        super.refresh();
        this.resolve(this.lbStrategy);
    }

    public void refreshWithServers(int lbStrategy, List<EquivalentAddressGroup> servers) {
        this.servers = servers;
        this.lbStrategy = lbStrategy;
        this.resolve(lbStrategy);
    }
}
