package com.servlet.bank;

public class TransactionTaskPOJO1 {
	
	private long TransactionId;
	private long ToAccountNumber;
	private long FromAccountNumber;
	private String Password;
	private long Amount;
	private long TimeStamp;
	
	public void TransactionTaskPOJO1() {
		
	}
		
	public void settermethod(long fromAccountNumber, long toAccountNumber, String password, long amount, long transactionId) {
		this.TransactionId = transactionId;
		this.ToAccountNumber = toAccountNumber;//this refers to the current class variable or current object attribute 
		this.FromAccountNumber = fromAccountNumber;
		this.Password = password;
		this.Amount = amount;
		this.TimeStamp = System.currentTimeMillis();
	}
	
	public long getToAccountNumber() {
		return ToAccountNumber;
	}
	
	public long getFromAccountNumber() {
		return FromAccountNumber;
		
	}
	public String getPassword() {
		return Password;
	}
	public long getAmount() {
		return Amount;
	}
	public long getTransactionID() {
		return TransactionId;
	}

}
