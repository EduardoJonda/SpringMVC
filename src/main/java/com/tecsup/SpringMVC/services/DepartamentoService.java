package com.tecsup.SpringMVC.services;

import java.util.List;

import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.model.Departamento;

public interface DepartamentoService {

	int create(String name, String description, String city) throws DAOException;

	void delete(int id) throws DAOException;

	void update(int id, String name, String description, String city) throws DAOException;

	Departamento findById(int id) throws DAOException, EmptyResultException;

	Departamento findByName(String name) throws DAOException, EmptyResultException;

	List<Departamento> findAll() throws DAOException, EmptyResultException;

}
