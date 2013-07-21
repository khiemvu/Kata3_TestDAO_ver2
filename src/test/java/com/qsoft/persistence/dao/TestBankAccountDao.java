package com.qsoft.persistence.dao;

import com.qsoft.persistence.entities.BankAccount;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/21/13
 * Time: 8:16 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public class TestBankAccountDao {
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private DataSource dataSourceTest;

    private IDatabaseTester iDatabaseTester;

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception
    {
        iDatabaseTester = new DataSourceDatabaseTester(dataSourceTest);
        iDatabaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        iDatabaseTester.setDataSet(dataSet);
        iDatabaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
        iDatabaseTester.onTearDown();
    }

    @Test
    public void testOpenABankAccountThenSaveToDB(){
        BankAccount bankAccount = new BankAccount("1234567890", 100L);
        bankAccountDAO.saveAccount(bankAccount);
        BankAccount checkAccount = bankAccountDAO.findAccount("1234567890");
        assertEquals(bankAccount.getNumber_acc(), checkAccount.getNumber_acc());
        assertEquals(bankAccount.getBalance(), checkAccount.getBalance());
        assertEquals(bankAccount.getTime_stamp(), checkAccount.getTime_stamp());
    }
    @Test
    public void testGetInfoABankAccountFromDB(){
        BankAccount account = bankAccountDAO.findAccount("0123456789");
        assertNotNull(account);
        assertEquals(account.getBalance(), 100.0);
        assertEquals(account.getTime_stamp(), 1000);
    }
}
