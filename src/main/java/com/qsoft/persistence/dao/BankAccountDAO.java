package com.qsoft.persistence.dao;

import com.qsoft.persistence.entities.BankAccount;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/15/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BankAccountDAO {
    public void saveAccount(BankAccount capture);

    public BankAccount findAccount(String accNumber);
}
