package com.nestor.electromecanica.service;

import java.util.List;

import com.nestor.electromecanica.entity.Venta;

public interface IVentaService {

	public List<Venta> getVentas();
	public Venta getVenta(Long id);
	public Venta saveVenta(Venta venta);
	public void deleteVenta(Long id);
}
