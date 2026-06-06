package com.servlet.bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperClass {
	
	public static void main(String [] args) {
		
		try {
			
			LinkedHashSet<String> lhs = new LinkedHashSet<String>();
			HashMap<String, String> hm = new HashMap<String, String>();
			
			File f = new File("C:\\Users\\PRANEETH\\eclipse-workspace\\TSRBank.zip_expanded\\TSRBank\\src\\Resources\\livejournal.txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			StringBuilder transaction = new StringBuilder(br.readLine());
			Pattern tid = Pattern.compile("\\d{10}\\s-");
			Pattern transactionsql = Pattern.compile("\"*\"");
			Matcher m = null;
			String transactionid = null;
			String nextline = null;
			
			while(transaction != null && !transaction.isEmpty()) {//if we find a transaction twice from the journal, then we can remove it from the HashSet immediately 
				//the remained transactions in the HashSet are to be processed from the journal
				m = tid.matcher(transaction);
				transactionid = m.find() ? m.group().substring(0, 10) : null;
				m = transactionsql.matcher(transaction);
				transaction.append(m.find() ? m.group().substring(1, m.group().length()-1) : "");//to remove the double quotes from the transaction sql
				
				if(transactionid!=null && !transactionid.isEmpty()) {
					if(lhs.contains(transactionid)) {
						lhs.remove(transactionid);
						hm.remove(transactionid);
					}else {
						lhs.add(transactionid);
						hm.put(transactionid, transaction.toString());
					}
				}
				System.out.println(transaction);
				nextline = br.readLine();
				transaction.replace(0, transaction.length(), nextline==null?"":nextline);//to clear the string builder and read the next line from the journal
			}
			
			System.out.println("Transactions to be processed from the journal : " + lhs.size());
			System.out.println("Transactions to be processed from the journal : " + hm.size());
			
			
		}catch(Exception e) {
			System.out.println("file system exception in background ledger thread:"+ e.getMessage());
		}
		
	}

}
