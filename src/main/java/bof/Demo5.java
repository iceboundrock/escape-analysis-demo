package bof;

public class Demo5 extends Worker {
    private static SmallObject<Integer> createFoo(int i) {
        return new SmallObject<>(i);
    }

    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            SmallObject<SmallObject<Integer>> ff = new SmallObject<>(createFoo(i));
        }
    }
}