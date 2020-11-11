package com.nestor.electromecanica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestor.electromecanica.dao.VentaDao;
import com.nestor.electromecanica.entity.Venta;

@Service
public class VentaServiceImpl implements IVentaService {
	
	@Autowired
	private VentaDao ventaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Venta> getVentas() {
		return (List<Venta>) ventaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Venta getVenta(Long id) {
		return ventaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta saveVenta(Venta venta) {
		return ventaDao.save(venta);
	}

	@Override
	@Transactional
	public void deleteVenta(Long id) {
		ventaDao.deleteById(id);
	}

}
