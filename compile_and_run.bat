if not exist target mkdir target
if not exist target\classes mkdir target\classes

javac -sourcepath src\main\java -d target\classes src\main\java\org\supermario\Main.java
xcopy src\main\resources\img target\classes\img\ /s/y
java -cp "%CLASSPATH%;target\classes" org.supermario.Main
