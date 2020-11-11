package com.nestor.electromecanica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestor.electromecanica.dao.MotorDao;
import com.nestor.electromecanica.entity.Motor;

@Service
public class MotorServiceImpl implements IMotorService{
	
	@Autowired
	private MotorDao motorDao;

	@Override
	@Transactional(readOnly=true)
	public List<Motor> getMotores() {
		return (List<Motor>) this.motorDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Motor getMotor(Long id) {
		return this.motorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteMotor(Long id) {
		this.motorDao.deleteById(id);
	}

	@Override
	@Transactional
	public Motor saveMotor(Motor motor) {
		return motorDao.save(motor);
	}

}
