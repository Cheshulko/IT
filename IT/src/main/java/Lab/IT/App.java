package Lab.IT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import service.DbService;
import service.IDbService;

//import service.DbService;

/**
 * Hello world!
 *
 */
//
//@ComponentScan({ "web" })
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		UserFriendlyConsoleInterface.start();
	}

//	@Bean(name = "/dbservice")
//	HttpInvokerServiceExporter accountService() {
//		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
//		exporter.setService(new DbService());
//		exporter.setServiceInterface(IDbService.class);
//		return exporter;
//	}

//	public static void main(String[] args) {
//		SpringApplication.run(App.class, args);
//	}
}
