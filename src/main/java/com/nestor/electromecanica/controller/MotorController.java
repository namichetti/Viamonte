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

import com.nestor.electromecanica.entity.Motor;
import com.nestor.electromecanica.entity.MotorMonofasico;
import com.nestor.electromecanica.entity.MotorTrifasico;
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
	
	@PostMapping("/motor_monofasico")
	public ResponseEntity<?> saveMotor(@RequestBody MotorMonofasico motorMonofasico){
		MotorMonofasico motorMonofasicoNew = null;
		Map<String, Object> response = new HashMap<String,Object>();
		
		try {
			motorMonofasicoNew = (MotorMonofasico) this.motorService.saveMotor(motorMonofasico);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() +" "+e.getLocalizedMessage());
			response.put("mensaje", "Error al buscar el motor en la BD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Motor>(motorMonofasicoNew,HttpStatus.OK);
	}
	
	@PostMapping("/motor_trifasico")
	public ResponseEntity<?> saveMotorTrifasico(@RequestBody MotorTrifasico motorTrifasico){
		MotorTrifasico  motorTrifasicoNew = null;
		Map<String, Object> response = new HashMap<String,Object>();
		
		try {
			motorTrifasicoNew  = (MotorTrifasico) this.motorService.saveMotor(motorTrifasico);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() +" "+e.getLocalizedMessage());
			response.put("mensaje", "Error al buscar el motor en la BD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
			return new ResponseEntity<Motor>(motorTrifasicoNew,HttpStatus.OK);
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
	
	@PutMapping("/motor_trifasico/{id}")
	public ResponseEntity<?> updateMotorTrifasico(@PathVariable Long id, @RequestBody MotorTrifasico motorTrifasico){
		MotorTrifasico  MotorTrifasicoNew  = null;
		MotorTrifasico  MotorTrifasicoUpdated  = null;
		Map<String,Object> response = new HashMap<String,Object>();

		try {
			
			MotorTrifasicoNew = (MotorTrifasico) this.motorService.getMotor(id);
			MotorTrifasicoNew.setCliente(motorTrifasico.getCliente());
			MotorTrifasicoNew.setHp(motorTrifasico.getHp());
			MotorTrifasicoNew.setLargoBobina(motorTrifasico.getLargoBobina());
			MotorTrifasicoNew.setLargoNucleo(motorTrifasico.getLargoNucleo());
			MotorTrifasicoNew.setMarca(motorTrifasico.getMarca());
			MotorTrifasicoNew.setMateriales(motorTrifasico.getMateriales());
			MotorTrifasicoNew.setNombre(motorTrifasico.getNombre());
			MotorTrifasicoNew.setReparaciones(motorTrifasico.getReparaciones());
			MotorTrifasicoNew.setRevolucionesPorMinuto(motorTrifasico.getRevolucionesPorMinuto());
			MotorTrifasicoNew.setTipoConexion(motorTrifasico.getTipoConexion());
			MotorTrifasicoNew.setVoltios(motorTrifasico.getVoltios());
			MotorTrifasicoNew.setRanuraLlena(motorTrifasico.getRanuraLlena());
			MotorTrifasicoNew.setSeccionAlambre(motorTrifasico.getSeccionAlambre());
			MotorTrifasicoNew.setVueltasBobina(motorTrifasico.getVueltasBobina());
			MotorTrifasicoNew.setCantidadRanuras(motorTrifasico.getCantidadRanuras());
			MotorTrifasicoUpdated = (MotorTrifasico) this.motorService.saveMotor(MotorTrifasicoNew);
			
			
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause());
			response.put("mensaje", "Error al eliminar el motor de la BD.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Motor>(MotorTrifasicoUpdated,HttpStatus.OK);		
				
	}
	
	@PutMapping("/motor_monofasico/{id}")
	public ResponseEntity<?> updateMotorMonofasico(@PathVariable Long id, @RequestBody MotorMonofasico motorMonofasico){
		MotorMonofasico motorMonofasicoNew = null;
		MotorMonofasico motorMonofasicoUpdated = null;
		Map<String,Object> response = new HashMap<String,Object>();

		try {
			
			motorMonofasicoNew = (MotorMonofasico) this.motorService.getMotor(id);
			motorMonofasicoNew.setCliente(motorMonofasico.getCliente());
			motorMonofasicoNew.setHp(motorMonofasico.getHp());
			motorMonofasicoNew.setLargoBobina(motorMonofasico.getLargoBobina());
			motorMonofasicoNew.setLargoNucleo(motorMonofasico.getLargoNucleo());
			motorMonofasicoNew.setMarca(motorMonofasico.getMarca());
			motorMonofasicoNew.setMateriales(motorMonofasico.getMateriales());
			motorMonofasicoNew.setNombre(motorMonofasico.getNombre());
			motorMonofasicoNew.setReparaciones(motorMonofasico.getReparaciones());
			motorMonofasicoNew.setRevolucionesPorMinuto(motorMonofasico.getRevolucionesPorMinuto());
			motorMonofasicoNew.setTipoConexion(motorMonofasico.getTipoConexion());
			motorMonofasicoNew.setVoltios(motorMonofasico.getVoltios());
			motorMonofasicoNew.setSeccionCobreBobinaTrabajo(motorMonofasico.getSeccionCobreBobinaTrabajo());
			motorMonofasicoNew.setSeccionCobreBobinaArranque(motorMonofasico.getSeccionCobreBobinaArranque());
			motorMonofasicoNew.setVueltasBobinaArranque(motorMonofasico.getVueltasBobinaArranque());
			motorMonofasicoNew.setVueltasBobinaTrabajo(motorMonofasico.getVueltasBobinaTrabajo());
			motorMonofasicoUpdated = (MotorMonofasico) this.motorService.saveMotor(motorMonofasicoNew);
			
			
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause());
			response.put("mensaje", "Error al eliminar el motor de la BD.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Motor>(motorMonofasicoUpdated,HttpStatus.OK);		
				
	}
}
