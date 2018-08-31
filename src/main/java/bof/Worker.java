package bof;

abstract class Worker implements Runnable {
    @Override
    public final void run() {
        final long begin = System.currentTimeMillis();
        doWork();
        final long end = System.currentTimeMillis();
        System.out.printf("used %dms to run.\n", end - begin);
    }

    protected abstract void doWork();
}
