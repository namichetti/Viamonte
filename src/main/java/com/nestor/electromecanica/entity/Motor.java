package com.nestor.electromecanica.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "motores")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Data
public class Motor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String marca;
	@Column(name="revoluciones_por_minuto")
	private String revolucionesPorMinuto;
	private String hp;
	private String voltios;
	@Column(name="largo_nucleo")
	private String largoNucleo;
	@Column(name="largo_bobina")
	private String largoBobina;
	@Column(name="tipo_conexion")
	private String tipoConexion;
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="motor_id")
	private List<Reparacion> reparaciones;
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="motor_id")
	private List<Material> materiales;
	
	public Motor() {
		this.materiales   = new ArrayList<Material>();
		this.reparaciones = new ArrayList<Reparacion>();
	}
}
