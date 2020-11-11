package com.nestor.electromecanica.service;

import java.util.List;

import com.nestor.electromecanica.entity.Proveedor;

public interface IProveedorService {

	public List<Proveedor> getProveedores();
	public Proveedor getProveedor(Long id);
	public Proveedor saveProveedor(Proveedor proveedor);
	public void deleteProveedor(Long id);
	
}
