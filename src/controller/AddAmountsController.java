package controller;


import java.time.LocalDate;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

public class AddAmountsController {
	private Main main;
	private int indexType=0;
	@FXML
	private TextField amount;
	@FXML
	private DatePicker date;
	@FXML
	private TextArea description;

	
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	
	@FXML
	public void save() {
		Alert alert;
		if(indexType==0) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("No type");
			alert.setHeaderText("Type isn't selected");
			alert.setContentText("Type should be selected");
			alert.showAndWait();
		}else if(amount.getText().equals("")) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("No amount");
			alert.setHeaderText("amount isn't specified");
			alert.setContentText("amount should be written");
			alert.showAndWait();
		}else  {
			double amountNumber=Double.parseDouble(amount.getText());
			String descriptionToSend=description.getText();
			main.addAmount(amountNumber,(LocalDate) date.getValue(),descriptionToSend , indexType);
			amount.clear();
			description.clear();
			indexType=0;
		}
	}
	
	@FXML
	public void typeDeposit() {
		indexType=1;
	}
	@FXML
	public void typeSpend() {
		indexType=2;
	}
	
}
