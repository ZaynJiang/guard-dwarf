package cn.guard.dwarf.common.thread;

import cn.guard.dwarf.common.exception.ThreadPoolException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadPools {

    private static final int PROCESSEROR_SIZE = Runtime.getRuntime().availableProcessors();
    private static Map<String, ExecutorService> poolsManger = new HashMap();

    public ThreadPools() {
    }

    public static ExecutorService createFixedPool(String name) {
        return new ThreadPoolExecutor(PROCESSEROR_SIZE, PROCESSEROR_SIZE, 15L, TimeUnit.MINUTES, new ArrayBlockingQueue(1024), new NamedThreadFactory(name));
    }

    public static ExecutorService createServerPool(int size, String serviceName) {
        return Executors.newFixedThreadPool(size, new NamedThreadFactory(serviceName));
    }

    public static ExecutorService createChannelPool() {
        return Executors.newFixedThreadPool(32, new NamedThreadFactory("grpcChannel"));
    }

    public static void registerPool(String serviceName, int size) throws ThreadPoolException {
        registerPool(serviceName, size, size);
    }

    private static void registerPool(String serviceName, int min, int max) throws ThreadPoolException {
        String service = serviceName.toLowerCase();
        if (poolsManger.containsKey(service)) {
            throw new ThreadPoolException("Duplicate service names!!");
        } else {
            ExecutorService temp = new ThreadPoolExecutor(min, max, 15L, TimeUnit.MINUTES, new ArrayBlockingQueue(128), new NamedThreadFactory(service), new ThreadPoolExecutor.AbortPolicy());
            poolsManger.put(service, temp);
        }
    }

    public static ExecutorService getWorkerPool(String serviceName) throws ThreadPoolException {
        if (serviceName == null) {
            throw new ThreadPoolException("invalid service ");
        } else {
            String service = serviceName.toLowerCase();
            return (ExecutorService)poolsManger.get(service);
        }
    }

    public static void shutdown() {
        Iterator var0 = poolsManger.values().iterator();

        while(var0.hasNext()) {
            ExecutorService service = (ExecutorService)var0.next();
            service.shutdown();
        }

    }
}
