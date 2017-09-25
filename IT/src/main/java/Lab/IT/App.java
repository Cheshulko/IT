package Lab.IT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import db.table.Storage;
import server.service.DbService;
import server.service.TableService;
import service.IDbService;
import service.ITableService;

//import service.DbService;

/**
 * Hello world!
 *
 */
//
@ComponentScan({ "server" })
@SpringBootApplication
public class App {
	// public static void main(String[] args) {
	// System.out.println("Hello World!");
	// UserFriendlyConsoleInterface.start();
	// }

	@Autowired
	private DbService dbService;
	
	@Autowired
	private TableService tableService;
	
	@Bean(name = "/dbservice")
	HttpInvokerServiceExporter dbService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(dbService);
		exporter.setServiceInterface(IDbService.class);
		return exporter;
	}

	@Bean(name = "/tableservice")
	HttpInvokerServiceExporter tableService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(tableService);
		exporter.setServiceInterface(ITableService.class);
		return exporter;
	}

	@Bean
	public Storage getStorage() {
		return Storage.Instance;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		// UserFriendlyConsoleInterface.start();
	}
}
