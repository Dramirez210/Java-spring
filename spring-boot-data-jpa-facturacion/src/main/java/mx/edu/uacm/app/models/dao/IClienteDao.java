package mx.edu.uacm.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import mx.edu.uacm.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>, CrudRepository<Cliente, Long> {

	@Query("SELECT cliente FROM Cliente cliente LEFT JOIN FETCH cliente.facturas factura WHERE cliente.id = ?1")
	public Cliente fetchByIdWithFacturas(Long id);
	
}
