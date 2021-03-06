package com.nestor.electromecanica.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "proveedores")
@Data
public class Proveedor implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String celular;
	private String telefono;
	private String direccion;
	@OneToMany(fetch=FetchType.LAZY, orphanRemoval =true, cascade=CascadeType.ALL, mappedBy="proveedor")
	private List<Material> materiales;
	
	public Proveedor() {
		this.materiales = new ArrayList<Material>();
	}
	
	
}
