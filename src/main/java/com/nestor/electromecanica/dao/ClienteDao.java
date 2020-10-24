package com.nestor.electromecanica.dao;

import org.springframework.data.repository.CrudRepository;

import com.nestor.electromecanica.entity.Cliente;

public interface ClienteDao  extends CrudRepository<Cliente, Long>{

}
