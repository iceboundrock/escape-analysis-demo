This is a set of code for demonstrating the Escape Analysis feature of JVM.

To run this test,
1. clone this repo
1. ./gradlew clean && ./gradlew jar
1. java -server -XX:+PrintGC -XX:+DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar ***x***
1. java -server -XX:+PrintGC -XX:-DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar ***x***
 
x can be 1 ~ 8 

to change the ThreadStackSize, use -XX:ThreadStackSize=***ss***, the unit is KB

Some reference blog posts and documents:
* Escape Analysis in [Official Java 7 docs](https://docs.oracle.com/javase/7/docs/technotes/guides/vm/performance-enhancements-7.html#escapeAnalysis)
* [Escape Analysis in Java](https://www.beyondjava.net/escape-analysis-java) from beyondjava.net
* [Scalar Replacement](https://shipilev.net/jvm-anatomy-park/18-scalar-replacement/) from JVM Anatomy Park series on shipilev.net
