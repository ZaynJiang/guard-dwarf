package cn.guard.dwarf.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    public static final String NAME_THREAD_PREFIX = "dwarf-pool-";
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private ThreadGroup group;
    private final AtomicInteger threadNumber;
    private String namePrefix;
    private int priority;

    public NamedThreadFactory(String name) {
        this(name, 0);
    }

    public NamedThreadFactory(String name, int priority) {
        this.threadNumber = new AtomicInteger(1);
        SecurityManager s = System.getSecurityManager();
        this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = "dwarf-pool-" + poolNumber.getAndIncrement() + "-" + name + "-thread-";
        this.priority = priority;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }

        if (this.priority < 1) {
            t.setPriority(5);
        } else {
            t.setPriority(this.priority);
        }

        return t;
    }
}
