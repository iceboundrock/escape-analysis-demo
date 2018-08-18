package bof;

public class Demo1 extends Worker {
    private static class Foo {
        final SmallObject smallObject;
        Foo(int i) {
            smallObject = new SmallObject<>(i);
        }
    }

    @Override protected void doWork() {
        for (int i = 0; i < 1000 * 1000 * 10; i++) {
            Foo f = new Foo(i);
        }
    }
}