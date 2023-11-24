package mx.edu.uacm.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import jakarta.validation.Valid;
import mx.edu.uacm.app.models.entity.Estudiante;
import mx.edu.uacm.app.models.service.IEstudianteService;

@Controller
@SessionAttributes("estudiante")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	
	
	@RequestMapping(value="/listar2", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Estudiantes");
		model.addAttribute("estudiantes", estudianteService.findAll());
		return "listar2";
	}
	
	@RequestMapping(value = "/form2", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Estudiante estudiante = new Estudiante();
		model.put("titulo", "Formulario Estudiante");
		model.put("estudiante", estudiante);
		return "form2";
	}
	
	@RequestMapping(value = "/form2/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Estudiante estudiante = null;
		if(id > 0) {
			estudiante= estudianteService.findOne(id);
		}else {
			return "redirect:/listar2";
		}
		
		model.put("titulo", "Formulario Editar - Estudiante");
		model.put("estudiante", estudiante);
		
		return "form2";
	}
	
	@RequestMapping(value = "/form2", method = RequestMethod.POST)
	public String guardar(@Valid Estudiante estudiante, BindingResult result, Model model, SessionStatus status) {
		// Validar cliente primero va @Valid y en result guardara los errores en caso de que existan
		if(result.hasErrors()) {
			model.addAttribute("titulo", "El formulario tiene errores");
			return "form2";
		}
		estudianteService.save(estudiante);
		status.setComplete();
		return "redirect:listar2";
	}
	
	@RequestMapping(value="/eliminarEstudiante/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			estudianteService.delete(id);
		}
		return "redirect:/listar2";
	}
	
}
