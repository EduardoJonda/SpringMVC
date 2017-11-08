package com.tecsup.SpringMVC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.SpringMVC.dao.DepartamentoDAO;
import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.model.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoDAO departamentoDAO;

	@Override
	public int create(String name, String description, String city) throws DAOException {
		
		int departamento_id = departamentoDAO.create(name, description, city);

		return departamento_id;
	}

	@Override
	public void delete(int id) throws DAOException {
		
		departamentoDAO.delete(id);

	}

	@Override
	public void update(int id, String name, String description, String city) throws DAOException {
		
		departamentoDAO.update(id, name, description, city);
		
	}

	@Override
	public Departamento findById(int id) throws DAOException, EmptyResultException {
		
		Departamento departamento = departamentoDAO.findById(id);

		return departamento;
	}

	@Override
	public Departamento findByName(String name) throws DAOException, EmptyResultException {
		
		Departamento departamento = departamentoDAO.findByName(name);

		return departamento;
	}

	@Override
	public List<Departamento> findAll() throws DAOException, EmptyResultException {
	
		List<Departamento> departamento = departamentoDAO.findAll();

		return departamento;
	}

}

