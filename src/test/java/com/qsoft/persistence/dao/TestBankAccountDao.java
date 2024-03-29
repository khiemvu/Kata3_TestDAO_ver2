package com.qsoft.persistence.dao;

import com.qsoft.persistence.entities.BankAccount;
import com.qsoft.persistence.entities.Transaction;
import com.qsoft.persistence.entities.validator.CheckNumberAccount;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

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

    private static Validator validation;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validation = factory.getValidator();
    }

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
    @Test
    public void testSaveABankAccountWithLengthOfNumberAccountGreater10(){
        BankAccount bankAccount = new BankAccount("01234567890", 100L);

        Set<ConstraintViolation<BankAccount>> violations = validation.validate(bankAccount, CheckNumberAccount.class);
        assertEquals(violations.size(),1);
        assertEquals(violations.iterator().next().getMessage(), "Number Account must has length is 10");
    }
    @Test
    public void testSaveABankAccountWithLengthOfNumberAccountLessThan10(){
        BankAccount bankAccount = new BankAccount("012345678", 100L);

        Set<ConstraintViolation<BankAccount>> violations = validation.validate(bankAccount, CheckNumberAccount.class);
        assertEquals(violations.size(),1);
        assertEquals(violations.iterator().next().getMessage(), "Number Account must has length is 10");
    }
    @Test
    public void testOpenAccountWithBalanceLessThanZero(){
        BankAccount bankAccount = new BankAccount("012345678", -100, 100L);
        Set<ConstraintViolation<BankAccount>> violations = validation.validate(bankAccount);
        assertEquals(violations.size(),1);
        assertEquals(violations.iterator().next().getMessage(), "Value min of balance must is 0");
    }
//    @Test
//    public void testOpenAccountWithNoTimeStamp(){
//        BankAccount bankAccount = new BankAccount("0123456789");
//        Set<ConstraintViolation<BankAccount>> violations = validation.validate(bankAccount, CheckTimeStamp.class);
//        assertEquals(violations.size(),1);
//        assertEquals(violations.iterator().next().getMessage(), "TimeStamp is compulsory");
//    }
    @Test
    public void testSaveATransactionToDB(){
        Transaction transaction = new Transaction("0123456789", 1000, "deposit", 1000L);
        transactionDAO.saveTransaction(transaction);
        List<Transaction> transactionList = transactionDAO.getAllTransaction("0123456789");
        assertEquals(4, transactionList.size());
        assertEquals("deposit", transactionList.get(3).getDes());
        assertEquals(1000L, transactionList.get(3).getTime_stamp());
        assertEquals(1000.0, transactionList.get(3).getBalance());
    }
    @Test
    public void testDoATransactionWithAmountLessThanZero(){
        Transaction transaction = new Transaction("0123456789", -100, "deposit", 1500L);
        Set<ConstraintViolation<Transaction>> violations = validation.validate(transaction);
        assertEquals(1, violations.size());
        assertEquals(violations.iterator().next().getMessage(), "Amount money for transaction must greater 0");
    }
    @Test
    public void testGetAllTransactionOfAAccount(){
        List<Transaction> listTransaction = transactionDAO.getAllTransaction("0123456789");
        assertEquals(3, listTransaction.size());
        assertEquals(10000.0, listTransaction.get(0).getBalance());
        assertEquals("withdraw", listTransaction.get(1).getDes());
        assertEquals(60000L, listTransaction.get(2).getTime_stamp());
    }
    @Test
    public void testGetAllTransactionOfAAccountFromTime_startToTime_stop(){
        List<Transaction> transactionList = transactionDAO.getAllTransaction("0123456789", 30000L, 60000L);
        assertEquals(2, transactionList.size());
        assertEquals("withdraw", transactionList.get(1).getDes());
    }
    @Test
    public void testGetNTransactionNewest(){
        List<Transaction> transactionList = transactionDAO.getAllTransaction("0123456789", 2);
        assertEquals(2, transactionList.size());
        assertEquals("withdraw", transactionList.get(1).getDes());

    }
}
