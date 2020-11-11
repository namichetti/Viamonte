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

import com.nestor.electromecanica.entity.Cliente;
import com.nestor.electromecanica.service.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/cliente")
	public List<Cliente> getClientes(){
		return this.clienteService.getClientes();
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Long id){
		Map<String, Object> response = new HashMap<String, Object>();
		Cliente cliente = null;
		try {
			cliente = this.clienteService.getCliente(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al buscar el cliente a la BD.");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(cliente == null) {
			response.put("mensaje", "No existe cliente.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<?> createCliente(@RequestBody Cliente cliente){
	Cliente clienteNew = null;
	Map<String, Object> response = new HashMap<String, Object>();
	
	try {
		clienteNew = this.clienteService.saveCliente(cliente);
	} catch (DataAccessException e) {
		response.put("mensaje", "Error al dar de alta el cliente en la BD");
		response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	if(clienteNew == null) {
		response.put("mensaje", "No pudo darse de alta al cliente.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Cliente>(clienteNew, HttpStatus.OK);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		Cliente clienteUpdate = null;
		Cliente clienteNew    = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			clienteUpdate = this.clienteService.getCliente(id);
			clienteUpdate.setNombre(cliente.getNombre());
			clienteUpdate.setApellido(cliente.getApellido());
			clienteUpdate.setCelular(cliente.getCelular());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setMotores(cliente.getMotores());
			clienteNew = this.clienteService.saveCliente(clienteUpdate);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la BD");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Cliente>(clienteNew, HttpStatus.OK);
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			this.clienteService.deleteCliente(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error eliminar el cliente de la BD");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		 response.put("mensaje","Cliente elimado con éxtio.");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}
