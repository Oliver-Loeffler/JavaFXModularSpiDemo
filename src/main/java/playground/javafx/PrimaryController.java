package playground.javafx;

import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PrimaryController implements Initializable {
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private ListView<String> driverList;
	
	private ObservableList<String> drivers;

    @FXML
    private void switchToSecondary() throws IOException {
        ModularSpiDemo.setRoot("secondary");
    }
    
    @FXML
    private void discoverDrivers() {
    	resetView();
    	ServiceLoader.load(java.sql.Driver.class)
				     .forEach(this::getDriverClassName);
    }
    
    @FXML
    private void resetView() {    	
    	drivers.clear();
    }

	private void getDriverClassName(Driver provider) {
		drivers.add(provider.getClass().getName());
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		drivers = FXCollections.observableList(new ArrayList<>());
		driverList.setItems(drivers);
		statusLabel.textProperty()
		           .bind(Bindings.size(drivers).asString("%d drivers found"));
	}
}
