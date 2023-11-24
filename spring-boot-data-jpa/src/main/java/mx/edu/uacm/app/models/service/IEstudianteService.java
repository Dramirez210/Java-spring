package mx.edu.uacm.app.models.service;

import java.util.List;

import mx.edu.uacm.app.models.entity.Estudiante;

public interface IEstudianteService {
	
	public Estudiante findOne(Long id);
	
	public List<Estudiante> findAll();
	
	public void save(Estudiante cliente);
	
	public void delete(Long id);
}
