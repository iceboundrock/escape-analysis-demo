package bof;

abstract class Worker implements Runnable {
    @Override
    public final void run() {
        final long begin = System.nanoTime();
        doWork();
        final long end = System.nanoTime();
        System.out.printf("used %dns to run.\n", end - begin);
    }

    protected abstract void doWork();
}
