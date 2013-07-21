package com.qsoft;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.entities.BankAccount;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/21/13
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        BankAccountDAO bankAccountDAO = (BankAccountDAO) applicationContext.getBean("bankAccountDAOImpl");

        BankAccount bankAccount = new BankAccount("0123456789", 100L);
        bankAccountDAO.saveAccount(bankAccount);
    }
}
