package com.tecsup.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.SpringMVC.model.Departamento;

public class DepartamentoMapper implements RowMapper<Departamento>{

	public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Departamento departamento = new Departamento();
		departamento.setDepartmentId(rs.getInt("department_id"));
		departamento.setName(rs.getString("name"));
		departamento.setDescription(rs.getString("description"));
		departamento.setCity(rs.getString("city"));
		return departamento;
	}
}
