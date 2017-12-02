package mago.testSupport.model;

public class SystemConfig {
    private LogConfig log;
    private ThreadPoolConfig threadPool;

    public ThreadPoolConfig threadPoolConfig() {
        return threadPool;
    }

    public LogConfig logConfig() {
        return log;
    }

    public static class ThreadPoolConfig {
        int size;
        public int size() { return size; }
    }

    public static class LogConfig {
        String name;
        long rollSize;
        public String name() { return name; }
        public long rollSize() { return rollSize; }
    }
}
