package bof;

public class Demo9 extends Worker {
    static class FooFactory {
        // prevent createFoo to be inline, compare to demo 6
        SmallObject<Integer> createFoo(int i) {
            return new SmallObject<>(i);
        }
    }

    private final FooFactory fooFactory = new FooFactory();

    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            SmallObject<Integer> fi = fooFactory.createFoo(i);
            SmallObject<SmallObject<Integer>> ff = new SmallObject<>(fi);
        }
    }
}