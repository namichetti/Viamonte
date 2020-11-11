package com.nestor.electromecanica.service;

import java.util.List;

import com.nestor.electromecanica.entity.Motor;

public interface IMotorService {

	public List<Motor> getMotores();
	public Motor getMotor(Long id);
	public void deleteMotor(Long id);
	public Motor saveMotor(Motor motor);
}
