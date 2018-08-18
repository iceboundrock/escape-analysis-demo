package bof;

public class Demo3 extends Worker {
    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            SmallObject f = new SmallObject<>(String.valueOf(i));
        }
    }
}