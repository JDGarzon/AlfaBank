package controller;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AlfaBank;
import model.Amount;
import javafx.scene.control.DatePicker;


public class TableViewController {
	private Main main;
	@FXML
	TableColumn<Amount, Double> spended;
	@FXML
	TableColumn<Amount, Double> deposit;
	@FXML
	Label textDeposit;
	@FXML
	DatePicker date1;
	@FXML
	DatePicker date2;
	@FXML
	TableColumn<AlfaBank, Double> balance;
	@FXML
	TableView<Amount> spendsObsList;
	@FXML
	TableView<Amount> depositsObsList;
	ObservableList<Amount> spendsList;
	ObservableList<Amount> depositsList;
	
	public void initialize() {
		spended.setCellValueFactory(new PropertyValueFactory<>("amount"));
		deposit.setCellValueFactory(new PropertyValueFactory<>("amount"));
	}
	public void setMain(Main main) {
		this.main = main;
	}
	@FXML
	public void refresh() {	
		spendsList= (ObservableList<Amount>)FXCollections.observableArrayList(main.getSpends());
		spendsObsList.setItems(spendsList);
		textDeposit.setText(main.getBalance()+"");
		depositsList=(ObservableList<Amount>) FXCollections.observableArrayList(main.getDeposits());
		depositsObsList.setItems(depositsList);
		
	}
	
	@FXML
	public void buttonFilt() {
		
		filter();
	}
	public void filter() {
		spendsList= (ObservableList<Amount>)FXCollections.observableArrayList(main.getSpends());
		depositsList=(ObservableList<Amount>) FXCollections.observableArrayList(main.getDeposits());
		FilteredList<Amount> filteredFirstList1 = new FilteredList<>(spendsList,i->i.getDate().isAfter(date1.getValue()));
		FilteredList<Amount> filteredSecondList1= new FilteredList<>(depositsList,i->i.getDate().isAfter(date1.getValue()));
		FilteredList<Amount> filteredFirstList2 = new FilteredList<>(filteredFirstList1,i->i.getDate().isBefore(date2.getValue()));
		FilteredList<Amount> filteredSecondList2= new FilteredList<>(filteredSecondList1,i->i.getDate().isBefore(date2.getValue()));
		spendsObsList.setItems(filteredFirstList2);
		depositsObsList.setItems(filteredSecondList2);
		calculateBalanceFiltered();
	}
	public void calculateBalanceFiltered() {
		ObservableList<Amount> toCalculateSpends=spendsObsList.getItems();
		ObservableList<Amount> toCalculateDeposits=depositsObsList.getItems();
		double amountSpends=0;
		double amountDeposits=0;
		int i;
		for(i=0;i<toCalculateSpends.size();i++) {
			amountSpends+=toCalculateSpends.get(i).getAmount();
		}
		for(i=0;i<toCalculateDeposits.size();i++) {
			amountDeposits+=toCalculateDeposits.get(i).getAmount();
		}
		textDeposit.setText((amountDeposits-amountSpends)+"");
	}
	@FXML
	public void deleteElement() {
		int index=0;
		index=spendsObsList.getSelectionModel().getSelectedIndex();
		if(index!=-1) {
			
			spendsList.remove(index);
			spendsObsList.setItems(spendsList);
			main.deleteSpend(index);
			
			
		}
		index=depositsObsList.getSelectionModel().getSelectedIndex();
		if(index!=-1) {
			
			depositsList.remove(index);
			depositsObsList.setItems(depositsList);
			main.deleteDeposit(index);
		}
		refresh();
	}
	
}
