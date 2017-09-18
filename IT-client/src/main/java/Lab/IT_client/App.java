package Lab.IT_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import service.IDbService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

	@Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8090/dbservice");
        invoker.setServiceInterface(IDbService.class);
        return invoker;
    }
	
	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(App.class, args);
		IDbService dbService = cac.getBean(IDbService.class);
		System.out.println(dbService.createDB("try").getDbName());
	}
}
