package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.entities.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/16/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Component
public class TransactionDAOImpl implements TransactionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTransaction(Transaction transaction) {
        entityManager.persist(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction(String number_account) {
        Query query = entityManager.createQuery("select t from Transaction t where t.number_account = :number_account");
        query.setParameter("number_account", number_account);
        List<Transaction> transactionList = query.getResultList();
        return transactionList;
    }

    @Override
    public List<Transaction> getAllTransaction(String number_account, long startTime, long stopTime) {
        Query query = entityManager.createQuery("select t from Transaction t where t.number_account = :number_account " +
                "and t.time_stamp >= :startTime and t.time_stamp <= :stopTime");
        query.setParameter("number_account", number_account);
        query.setParameter("startTime", startTime);
        query.setParameter("stopTime", stopTime);
        List<Transaction> transactionList = query.getResultList();
        return transactionList;
    }

    @Override
    public List<Transaction> getAllTransaction(String number_account, int number) {
        Query query = entityManager.createQuery("select t from Transaction t where t.number_account = :number_account");
        query.setParameter("number_account",number_account);
        query.setMaxResults(number);
        List<Transaction>transactionList = query.getResultList();
        return transactionList;
    }
}
