package bof;

public class Demo4 extends Worker {
    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            String s = ((Integer)i).toString();
            SmallObject f = new SmallObject<>(s);
        }
    }
}