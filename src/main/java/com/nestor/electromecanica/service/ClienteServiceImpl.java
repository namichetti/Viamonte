package com.nestor.electromecanica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestor.electromecanica.dao.ClienteDao;
import com.nestor.electromecanica.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> getClientes() {
		return  (List<Cliente>)this.clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente getCliente(Long id) {
		return this.clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteCliente(Long id) {
		this.clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		return this.saveCliente(cliente);
	}

}
