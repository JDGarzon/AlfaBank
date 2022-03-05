package model;
import java.time.LocalDate;

public class Amount {
	
	private Type type;
	private Double amount;
	private LocalDate date;
	private String description;
	
	public Amount(double amount,LocalDate date,String description,Type type) {
		this.amount=amount;
		this.date=date;
		this.description=description;
		this.type=type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
