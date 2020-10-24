package com.nestor.electromecanica.dao;

import org.springframework.data.repository.CrudRepository;

import com.nestor.electromecanica.entity.Material;

public interface MaterialDao  extends CrudRepository<Material, Long>{

}
