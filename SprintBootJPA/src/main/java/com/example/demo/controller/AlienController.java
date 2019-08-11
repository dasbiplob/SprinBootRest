package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController {

	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "Home.jsp";
	}
	
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		
		repo.save(alien);
		return "Home.jsp";
	}
	
	
	@GetMapping(path= "/aliens")
	public List<Alien> getAliens() {
	//public String getAliens(@RequestParam Integer aid){
		
		//ModelAndView mv = new ModelAndView("showAline.jsp");
		//Alien alien = repo.findById(aid).orElse(new Alien());
		
//		System.out.println(repo.findByTech("java"));
//		System.out.println(repo.findByAidGreaterThan(102));
//		System.out.println(repo.findByTechSorted("java"));
		
		//mv.addObject(alien);
		//return mv;
		return (List<Alien>) repo.findAll();
	}
	
	@RequestMapping("/alien/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid") Integer aid) {
		
		return repo.findById(aid);
	}
	
	
	@PostMapping(path="/alien",  consumes= {"application/json"})
	public Alien postAlien(@RequestBody Alien alien) {
		
		return repo.save(alien);
		
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable("aid") Integer aid) {
		
		Alien a = repo.getOne(aid);
		
		repo.delete(a);
		 return "deleted";
	}
	
	@PutMapping(path="/alien", consumes= {"application/json"})
	public Alien saveOrupdateAlien(@RequestBody Alien alien) {
		
		return repo.save(alien);
	}
}
