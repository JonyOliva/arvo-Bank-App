package com.Services;

import java.util.ArrayList;

import com.Model.Account;
import com.Model.typeAccount;

public interface AccountService {

	public Boolean insertAccount(Account acc);
	public Boolean deleteAccount(int idAccount);
	public Boolean acceptAccount(int idAcc, int newState);
	public ArrayList<Account> getAccountsFrom(int idClient);
	public ArrayList<Account> getAllUnchekedAccounts();
	public ArrayList<typeAccount> getAllTypes();
	public typeAccount getType(int idType);
}
