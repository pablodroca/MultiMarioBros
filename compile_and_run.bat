if not exist bin mkdir bin
javac -sourcepath src -d bin src\org\supermario\Main.java
xcopy src\img bin\img\ /s/y
java -cp "%CLASSPATH%;bin" org.supermario.Main
