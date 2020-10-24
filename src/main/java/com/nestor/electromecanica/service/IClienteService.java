package com.nestor.electromecanica.service;

import java.util.List;

import com.nestor.electromecanica.entity.Cliente;

public interface IClienteService {

	public List<Cliente> getClientes();
	public Cliente getCliente(Long id);
	public void deleteCliente(Long id);
	public Cliente saveCliente(Cliente cliente);
}
