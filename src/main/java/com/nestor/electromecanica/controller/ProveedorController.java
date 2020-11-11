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

import com.nestor.electromecanica.entity.Proveedor;
import com.nestor.electromecanica.service.IProveedorService;

@RestController
@RequestMapping("/api")
public class ProveedorController {
	
	@Autowired
	private IProveedorService proveedorService;
	
	@GetMapping("/proveedor")
	public List<Proveedor> getProveedor(){
		return proveedorService.getProveedores();
	}
	
	@GetMapping("/proveedor/{id}")
	public ResponseEntity<?> getMaterial(@PathVariable long id){
		Proveedor proveedor = null;
		Map<String,Object> response = new HashMap<String, Object>();
		
		try{
			proveedor = this.proveedorService.getProveedor(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al acceder a la BD con el ID del proveedor.");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(proveedor == null) {
			response.put("mensaje", "Id no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}
	
	@PostMapping("/proveedor")
	public ResponseEntity<?> createCliente(@RequestBody Proveedor proveedor){
	Proveedor proveedorNew = null;
	Map<String, Object> response = new HashMap<String, Object>();
	
	try {
		proveedorNew = this.proveedorService.saveProveedor(proveedor);
	} catch (DataAccessException e) {
		response.put("mensaje", "Error al dar de alta el proveedor en la BD");
		response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	if(proveedorNew == null) {
		response.put("mensaje", "No pudo darse de alta al proveedor.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Proveedor>(proveedorNew, HttpStatus.OK);
	}
	
	@PutMapping("/proveedor/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Proveedor proveedor){
		Proveedor proveedorUpdate = null;
		Proveedor proveedorNew    = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			proveedorUpdate = this.proveedorService.getProveedor(id);
			proveedorUpdate.setNombre(proveedor.getNombre());
			proveedorUpdate.setTelefono(proveedor.getTelefono());
			proveedorUpdate.setCelular(proveedor.getCelular());
			proveedorUpdate.setDireccion(proveedor.getDireccion());
			proveedorUpdate.setMateriales(proveedor.getMateriales());
			proveedorNew = this.proveedorService.saveProveedor(proveedorUpdate);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el proveedor en la BD");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Proveedor>(proveedorNew, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/proveedor/{id}")
	public ResponseEntity<?> deleteMaterial(@PathVariable Long id){
		Proveedor proveedor = null;
		Map<String,Object> response = new HashMap<String,Object>();
		
		try {
			proveedor = this.proveedorService.getProveedor(id);
			this.proveedorService.deleteProveedor(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause());
			response.put("mensaje", "Error al eliminar el proveedor de la BD.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(proveedor == null) {
			response.put("mensaje", "El Id del proveedor no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Proveedor eliminado con éxito.");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

}
