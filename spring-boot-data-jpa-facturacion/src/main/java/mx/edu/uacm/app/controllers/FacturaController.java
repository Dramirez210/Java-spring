package mx.edu.uacm.app.controllers;

import java.util.List;
import java.util.Map;

/*import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import mx.edu.uacm.app.models.entity.Cliente;
import mx.edu.uacm.app.models.entity.Factura;
import mx.edu.uacm.app.models.entity.ItemFactura;
import mx.edu.uacm.app.models.entity.Producto;
import mx.edu.uacm.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura") //Sesion para guardar el formulario hasta que se procesa, junto con cliente
public class FacturaController {

	@Autowired
	private IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, // se puede omitir si se llaman igual
			Map<String, Object> model,
			RedirectAttributes flash) { 
			//Factura factura = clienteService.findFacturaById(id); //id del pathvariable
			Factura factura = clienteService.fetchFacturaByIdWithClienteWhiteItemFacturaWithProducto(id); //consulta mejorada
			if(factura == null) {
				flash.addAttribute("error", "La factura no existe.");
				return "redirect:/listar";
			}
			model.put("titulo", "Factura: ".concat(factura.getDescripcion()));
			model.put("factura", factura);
			
		return "factura/ver";
	}
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value="clienteId") Long clienteId, 
			Map<String, Object> model, 
			RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		
		model.put("factura", factura);
		model.put("titulo", "Crear factura");
		
		return "factura/form";
	}
	
	@GetMapping(value="/cargar-productos/{term}", produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){ //suprimir vista de thymeleaf --> salida a Json
		return clienteService.findByName(term);
	}
	
	@PostMapping("/form")
	public String guardar (@Valid Factura factura,
			BindingResult result, // Errores
			Model model, // pasar datos a la vista
			@RequestParam(name="item_id[]", required = false) Long[] itemId,
			@RequestParam(name="cantidad[]", required = false) Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "factura/form";
		}
		
		if(itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: La factura NO puede estar vacia.");
			return "factura/form";
		}
		
		for(int i = 0; i< itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);
			
			log.info("ID:::"+ itemId[i].toString());
			log.info("ID:::"+ itemId[i]);
			log.info("Cantidad:::"+ cantidad[i].toString());
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		flash.addFlashAttribute("success", "Factura creada con éxito!");
		return "redirect:/ver/" + factura.getCliente().getId();
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id,
			RedirectAttributes flash) {
		Factura factura = clienteService.findFacturaById(id);
		
		if(factura != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada.");
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		flash.addAttribute("error", "La factura no existe.");
		return "redirect:/listar";
	}
	
}
