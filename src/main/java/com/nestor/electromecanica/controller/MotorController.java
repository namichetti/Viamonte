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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.electromecanica.entity.Motor;
import com.nestor.electromecanica.service.IMotorService;


@RestController
@RequestMapping("/api")
public class MotorController {
	
	@Autowired
	private IMotorService motorService;
	
	@GetMapping("/motor")
	public List<Motor> getmotor(){
		return motorService.getMotores();
	}
	
	@GetMapping("/motor/{id}")
	public ResponseEntity<?> getMotor(@PathVariable Long id){
		Motor motor = null;
		Map<String, Object> response = new HashMap<String,Object>();
		try {			
			motor = this.motorService.getMotor(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() +" "+e.getLocalizedMessage());
			response.put("mensaje", "Error al buscar el motor en la BD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		if(motor == null) {
			response.put("mensaje", "Error al buscar el motor por ID");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Motor>(motor,HttpStatus.OK);
	}
	
	@PostMapping("/motor")
	public ResponseEntity<?> saveMotor(@RequestBody Motor motor){
		Motor motorNew = null;
		Map<String, Object> response = new HashMap<String,Object>();
		
		try {
			motorNew = this.motorService.saveMotor(motor);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() +" "+e.getLocalizedMessage());
			response.put("mensaje", "Error al buscar el motor en la BD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Motor>(motorNew,HttpStatus.OK);
	}
	
	@DeleteMapping("/motor/{id}")
	public ResponseEntity<?> deleteMaterial(@PathVariable Long id){
		Motor motor = null;
		Map<String,Object> response = new HashMap<String,Object>();
		
		try {
			motor = this.motorService.getMotor(id);
			this.motorService.deleteMotor(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause());
			response.put("mensaje", "Error al eliminar el motor de la BD.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(motor == null) {
			response.put("mensaje", "El Id del motor no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Motor eliminado con éxito.");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

}
