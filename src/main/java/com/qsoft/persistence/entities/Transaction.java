package com.qsoft.persistence.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Khiem
 * Date: 7/15/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "id_transaction_seq", sequenceName = "id_transaction_seq", allocationSize= 1)
public class Transaction
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_transaction_seq")
    @Id
    @Column(name="id_transaction")
    private long id;

    @Column(name= "number_account")
    private String number_account;

    @Column(name="amount")
    private double balance;

    @Column(name="des")
    private String des;

    @Column(name="time_stamp")
    private long time_stamp;


    public Transaction(String number_acc, double balance, String des, long time_stamp) {
        this.number_account = number_acc;
        this.balance = balance;
        this.des = des;
        this.time_stamp = time_stamp;
    }

    public Transaction(){

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber_acc() {
        return number_account;
    }

    public void setNumber_acc(String number_acc) {
        this.number_account = number_acc;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }
}
