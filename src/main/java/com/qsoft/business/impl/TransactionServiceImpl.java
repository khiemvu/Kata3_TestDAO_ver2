package com.qsoft.business.impl;

import com.qsoft.business.TransactionService;
import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/21/13
 * Time: 8:02 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public void initTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Override
    public Transaction createTransaction(String numberAcc, long time_stamp, double amount, String des) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getAllTransaction(String accNumber) {
        List<Transaction> transactionList = transactionDAO.getAllTransaction(accNumber);
        if(transactionList == null)
            return null;
        else
            return transactionList;
    }

    @Override
    public List<Transaction> getAllTransaction(String accNumber, long startTime, long stopTime) {
        List<Transaction> transactionList = transactionDAO.getAllTransaction(accNumber, startTime, stopTime);
        if(transactionList == null)
            return null;
        else
            return transactionList;
    }

    @Override
    public List<Transaction> getAllTransaction(String accNumber, int number) {
        List<Transaction> transactionList = transactionDAO.getAllTransaction(accNumber, number);
        if(transactionList == null)
            return null;
        else
            return transactionList;
    }
}
