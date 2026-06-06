package com.servlet.bank;

public class TransactionTaskPOJO1 {
	
	private long TransactionId;
	private long ToAccountNumber;
	private long FromAccountNumber;
	private String Password;
	private long Amount;
	private long TimeStamp;
	
//	public TransactionTaskPOJO1(long fromAccountNumber, long toAccountNumber, String password, long amount, long transactionId) {
//		System.out.println("TransactionTaskPOJO constructor called-successfully created a transaction task object");
//		this.TransactionId = transactionId;
//		this.ToAccountNumber = toAccountNumber;//this refers to the current class variable or current object attribute
//		//TransactionTaskPOJO.ToAccountNumber = toAccountNumber;// this refers to a class variable or an attribute of a class's bluprint only 
//		this.FromAccountNumber = fromAccountNumber;
//		this.Password = password;
//		this.Amount = amount;
//		this.TimeStamp = System.currentTimeMillis();
//	}
	
	public void TransactionTaskPOJO1() {
		
	}
		
	
	public void settermethod(long fromAccountNumber, long toAccountNumber, String password, long amount, long transactionId) {
		//System.out.println("TransactionTaskPOJO setter called-successfully created and assigned values to a transaction task object");
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
//	public long getTimeStamp() {
//		return TimeStamp;
//	}
	public long getTransactionID() {
		return TransactionId;
	}

}
