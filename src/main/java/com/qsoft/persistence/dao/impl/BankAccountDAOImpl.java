package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.entities.BankAccount;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/16/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Component
public class BankAccountDAOImpl implements BankAccountDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAccount(BankAccount bankAccount) {
        entityManager.persist(bankAccount);
    }

    @Override
    public BankAccount findAccount(String accNumber) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
