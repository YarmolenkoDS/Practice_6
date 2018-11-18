javac -classpath lib/junit-4.10.jar -d classes ^
    -sourcepath sources sources/ua/edu/sumdu/ta/yarmolenko/pr6/LinkedTaskList.java ^
        sources/ua/edu/sumdu/ta/yarmolenko/pr6/ArrayTaskList.java ^
        sources/ua/edu/sumdu/ta/yarmolenko/pr6/tests/AllTests.java
pause
cd ./build
jar cf program.jar -C ../classes .
pause
cd ../
java -classpath build/program.jar;lib/junit-4.10.jar ^
	org.junit.runner.JUnitCore ^
	ua.edu.sumdu.ta.yarmolenko.pr6.tests.AllTests
pause