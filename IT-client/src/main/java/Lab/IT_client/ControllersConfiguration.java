package Lab.IT_client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import Lab.IT_client.stage.main.MainSceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import ru.habrahabr.Application;
//import ru.habrahabr.ControllersConfiguration.ViewHolder;
//import ru.habrahabr.ui.MainController;
import service.IDbService;
import service.ITableService;

@Configuration
public class ControllersConfiguration {

	@Bean
	public HttpInvokerProxyFactoryBean dbServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8091/dbservice");
		invoker.setServiceInterface(IDbService.class);
		return invoker;
	}

	@Bean
	public HttpInvokerProxyFactoryBean tableServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8091/tableservice");
		invoker.setServiceInterface(ITableService.class);
		return invoker;
	}

	@Bean(name = "mainScene")
	public ViewHolder getMainScene() throws IOException {
		return loadView("src/main/java/Lab/IT_client/stage/main/MainScene.fxml");
	}

	@Bean
	public MainSceneController getMainController() throws IOException {
		return (MainSceneController) getMainScene().getController();
	}

	protected ViewHolder loadView(String url) throws IOException {
		InputStream fxmlStream = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			/**
			 * temporary for scenebuilder debug fxml
			 */
			loader.setLocation(new File(url).toURL());
			loader.load();
			return new ViewHolder(loader.getRoot(), loader.getController());
		} finally {
			if (fxmlStream != null) {
				fxmlStream.close();
			}
		}
	}

	public class ViewHolder {
		private Parent view;
		private Object controller;

		public ViewHolder(Parent view, Object controller) {
			this.view = view;
			this.controller = controller;
		}

		public Parent getView() {
			return view;
		}

		public void setView(Parent view) {
			this.view = view;
		}

		public Object getController() {
			return controller;
		}

		public void setController(Object controller) {
			this.controller = controller;
		}
	}
}