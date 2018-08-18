package bof;

public class Demo7 extends Worker {
    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            BigObject f = new BigObject<Integer>(i);
        }
    }
}
