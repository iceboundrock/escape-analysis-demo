package bof;

public class Main {

    /**
     * To run this test,
     * 1. ./gradlew clean && ./gradlew jar
     * 2. cd build/libs
     * 3. java -server -XX:+PrintGC -XX:+DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar x
     * 4. java -server -XX:+PrintGC -XX:-DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar x
     *
     * x: 1 ~ 8
     */

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        final Runnable demo = (Runnable) Class.forName("bof.Demo" + args[0]).newInstance();
        final Thread t = new Thread(null, demo, "demo", 1024);
        t.start();
        t.join();
    }
}
