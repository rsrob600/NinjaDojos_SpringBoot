package com.codingdojo.ninjadojos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.ninjadojos.models.Dojo;
import com.codingdojo.ninjadojos.models.Ninja;
import com.codingdojo.ninjadojos.services.DojoService;
import com.codingdojo.ninjadojos.services.NinjaService;

@Controller
public class NinjaDojosController {
	private final DojoService dojoService;
	private final NinjaService ninjaService;
	
	
	public NinjaDojosController(DojoService dojoService, NinjaService ninjaService) {
		this.dojoService = dojoService;
		this.ninjaService = ninjaService;
	}
	
	// display add new dojo page
	@RequestMapping("/dojos/new")
	public String newDojo(@ModelAttribute("dojo")Dojo dojo) {
		return "view/newDojo.jsp";
	}
	
	// Save new dojo page
	@PostMapping("/addDojo")
	public String addDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newDojo.jsp";
		} else {
			dojoService.addDojo(dojo);
			return "redirect:/dojos/new";
		}
	}
	
	// display add new ninja page
	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja")Ninja ninja, Model model) {
		List<Dojo> allDojos = dojoService.allDojos();
		model.addAttribute("dojos", allDojos);
		return "view/newNinja.jsp";
	}
	
	// Save new dojo page
	@PostMapping("/addNinja")
	public String addNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("errors", result.getAllErrors());
			return "view/newNinja.jsp";
		} else {
			ninjaService.addNinja(ninja);
			return "redirect:/ninjas/new";
		}
	}
	
	
	// View All ninjas by dojo
	@RequestMapping("/dojos/{id}")
	public String ninjasViewAll(Model model, @PathVariable("id") Long id) {
		Dojo dojo = dojoService.findDojo(id);
		model.addAttribute("dojo", dojo);
		model.addAttribute("ninjas", dojo.getNinjas());
		return "view/allNinjas.jsp";
	}
	

	
}
