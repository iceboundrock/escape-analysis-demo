package bof;

public class Demo6 extends Worker {
    private static SmallObject<Integer> createFoo(int i) {
        return new SmallObject<>(i);
    }

    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            SmallObject<Integer> fi = createFoo(i);
            SmallObject<SmallObject<Integer>> ff = new SmallObject<>(fi);
        }
    }
}