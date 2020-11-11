package com.nestor.electromecanica.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.electromecanica.entity.Venta;
import com.nestor.electromecanica.service.IVentaService;

@RestController
@RequestMapping("/api")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping("/venta")
	public List<Venta> getVenta(){
		return ventaService.getVentas();
	}
	
	@GetMapping("/venta/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		Venta venta = null;
		try {
			venta = this.ventaService.getVenta(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al buscar el venta a la BD.");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(venta == null) {
			response.put("mensaje", "No existe venta.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}
	
	@PostMapping("/venta")
	public ResponseEntity<?> createCliente(@RequestBody Venta venta){
	Venta ventaeNew = null;
	Map<String, Object> response = new HashMap<String, Object>();
	
	try {
		ventaeNew = this.ventaService.saveVenta(venta);
	} catch (DataAccessException e) {
		response.put("mensaje", "Error al dar de alta el venta en la BD");
		response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	if(ventaeNew == null) {
		response.put("mensaje", "No pudo darse de alta al venta.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Venta>(ventaeNew, HttpStatus.OK);
	}
	
	@PutMapping("/venta/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Venta venta){
		Venta ventaUpdate = null;
		Venta ventaNew    = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			ventaUpdate = this.ventaService.getVenta(id);
			ventaUpdate.setCantidad(venta.getCantidad());;
			ventaUpdate.setFechaVenta(venta.getFechaVenta());
			ventaUpdate.setHoraVenta(venta.getHoraVenta());
			ventaUpdate.setImporteTotal(venta.getImporteTotal());
			ventaNew = this.ventaService.saveVenta(ventaUpdate);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el venta en la BD");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Venta>(ventaNew, HttpStatus.OK);
	}
	
	@DeleteMapping("/venta/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			this.ventaService.deleteVenta(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error eliminar la venta de la BD");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		 response.put("mensaje","Venta eliminada con éxito.");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
}
