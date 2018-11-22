package cn.guard.dwarf.rpc.resolver;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DwarfNameResolverProvider extends NameResolverProvider {
    private List<Map<String, Object>> initAddress = new ArrayList();
    private List<EquivalentAddressGroup> equivalentAddressGroups;
    private int lbStrategy;
    private DwarfNameResolver instance = new DwarfNameResolver();

    protected boolean isAvailable() {
        return true;
    }

    protected int priority() {
        return 0;
    }

    public DwarfNameResolverProvider(int lbStrategy, List<Map<String, Object>> initAddress) {
        this.lbStrategy = lbStrategy;
        this.initAddress = initAddress;
        this.equivalentAddressGroups = this.transInitAddresses();
    }

    @Nullable
    public NameResolver newNameResolver(URI targetUri, Attributes params) {
        this.instance.init(this.lbStrategy, this.equivalentAddressGroups);
        return this.instance;
    }

    public void refreshServers(int lbStrategy, List<Map<String, Object>> servers) {
        this.initAddress = servers;
        this.equivalentAddressGroups = this.transInitAddresses();
        this.instance.refreshWithServers(lbStrategy, this.equivalentAddressGroups);
    }

    private List<EquivalentAddressGroup> transInitAddresses(){
        return null;
    }

    public List<EquivalentAddressGroup> getEquivalentAddressGroups() {
        return this.equivalentAddressGroups;
    }

    public String getDefaultScheme() {
        return null;
    }
}
