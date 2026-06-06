package com.servlet.bank;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import com.servlet.bank.*;

public class RingBufferOperator {//this will be a singleton class which will be used by the transaction queue operator to store the transactions in a ring buffer and the transaction processor to take the transactions from the ring buffer and process them, it is a shared resource between the transaction queue operator and the transaction processor and it is a bridge between them, it is a thread safe class as it will be accessed by multiple threads concurrently, it will have two separate ring buffers for money transfer tasks and deposit and withdraw tasks, but for simplicity we will implement only one ring buffer for money transfer tasks for now, we can implement another ring buffer for deposit and withdraw tasks in the future if needed
	
	private final int capacity;
	private final TransactionTaskPOJO1[] ringbuffer;
	private volatile AtomicInteger writeposition;
	private volatile int readposition;
	private volatile int count; // number of elements currently in buffer
	private int currentvalue;
	private int index;
	
	private RingBufferOperator() {//currently we use 10000 as the capacity
		this.capacity = 10000;
		this.ringbuffer = new TransactionTaskPOJO1[capacity];//we will overwrite the old transactions which are already processed or commited to the database
		this.writeposition = new AtomicInteger(0);
		this.readposition = 0;
		this.count = 0;
		
		//now fill all the indices of the ring buffer with a default value, or else it will assign null automatically
		Arrays.fill(this.ringbuffer, new TransactionTaskPOJO1());
	}
	
	private static final RingBufferOperator singletoninstance = new RingBufferOperator();
	
	public static RingBufferOperator getInstance() {
		return singletoninstance;
	}
	
	/**
	 * Blocking add: waits until space is available.
	 * Keeps signature that throws InterruptedException so callers can choose how to handle it.
	 */
	public synchronized void addTransactionTask(long accountno2, long accountno1, String password, long amount, long transactionid) throws InterruptedException {
		
		
		if (accountno2 <= 0 || accountno1 <= 0 || password == null || amount <= 0 || transactionid <= 0) {
			throw new IllegalArgumentException("transaction == null");
		}
	    while (count == capacity) {//it is a backpressure mechanism to give time to the transaction processor to process the transactions and free up space in the ring buffer, otherwise if we keep adding transactions without waiting for the transaction processor to process them, we will end up with a lot of unprocessed transactions in the ring buffer and it will lead to a out of memory error
	        wait();
	    }
	    
	    //System.out.println("Adding transaction task to ring buffer at position : " + writeposition);
	    //System.out.println(ringbuffer[writeposition]);
	    //System.out.println(ringbuffer[0]);
	    currentvalue = writeposition.getAndIncrement();
	    index = currentvalue % capacity;
	    ringbuffer[index].settermethod(accountno2, accountno1, password, amount, transactionid);
	    //writeposition = (currentvalue + 1) % capacity;
	    count++;
	    notifyAll();
	}
	
	/**
	 * Blocking take: waits until an element is available.
	 * This method handles InterruptedException by re-setting the thread's interrupt flag and returning null.
	 */
//	public synchronized TransactionTaskPOJO1 takeTransactionTask() {
//	    while (count == 0) {
//	        try {
//	            wait();
//	        } catch (InterruptedException e) {
//	            Thread.currentThread().interrupt();
//	            return null;
//	        }
//	    }
//	    TransactionTaskPOJO1 t = ringbuffer[readposition];
//	    ringbuffer[readposition] = null; // avoid memory leak
//	    readposition = (readposition + 1) % capacity;
//	    count--;
//	    notifyAll();
//	    return t;
//	}
	
	public synchronized TransactionTaskPOJO1 takeTransactionTask(long timeoutMs) {
	    long startTime = System.currentTimeMillis();
	    try {
	        while (count == 0) {//count = 0 => no task is available in the ring buffer to process
	            try {
	                long elapsedTime = System.currentTimeMillis() - startTime;
	                if (elapsedTime >= timeoutMs) {
	                    return null; // Timeout reached, return null
	                }
	                wait(timeoutMs - elapsedTime);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	                return null;
	            }
	        }
	        //System.out.println("returning transaction task from ring buffer at position : " + readposition);
	        return ringbuffer[readposition];
	    } finally {
	        if (count > 0) { // Ensure we only update positions if a task was actually taken
	            readposition = (readposition + 1) % capacity;
	            count--;
	            notifyAll();
	        }
	    }
	}
	
//	/**
//	 * Optional non-blocking offer if callers need it.
//	 */
//	public synchronized boolean offerTransactionTask(TransactionTaskPOJO transaction) {
//		if (transaction == null) return false;
//		if (count == capacity) return false;
//		ringbuffer[writeposition] = transaction;
//		writeposition = (writeposition + 1) % capacity;
//		count++;
//		notifyAll();
//		return true;
//	}

}