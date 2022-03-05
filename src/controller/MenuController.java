package controller;
import application.Main;
import javafx.fxml.FXML;


public class MenuController {
	private Main main;
	
	
	public void setMain(Main main) {
		this.main=main;
	}
	@FXML
	public void startListView() {
		main.startListView();
	}
	@FXML
	public void startAddAmount() {
		main.startAddAmounts();
	}
	
}
