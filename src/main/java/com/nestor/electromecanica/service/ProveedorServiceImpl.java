package com.nestor.electromecanica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestor.electromecanica.dao.ProveedorDao;
import com.nestor.electromecanica.entity.Proveedor;

@Service
public class ProveedorServiceImpl implements IProveedorService {
	
	@Autowired
	private ProveedorDao proveedorDao;

	@Override
	@Transactional(readOnly=true)
	public List<Proveedor> getProveedores() {
		return (List<Proveedor>) this.proveedorDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Proveedor getProveedor(Long id) {
		return this.proveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proveedor saveProveedor(Proveedor proveedor) {
		return this.proveedorDao.save(proveedor);
	}


	@Override
	@Transactional
	public void deleteProveedor(Long id) {
		 this.proveedorDao.deleteById(id);
	}

}
