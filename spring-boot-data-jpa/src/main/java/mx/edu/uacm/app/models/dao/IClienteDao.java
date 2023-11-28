package mx.edu.uacm.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import mx.edu.uacm.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>, CrudRepository<Cliente, Long> {

	
	
}
