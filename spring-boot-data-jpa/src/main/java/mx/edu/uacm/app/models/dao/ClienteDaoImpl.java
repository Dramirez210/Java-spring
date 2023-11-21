package mx.edu.uacm.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mx.edu.uacm.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) { //editar y eliminar
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		}else {
			em.persist(cliente); //guardar cliente
		}
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
