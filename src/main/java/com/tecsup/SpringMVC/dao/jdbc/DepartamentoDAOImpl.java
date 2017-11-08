package com.tecsup.SpringMVC.dao.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tecsup.SpringMVC.mapper.DepartamentoMapper;
import com.tecsup.SpringMVC.dao.DepartamentoDAO;
import com.tecsup.SpringMVC.exception.DAOException;
import com.tecsup.SpringMVC.exception.EmptyResultException;
import com.tecsup.SpringMVC.model.Departamento;

@Repository
public class DepartamentoDAOImpl implements DepartamentoDAO {

	private static final Logger logger = LoggerFactory.getLogger(DepartamentoDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int create(String name, String description, String city) throws DAOException {
		
		String query = "INSERT INTO departments (name, description, city)  VALUES ( ?,?,? )";

		Object[] params = new Object[] { name, description, city };

		Departamento departamento = null;
		
		try {
		 
			jdbcTemplate.update(query, params);
			 
			departamento = this.findByName(name);

		} catch (EmptyResultException e) {
			logger.info("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
		
		return departamento != null ? departamento.getDepartmentId() : -1;
	}

	@Override
	public void delete(int id) throws DAOException {
		
		String query = "DELETE FROM  departments WHERE department_id = ? ";

		Object[] params = new Object[] { id };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(int id, String name, String description, String city) throws DAOException {
		logger.info("name = " + name);
		
		String query = "UPDATE departments SET description =?, city =?  WHERE department_id = ?";

		Object[] params = new Object[] { description, city, id };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Departamento findByName(String name) throws DAOException, EmptyResultException {
		 
		String query = "SELECT department_id, name, description, city"
				+ " FROM departments WHERE upper(name) like upper(?) ";

		Object[] params = new Object[] { "%" + name + "%" };

		try {

			Departamento departamento = jdbcTemplate.queryForObject(query, params, new DepartamentoMapper());
			//
			return departamento;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Departamento findById(int id) throws DAOException, EmptyResultException {
		 
		String query = "SELECT department_id, name, description, city "
				+ " FROM departments WHERE department_id = ? ";

		Object[] params = new Object[] { id };

		try {

			Departamento departamento = jdbcTemplate.queryForObject(query, params, new DepartamentoMapper());
			//
			return departamento;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public List<Departamento> findAll() throws DAOException, EmptyResultException {
		 
		String query = "SELECT department_id, name, description, city "+" FROM departments ";

		try {

			List<Departamento> departamento = jdbcTemplate.query(query, new DepartamentoMapper());
	 
			return departamento;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
}
