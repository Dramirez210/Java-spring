package mx.edu.uacm.app.models.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.edu.uacm.app.models.entity.Cliente;

public interface IClienteService {
	
	public Cliente findOne(Long id);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save(Cliente cliente);
	
	public void delete(Long id);

}
