package com.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.ClientDao;
import com.Dao.ClientDaoImpl;
import com.Model.Account;
import com.Model.BeanFactory;
import com.Model.City;
import com.Model.Client;
import com.Model.Countrie;
import com.Model.Genre;
import com.Model.Province;
import com.Model.User;
import com.Services.AccountService;
import com.Services.AccountServiceImpl;
import com.Services.ClientService;
import com.Services.ClientServiceImpl;
import com.Services.GenreService;
import com.Services.GenreServiceImpl;
import com.Services.LocationService;
import com.Services.LocationServiceImpl;
import com.Services.UserService;
import com.Services.UserServiceImpl;
import com.main.Utilities;

@Controller
public class AdministrativeController {

	BeanFactory bFactory = new BeanFactory();
	BeanFactory bf = new BeanFactory();
	ClientDao cd = new ClientDaoImpl();
	ClientService cs = new ClientServiceImpl();
	LocationService ls = new LocationServiceImpl();// bf.createLocationServiceImpl();
	GenreService gs = new GenreServiceImpl(); // bf.createGenreServiceImpl();
	AccountService accs = new AccountServiceImpl();
	UserService us = new UserServiceImpl();

	@RequestMapping("admClients")
	public ModelAndView Clients() {
		ModelAndView MV = new ModelAndView("admClients");
		ClientServiceImpl sClient = bFactory.createClientServiceImpl();

		MV.addObject("clients", sClient.readClients());

		return MV;
	}

	@RequestMapping("admAccounts")
	public ModelAndView Accounts() {
		ModelAndView mv = new ModelAndView("admAccounts");
		mv.addObject("uncheckedAccounts", accs.getAllUnchekedAccounts());
		return mv;
	}

	@RequestMapping(value="admAccountsState", method = RequestMethod.POST)
	public ModelAndView AccountState(String accept, String reject){
		ModelAndView mv = new ModelAndView("admAccounts");
		Boolean result = false;
		if(accept != null) {
			result = accs.acceptAccount(Integer.parseInt(accept), 2);
		}else if(reject != null) {
			result = accs.acceptAccount(Integer.parseInt(reject), 0);
		}
		mv.addObject("uncheckedAccounts", accs.getAllUnchekedAccounts());

		mv.addObject("result", result); // true bien, false mal
		mv.addObject("msg", new String[] { "Ha ocurrido un error", "Operación realizada correctamente" });

		return mv;
	}
	
	@RequestMapping(value="admDeleteAccount", method=RequestMethod.POST)
	public ModelAndView DeleteAccount(int idAccount, int idClient) {
		ModelAndView mv = new ModelAndView("admClientProfile");
		Boolean result = accs.deleteAccount(idAccount);
		mv.addObject("result", result);
		mv.addObject("msg", new String[] { "Ha ocurrido un error", "La eliminación fue realizada correctamente" });
		return ClientProfile(idClient, mv);
	}

	@RequestMapping("admClientProfile")
	public ModelAndView ClientProfile(int id, ModelAndView MV) {
		if(MV.isEmpty())
			MV = new ModelAndView("admClientProfile");

		try {
			Client client = cd.getClient(id);
			MV.addObject("client", client);
			MV.addObject("countries", ls.getAllCountries());
			MV.addObject("genres", gs.getAllGenres());
			ArrayList<Province> provs = ls.getAllProvince();
			MV.addObject("provinces", provs);
			ArrayList<Account> acc = accs.getAccountsFrom(client.getIdClient());
			MV.addObject("accounts", acc);
		} catch (Exception e) {
			e.printStackTrace();
			return MV;
		}

		return MV;
	}

	@RequestMapping(value = "admUpdateClient", method = RequestMethod.POST)
	public ModelAndView UpdateClient(String txtDni, int drpGenre, String txtEmail, String txtDate, int drpCountry,
			int drpProvince, String drpCity, String txtUser, String txtPass, int txtIdClient) {
		ModelAndView MV = new ModelAndView();
		Date date = null;
	    try {
				date=new SimpleDateFormat("yyyy-MM-dd").parse(txtDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	    Genre g = gs.getGenre(drpGenre);
	    Countrie n = ls.getCountrie(drpCountry);
	    Province p = ls.getProvince(drpProvince);
	    Client c = cs.readClient(txtIdClient);
	    c.getUser().setPassword(txtPass);
	    c.getUser().setUserName(txtUser);
	    c.setBirthdate(date);
	    c.setDni(txtDni);
	    c.setEmail(txtEmail);
	    c.setGenre(g);
	    c.setNationality(n);
	    c.setProvince(p);
	    
	    cs.updateClient(c);
	    MV.setViewName("redirect:admClientProfile.do?id="+txtIdClient);
		return MV;
	}

	@RequestMapping("admDeleteClient")
	public ModelAndView DeleteClient(int idClient, int idUser) {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("admClients");
		cs.deleteClient(idClient, idUser);

		return MV;
	}

	@RequestMapping("admLoans")
	public ModelAndView Loans() {
		return new ModelAndView("admLoans");
	}

	@RequestMapping("admReports")
	public ModelAndView Reports() {
		return new ModelAndView("admReports");
	}
}