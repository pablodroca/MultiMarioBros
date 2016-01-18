#! /bin/bash
#if [ -z "$JAVA_HOME" ] && [[ "$CLASSPATH" ~= "jfxrt.jar" ]]; then

if [[ ! "$(javac -version 2>&1)" =~ "1.8" ]]; then
  if [ -z "$JAVA_HOME" ] && [[ ! "$CLASSPATH" =~ "jfxrt.jar" ]]; then
    echo "Please define either a valid JAVA_HOME or a CLASSPATH with jfxrt.jar to ensure the JavaFX components can work at JDK <1.8"
    exit -1
  fi
  if [ -z "$JAVA_HOME" ]; then
    classpath=$CLASSPATH
  else
    classpath="$JAVA_HOME/lib/jre/lib/jfxrt.jar"
  fi
fi

mkdir -p bin
javac -cp "$classpath" -sourcepath src -d bin src/org/supermario/Main.java
cp -r src/img bin/img
java -cp "$classpath:bin" org.supermario.Main
