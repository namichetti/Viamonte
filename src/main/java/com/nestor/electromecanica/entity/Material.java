package com.nestor.electromecanica.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "materiales")
@Data
@NoArgsConstructor
public class Material implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Float precio;
	private Integer cantidad;
	@Column(name="unidad_medida")
	private String unidadMedida;
	@ManyToOne(fetch=FetchType.LAZY)
	private Proveedor proveedor;
}
