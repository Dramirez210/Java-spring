package mx.edu.uacm.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	
	/*Clase Factura -- JPQL*/
	@Query("SELECT factura FROM Factura factura JOIN FETCH factura.cliente cliente JOIN FETCH factura.items linea JOIN FETCH linea.producto WHERE factura.id = ?1")  
	public Factura fetchByIdWithClienteWhiteItemFacturaWithProducto(Long id);

}
