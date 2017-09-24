package Lab.IT_client;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.TreeSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import Lab.IT_client.stage.main.MainSceneController;
import db.DB;
import fake.FakeDB;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.IDbService;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
public class App extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	///////
	private FakeDB fakeDB;

	public App() {
		fakeDB = new FakeDB();
	}

	// @Bean
	// public HttpInvokerProxyFactoryBean invoker() {
	// HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
	// invoker.setServiceUrl("http://localhost:8090/dbservice");
	// invoker.setServiceInterface(IDbService.class);
	// return invoker;
	// }

	public static void main(String[] args) {
//		 ConfigurableApplicationContext cac = SpringApplication.run(App.class,
//		 args);
		// IDbService dbService = cac.getBean(IDbService.class);
		// System.out.println(dbService.createDB("try").getDbName());

		launch(args);

	}

	public void initRootLayout() {
		//
		try {
			// Загружаем корневой макет из fxml файла.
			FXMLLoader loader = new FXMLLoader();
			URL url = new File("src/main/java/Lab/IT_client/stage/main/MainScene.fxml").toURL();
			loader.setLocation(url);
			rootLayout = (BorderPane) loader.load();

			MainSceneController mainSceneController = loader.getController();
			mainSceneController.setDataFromFakeDB(fakeDB);

			// Отображаем сцену, содержащую корневой макет.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();
	}
}
