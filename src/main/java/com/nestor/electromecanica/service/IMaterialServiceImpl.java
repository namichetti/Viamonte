package com.nestor.electromecanica.service;

import java.util.List;
import com.nestor.electromecanica.entity.Material;

public interface IMaterialServiceImpl {

	public List<Material> getMateriales();
	public Material getMaterial(Long id);
	public void deleteMaterial(Long id);
	public Material saveMaterial(Material cliente);
}
