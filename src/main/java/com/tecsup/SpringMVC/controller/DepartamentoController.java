package com.tecsup.SpringMVC.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.SpringMVC.model.Departamento;
import com.tecsup.SpringMVC.services.DepartamentoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DepartamentoController {

	private static final Logger logger = LoggerFactory.getLogger(DepartamentoController.class);

	@Autowired
	private DepartamentoService departamentoService;

	
	@GetMapping("/admin/depart/list")
	public String list(@ModelAttribute("SpringWeb") Departamento departamento, ModelMap model) {

		try {
			model.addAttribute("departments", departamentoService.findAll());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/depart/list";
	}

	@GetMapping("/admin/depart/{action}/{department_id}")
	public ModelAndView form(@PathVariable String action, @PathVariable int department_id, ModelMap model) {

		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			Departamento departamento = departamentoService.findById(department_id);
			logger.info(departamento.toString());
			modelAndView = new ModelAndView("admin/depart/" + action, "command", departamento);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/depart/" + action, "command", new Departamento());
		}

		return modelAndView;
	}

	@PostMapping("/admin/depart/editsave")
	public ModelAndView editsave(@ModelAttribute("SpringWeb") Departamento departamento, ModelMap model) {
	
		ModelAndView modelAndView = null;
		logger.info("editsave() = "+departamento.toString());
		try {
			departamentoService.update(departamento.getDepartmentId(), departamento.getName(), departamento.getDescription(), departamento.getCity());
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		}

		return modelAndView;
	}

	@PostMapping("/admin/depart/delete")
	public ModelAndView delete(@ModelAttribute("SpringWeb") Departamento departamento, ModelMap model) {

		ModelAndView modelAndView = null;

		try {
			departamentoService.delete(departamento.getDepartmentId());
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		}

		return modelAndView;
	}

	@GetMapping("/admin/depart/createform")
	public ModelAndView createform() {
		logger.info( "createform() ");
		Departamento departamento = new Departamento();

		ModelAndView modelAndView = new ModelAndView("admin/depart/createform", "command", departamento);

		return modelAndView;
	}

	@PostMapping("/admin/depart/create")
	public ModelAndView createdpto(@ModelAttribute("SpringWeb") Departamento departamento, ModelMap model) {
		logger.info( "create() ");
		ModelAndView modelAndView = null;

		try {
			
			int dptoId = departamentoService.create(departamento.getName(), departamento.getDescription(), departamento.getCity());
			logger.info( "new Department ID = " + dptoId);
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("redirect:/admin/depart/list");
		}

		return modelAndView;
	}
}
