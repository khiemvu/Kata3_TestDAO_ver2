package com.qsoft.persistence.dao;

import com.qsoft.persistence.entities.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/15/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO {
    void saveTransaction(Transaction capture);

    List<Transaction> getAllTransaction(String s);

    List<Transaction> getAllTransaction(String s, long startTime, long stopTime);

    List<Transaction> getAllTransaction(String s, int n);
}
