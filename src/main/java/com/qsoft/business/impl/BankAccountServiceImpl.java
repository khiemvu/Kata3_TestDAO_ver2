package com.qsoft.business.impl;

import com.qsoft.business.BankAccountService;
import com.qsoft.business.TransactionService;
import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.entities.BankAccount;
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
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Override
    public void setAccountDAO(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    @Override
    public BankAccount createAccount(String numberAcc, long time_stamp) {
        BankAccount bankAccount = new BankAccount(numberAcc, time_stamp);
        bankAccount.setBalance(0.0);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BankAccount getAccount(String numberAcc) {
        BankAccount bankAccount = bankAccountDAO.findAccount(numberAcc);
        if(bankAccount != null){
            return bankAccount;
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BankAccount doDeposit(String accNumber, double amount, String des) {
        BankAccount bankAccount = getAccount(accNumber);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BankAccount doWithdraw(String accNumber, double amount, String des) {
        BankAccount bankAccount = getAccount(accNumber);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountDAO.saveAccount(bankAccount);
        return bankAccount;   //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getAllTransaction(String numberAcc) {
        TransactionService transactionService = new TransactionServiceImpl();
        return transactionService.getAllTransaction(numberAcc);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getAllTransaction(String numberAcc, long timeStart, long timeStop) {
        TransactionService transactionService = new TransactionServiceImpl();
        return transactionService.getAllTransaction(numberAcc, timeStart, timeStop);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getAllTransaction(String numberAcc, int numRecord) {
        TransactionService transactionService = new TransactionServiceImpl();
        return transactionService.getAllTransaction(numberAcc, numRecord);
    }
}
