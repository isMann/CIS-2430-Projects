package dungeon;

import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String [] args) {

        System.out.println("*****Testing Chamber*****");
		Result result = JUnitCore.runClasses(ChamberTest.class);
        System.out.println("\n*****Failed Test Report****\n");
        List<Failure> failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount() + "\n");

        System.out.println("*****Testing Door*****");
        result = JUnitCore.runClasses(DoorTest.class);
        System.out.println("\n*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount() + "\n");

        System.out.println("*****Testing PassageSection*****");
        result = JUnitCore.runClasses(PassageSectionTest.class);
        System.out.println("\n*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount() + "\n");

        System.out.println("*****Testing Passage*****");
        result = JUnitCore.runClasses(PassageTest.class);
        System.out.println("\n*****Failed Test Report****\n");
        failedList = result.getFailures();
        failedList.forEach(f -> {
            System.out.println(f);
        });
        System.out.println("Number of Failed Tests = " + result.getFailureCount() + "\n");
        /*repeat steps the above for each junit test file you have*/
	}
}