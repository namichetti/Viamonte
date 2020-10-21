package com.nestor.electromecanica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class MotorTrifasico extends Motor{

	private static final long serialVersionUID = 1L;
	@Column(name="vueltas_bobina")
	private Float vueltasBobina;
	@Column(name="ranura_llena")
	private Boolean ranuraLlena;
	@Column(name="cantidad_ranuras")
	private Integer cantidadRanuras;
	@Column(name="seccion_alambre")
	private String seccionAlambre;

}
