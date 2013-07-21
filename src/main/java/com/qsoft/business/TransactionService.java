package com.qsoft.business;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.entities.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/21/13
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionService {
    public void initTransactionDAO(TransactionDAO transactionDAO);

    public Transaction createTransaction(String numberAcc, long time_stamp, double amount, String des);

    public List<Transaction> getAllTransaction(String accNumber);

    public List<Transaction> getAllTransaction(String accNumber, long startTime, long stopTime);

    public List<Transaction> getAllTransaction(String accNumber, int number);
}
