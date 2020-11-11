package com.nestor.electromecanica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nestor.electromecanica.dao.MaterialDao;
import com.nestor.electromecanica.entity.Material;

@Service
public class MaterialServiceImpl implements IMaterialServiceImpl{
	
	@Autowired
	private MaterialDao materialDao;

	@Override
	@Transactional(readOnly=true)
	public List<Material> getMateriales() {
		return (List<Material>)materialDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Material getMaterial(Long id) {
		return materialDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteMaterial(Long id) {
		materialDao.deleteById(id);
	}

	@Override
	@Transactional
	public Material saveMaterial(Material material) {
		return materialDao.save(material);
	}

}
