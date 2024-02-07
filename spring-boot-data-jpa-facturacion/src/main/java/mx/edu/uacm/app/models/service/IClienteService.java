package mx.edu.uacm.app.models.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.edu.uacm.app.models.entity.Cliente;
import mx.edu.uacm.app.models.entity.Factura;
import mx.edu.uacm.app.models.entity.Producto;

public interface IClienteService {
	
	public Cliente findOne(Long id);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save(Cliente cliente);
	
	public void delete(Long id);

	public List<Producto> findByName(String term);
	
	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
	
	public Factura findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWhiteItemFacturaWithProducto(Long id);
	
	public Cliente fetchByIdWithFacturas(Long id);
	
}
