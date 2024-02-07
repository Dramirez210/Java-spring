package mx.edu.uacm.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uacm.app.models.dao.IClienteDao;
import mx.edu.uacm.app.models.dao.IFacturaDao;
import mx.edu.uacm.app.models.dao.IProductoDao;
import mx.edu.uacm.app.models.entity.Cliente;
import mx.edu.uacm.app.models.entity.Factura;
import mx.edu.uacm.app.models.entity.Producto;

@Service //unico acceso a distintos dao o repository
public class ClienteServiceImpl implements IClienteService{ //patron facade Clase service

	//Aqui van todos los dao
	
	@Autowired 
	private IClienteDao clienteDao;
	@Autowired
	private IProductoDao productoDao;
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly=true) //consulta Select no modifica nada
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	/*@Override
	@Transactional(readOnly=true)
	public List<Producto> findByName(String term) {
		return productoDao.findByName(term);
	} */
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findByName(String term) {
		return productoDao.findByNameLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly=true) 
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true) 
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null); // en caso de no estar presente el entity
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id); // spring boot 2
		//facturaDao.delete(id); // despues de spring 2
	}

	@Override
	@Transactional(readOnly=true) 
	public Factura fetchFacturaByIdWithClienteWhiteItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWhiteItemFacturaWithProducto(id);
	}

	@Override
	@Transactional(readOnly=true) 
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteDao.fetchByIdWithFacturas(id);
	}

	
}
