package com.eventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventos.model.Convidado;
import com.eventos.model.Evento;
import com.eventos.repository.ConvidadoRepository;
import com.eventos.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er; 
	
	@Autowired
	private ConvidadoRepository cr; 
	
	
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
	public String form(Evento evento) {
		
		er.save(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("evento/eventos");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
		
		
	}
	
	@RequestMapping(value="/detalhesEvento/{codigo}", method=RequestMethod.GET )
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/detalhesEvento/{codigo}", method=RequestMethod.POST )
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, 
			
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhesEvento/{codigo}";
		}
		
		
		
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/detalhesEvento/{codigo}";
		
	}
	
	@RequestMapping("/deletarEvento/{codigo}")
	public String deletarEvento(@PathVariable("codigo") long codigo) {
		Evento evento =  er.findByCodigo(codigo);
		er.delete(evento);
		
		return "redirect:/eventos";
	}
	
	
	
	
	


}
