package com.nestor.electromecanica.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "motores")
@Data
@NoArgsConstructor
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
	
	
	
}
