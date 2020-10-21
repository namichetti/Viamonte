package com.nestor.electromecanica.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "ventas")
@Data
@NoArgsConstructor
public class Venta implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="fecha_venta")
	private Date FechaVenta;
	@Column(name="hora_venta")
	private Time HoraVenta;
	private Integer cantidad;
	private Double importeTotal;
	

}
