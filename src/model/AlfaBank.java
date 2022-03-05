package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class AlfaBank {
	
	private double balance;
	private ArrayList<Amount> spends;
	private ArrayList<Amount> deposits;
	private int index;
	
	public AlfaBank() {
		balance=(double) 0;
		spends=new ArrayList<>();
		deposits=new ArrayList<>();
		index=0;
	}
	
	public double getBalance() {
		return balance;
	}
	public ArrayList<Amount> getSpends() {
		return spends;
	}
	public ArrayList<Amount> getDeposits() {
		return deposits;
	}
	public void addAmount(double amount,LocalDate date,String description,int indextype) {
		Type type=null;
		
	
		switch(indextype){
			case 1:
				type=Type.deposite;
				deposits.add(new Amount(amount, date, description, type));
				break;
			case 2:
				type=Type.spend;
				spends.add(new Amount(amount, date, description, type));
				break;
		}
		
		calculateBalance();
		index+=1;
	}
	
	public void calculateBalance() {
		balance=0;
		for(int i=0;i<index;i++) {
			if(i<spends.size()) {
				balance-=spends.get(i).getAmount();
			}
			if(i<deposits.size()){
				balance+=deposits.get(i).getAmount();
			}
			
		}
	}
	
	public void deleteSpend(int i) {
		spends.remove(i);
		calculateBalance();
	}
	public void deleteDeposit(int i) {
		deposits.remove(i);
		calculateBalance();
	}
	
	
}
