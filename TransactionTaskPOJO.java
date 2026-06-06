package com.servlet.bank;

public class TransactionTaskPOJO {
	
	private final long TransactionId;
	private final long ToAccountNumber;
	private final long FromAccountNumber;
	private final String Password;
	private final long Amount;
	private final long TimeStamp;
	
	public TransactionTaskPOJO(long fromAccountNumber, long toAccountNumber, String password, long amount, long transactionId) {
		System.out.println("TransactionTaskPOJO constructor called-successfully created a transaction task object");
		this.TransactionId = transactionId;
		this.ToAccountNumber = toAccountNumber;//this refers to the current class variable or current object attribute
		//TransactionTaskPOJO.ToAccountNumber = toAccountNumber;// this refers to a class variable or an attribute of a class's bluprint only 
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
