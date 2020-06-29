package com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.SessionHandler;
import com.Model.Account;
import com.Model.BeanFactory;
import com.Model.Client;
import com.Model.Cmd;
import com.Services.AccountService;
import com.Services.AccountServiceImpl;
import com.Services.UserService;

@Controller
public class ClientsController {
	
	BeanFactory bf = new BeanFactory();
	UserService us = bf.createUserServiceImpl();
	AccountService as = new AccountServiceImpl();
	
	//debug
	SessionHandler sh;
	
	@RequestMapping("clAccounts")
	public ModelAndView Accounts(ModelAndView mv){
		if(mv.isEmpty())
		 mv = new ModelAndView("accounts");
		sh = new SessionHandler();
		Client cl = (Client)sh.get(Client.class, 5);
		mv.addObject("acccountTypes", as.getAllTypes());
		mv.addObject("idClient", cl.getIdClient());
		mv.addObject("accounts", as.getAccountsFrom(cl.getIdClient()));
		return mv; 
	}
	
	@RequestMapping("clTransfers")
	public ModelAndView Transfers(){
		return new ModelAndView("transfers");
	}
	
	@RequestMapping("clLoans")
	public ModelAndView Loans(){
		return new ModelAndView("loans");
	}
	
	@RequestMapping(value="clRequestAccount", method=RequestMethod.POST)
	public ModelAndView RequestNewAccount(int idClient, int cmbAccounts) {
		Account acc = new Account();
		acc.setState(1);
		acc.setClient((Client)sh.get(Client.class, idClient));
		acc.setTypeAcc(as.getType(cmbAccounts));
		acc.setCreationDate(Cmd.crearFecha());
		Boolean result = as.insertAccount(acc);
		ModelAndView mv = new ModelAndView("accounts");
		mv.addObject("result", result); //true bien, false mal
		mv.addObject("msg", new String[]{"Opps... a ocurrido un error", "La petici�n se ha enviado correctamente"});
		return Accounts(mv);
	}

}
