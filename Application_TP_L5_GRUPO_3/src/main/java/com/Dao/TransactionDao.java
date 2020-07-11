package com.Dao;

import java.math.BigInteger;
import java.util.ArrayList;


import com.Model.Transaction;
import com.Model.TransactionsPerMonth;
import com.Model.typeMove;

public interface TransactionDao {

	public Boolean insertTransaction(Transaction trans);
	public Boolean verifyTransaction(Transaction trans);
	public ArrayList<Transaction> getAllTransactions(int idAccount);
	public ArrayList<Transaction> getTransactionsFrom(int originAccount);
	public ArrayList<Transaction> getTransactionsTo(int destinationAccount);
	public typeMove getType(int idTypeMove);
	public ArrayList<TransactionsPerMonth> getTransactionsBetween(String init, String end);
	public ArrayList<String> getTransactionsBetweenName(String init, String end);
}
