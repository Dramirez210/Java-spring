package mx.edu.uacm.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uacm.app.models.dao.IClienteDao;
import mx.edu.uacm.app.models.entity.Cliente;

@Service //unico acceso a distintos dao o repository
public class ClienteServiceImpl implements IClienteService{ //patron facade Clase service

	//Aqui van todos los dao
	
	@Autowired 
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly=true)
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

}
