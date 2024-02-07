package mx.edu.uacm.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%") //A nivel de objeto
	public List<Producto> findByName(String term);
	//@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%") // funciona igual
	@Query("select p from Producto p where upper(p.nombre) like upper(concat('%', ?1, '%'))")
	public List<Producto> findByNameLikeIgnoreCase(String term);
}
