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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.electromecanica.entity.Material;
import com.nestor.electromecanica.service.IMaterialServiceImpl;

@RestController
@RequestMapping("/material")
public class MaterialController {
	
	@Autowired
	private IMaterialServiceImpl MaterialService;
	
	@GetMapping("/material")
	public List<Material> getMateriales(){
		return this.MaterialService.getMateriales();
	}
	
	@GetMapping("/material/{id}")
	public ResponseEntity<?> getMaterial(@PathVariable long id){
		Material material = null;
		Map<String,Object> response = new HashMap<String, Object>();
		
		try{
			material = this.MaterialService.getMaterial(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al acceder a la BD con el ID del material.");
			response.put("error", e.getMostSpecificCause() + " " + e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(material == null) {
			response.put("mensaje", "Id no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<Material>(material, HttpStatus.OK);
	}
	
	@DeleteMapping("/material/{id}")
	public ResponseEntity<?> deleteMaterial(@PathVariable Long id){
		Material material = null;
		Map<String,Object> response = new HashMap<String,Object>();
		
		try {
			material = this.MaterialService.getMaterial(id);
			this.MaterialService.deleteMaterial(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + " " + e.getMostSpecificCause());
			response.put("mensaje", "Error al eliminar el material de la BD.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(material == null) {
			response.put("mensaje", "El Id del material no existe.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Material eliminado con éxtio.");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/material/{id}")
	public ResponseEntity<?> updateMaterial(@PathVariable Long id, Material material){
		
		Material materialNew = null;
		Material materialUpdated = null;
		Map<String, Object> response = new HashMap<String,Object>();
		
		try {
			materialNew = this.MaterialService.getMaterial(id);
			materialNew.setNombre(material.getUnidadMedida());
			materialNew.setCantidad(material.getCantidad());
			materialNew.setPrecio(material.getPrecio());
			materialNew.setProveedor(material.getProveedor());;
			materialUpdated = this.MaterialService.saveMaterial(materialNew);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el material en la BD");
			response.put("error",  e.getMostSpecificCause() +" "+e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Material>(materialUpdated,HttpStatus.OK);
	}
	

}
