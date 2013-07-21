package com.qsoft.business;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.entities.BankAccount;
import com.qsoft.persistence.entities.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/21/13
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BankAccountService {

    public void  setAccountDAO(BankAccountDAO bankAccountDAO);

    public BankAccount createAccount(String numberAcc, long time_stamp);

    public BankAccount getAccount(String numberAcc);

    public BankAccount doDeposit(String accNumber, double amount, String des);

    public BankAccount doWithdraw(String accNumber, double amount, String des);

    public List<Transaction> getAllTransaction(String numberAcc);

    public List<Transaction> getAllTransaction(String accNumber, long timeStart, long timeStop);

    public List<Transaction> getAllTransaction(String accNumber, int numRecord);
}
