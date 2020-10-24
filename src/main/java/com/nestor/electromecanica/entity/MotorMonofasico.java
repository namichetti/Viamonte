package com.nestor.electromecanica.entity;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("motor_mosofasico")
public class MotorMonofasico extends Motor {
	
	private static final long serialVersionUID = 1L;
	@Column(name="vueltas_bobina_arranque")
	private Long vueltasBobinaArranque;
	@Column(name="seccion_cobre_bobina_arranque")
	private String seccionCobreBobinaArranque;
	@Column(name="vueltas_bobina_trabajo")
	private Long vueltasBobinaTrabajo;
	@Column(name="seccion_cobre_bobina_trabajo")
	private String seccionCobreBobinaTrabajo;
}
