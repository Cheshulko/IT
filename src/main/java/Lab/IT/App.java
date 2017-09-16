package Lab.IT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
//@ComponentScan({"web"})
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		UserFriendlyConsoleInterface.start();
	}
//	public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }
}
