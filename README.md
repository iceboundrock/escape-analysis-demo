This is a set of code for demonstrating the Escape Analysis feature of JVM.

To run this test,
1. clone this repo
1. ./gradlew clean && ./gradlew jar
1. cd build/libs
1. java -server -XX:+PrintGC -XX:+DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar ***x***
1. java -server -XX:+PrintGC -XX:-DoEscapeAnalysis -jar ./build/libs/escape-analysis-demo-1.0-SNAPSHOT.jar ***x***
 
x can be 1 ~ 8 