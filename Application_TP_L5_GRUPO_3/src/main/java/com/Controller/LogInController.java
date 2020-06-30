package com.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Model.Administrative;
import com.Model.BeanFactory;
import com.Model.User;
import com.Services.ClientService;
import com.Services.ClientServiceImpl;
import com.Services.LocationService;
import com.Services.UserService;

@Controller
// @SessionAttributes("user")
public class LogInController {

	BeanFactory bf = new BeanFactory();
	UserService us = bf.createUserServiceImpl();

	ClientService sClient = bf.createClientServiceImpl();
	LocationService sLocation = bf.createLocationServiceImpl();

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView redirectLogIn(String txtUsername, String txtPass, HttpServletRequest req) {
		ModelAndView MV = new ModelAndView();

		User user = us.getUser(txtUsername, txtPass);

		HttpSession session = req.getSession();

		if (user == null) {
			MV.addObject("NoUser", "El usuario solicitado no existe");
			MV.setViewName("index");
			return MV;
		} else if (user.getUserType().equals("Admin")) {
			Administrative adm = us.getAdmin(user);
			
			
			MV.addObject("clients",sClient.readClients());
			MV.addObject("countries", sLocation.getAllCountries());
			MV.addObject("province", sLocation.getAllProvince());
			
			session.setAttribute("user", adm);
			MV.setViewName("admClients");
		} else if (user.getUserType().equals("Client")) {
			// Client client = us.getClient(user);
			// MV.addObject("user", client);
			// MV.addObject("accounts", client.getBankAccounts());
			MV.setViewName("accounts");
		}

		return MV;
	}
	/*
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView redirectLogIn(String txtUsername, String txtPass, HttpServletRequest req) {
		ModelAndView MV = new ModelAndView();

		User user = us.getUser(txtUsername, txtPass);

		HttpSession session = req.getSession();

		if (user == null) {
			MV.addObject("NoUser", "El usuario solicitado no existe");
			MV.setViewName("index");
			return MV;
		} else if (user.getUserType().equals("Admin")) {
			Administrative adm = us.getAdmin(user);
			session.setAttribute("user", adm);
			MV.setViewName("redirect:admClients.do");
		} else if (user.getUserType().equals("Client")) {
			// Client client = us.getClient(user);
			// MV.addObject("user", client);
			// MV.addObject("accounts", client.getBankAccounts());
			MV.setViewName("accounts");
		}

		return MV;
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.POST)
	public ModelAndView redirectLogOut(HttpServletRequest req) {
		ModelAndView MV = new ModelAndView();
		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		MV.setViewName("index");
		return MV;
	}*/
}
