#! /bin/bash

if [[ ! "$(javac -version 2>&1)" =~ "1.8" ]]; then
  echo "MultiMarioBros works with JavaFX libraries included in Java 8. Previous versions of Java are not supported but could work by including the proper jfxrt.jar into the CLASSPATH."
    exit -1
else
  classpath=$CLASSPATH
fi

mkdir -p bin
javac -cp "$classpath" -sourcepath src -d bin src/org/supermario/Main.java
cp -r src/img bin/img
java -cp "$classpath:bin" org.supermario.Main
