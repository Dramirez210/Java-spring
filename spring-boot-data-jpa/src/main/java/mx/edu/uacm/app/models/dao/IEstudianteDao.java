package mx.edu.uacm.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.app.models.entity.Estudiante;

// Ya es un componente por extender de CrudRepository
public interface IEstudianteDao extends CrudRepository<Estudiante, Long> {
	//Aqui metemos consultas personalizadas
}
