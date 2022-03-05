package application;
	
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import controller.MenuController;
import controller.TableViewController;
import controller.AddAmountsController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.AlfaBank;
import model.Amount;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private AlfaBank bank;
	private Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			bank=new AlfaBank();
			startMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void startMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			
			BorderPane root;
			root = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void startAddAmounts() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/AddAmounts.fxml"));
			BorderPane addStage = (BorderPane)loader.load();
			AddAmountsController control=loader.getController();
			control.setMain(this);
			BorderPane root;
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(addStage);
			
			currentStage.setHeight(400);
			currentStage.setWidth(600);
			stage.show();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void startListView() {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/TableView.fxml"));
			BorderPane addStage = (BorderPane)loader.load();
			TableViewController control=loader.getController();
			BorderPane root;
			control.setMain(this);
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(addStage);
			
			currentStage.setHeight(400);
			currentStage.setWidth(600);
			stage.show();
			control.refresh();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void deleteSpend(int i) {
		bank.deleteSpend(i);
	}
	public void deleteDeposit(int i) {
		bank.deleteDeposit(i);
	}
	
	public void addAmount(double amount,LocalDate date,String description,int Indextype) {
		bank.addAmount(amount, date, description, Indextype);
		bank.calculateBalance();
	}
	
	public ArrayList<Amount> getSpends() {
		return bank.getSpends();
	}
	public ArrayList<Amount> getDeposits(){
		return bank.getDeposits();
	}
	public double getBalance() {
		return bank.getBalance();
	}
	
	
	
}
