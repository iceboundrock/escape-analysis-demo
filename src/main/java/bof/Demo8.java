package bof;

public class Demo8 extends Worker {
    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            BigObject f = new BigObject<>(new Object());
        }
    }
}