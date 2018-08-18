package bof;

public class Main {
    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        final Runnable demo = (Runnable) Class.forName("bof.Demo" + args[0]).newInstance();
        final Thread t = new Thread(null, demo, "demo", 1024);
        t.start();
        t.join();
    }
}
