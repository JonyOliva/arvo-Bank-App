package com.Services;

import java.util.ArrayList;

import com.Dao.ClientDao;

import com.Model.Client;

public class ClientServiceImpl implements ClientService{
	ClientDao dClient;
	
	public ClientServiceImpl(ClientDao dCli) {
		dClient = dCli;
	}
	
	public Boolean saveClient(Client client) {
			dClient.insertClient(client);
		return null;
	}

	public Boolean updateClient(Client client) {
		return cd.updateClient(client);
	}
	
	public Boolean deleteClient(int idClient,int idUser) {
		return (cd.deleteClient(idClient) && cd.deleteUser(idUser));
	}

	public Client readClient(int id) {
		return cd.getClient(id);
	}

	public ArrayList<Client> readClients() {

		ArrayList<Client> clients = dClient.getClients();
		return clients;
	}

	public Client readClient(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean emailExist(String email) {
		return dClient.emailExist(email);
	}

}
