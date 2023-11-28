package mx.edu.uacm.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import mx.edu.uacm.app.models.entity.Cliente;
import mx.edu.uacm.app.models.service.IClienteService;
import mx.edu.uacm.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());

		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest); // importar de data.domain

		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		model.addAttribute("titulo", "Listado de Clientes");
		// model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario Cliente");
		model.put("cliente", cliente);
		model.put("btnText", "Crear cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET) // editar y crear
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID no existe!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID no puede ser cero!");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Formulario Editar - Cliente");
		model.put("btnText", "Editar Cliente");

		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		// Validar cliente primero va @Valid y en result guardara los errores en caso de
		// que existan
		if (result.hasErrors()) {
			model.addAttribute("titulo", "El formulario tiene errores");
			return "form";
		}
		if (!foto.isEmpty()) {

			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

			Path rootAbsolutPath = rootPath.toAbsolutePath();

			log.info("rootPath:: " + rootPath);
			log.info("rootAbsolutPath:: " + rootAbsolutPath);

			try {
				/*
				 * byte[] bytes = foto.getBytes(); Path rutaCompleta = Paths.get(rootPath + "//"
				 * + foto.getOriginalFilename()); Files.write(rutaCompleta, bytes);
				 * flash.addFlashAttribute("info", "Carga completada '" +
				 * foto.getOriginalFilename() + "'");
				 * cliente.setFoto(foto.getOriginalFilename());
				 */
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				cliente.setFoto(uniqueFilename);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con exito!" : "Cliente creado con exito!";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito!");
		}
		return "redirect:/listar";
	}

}
