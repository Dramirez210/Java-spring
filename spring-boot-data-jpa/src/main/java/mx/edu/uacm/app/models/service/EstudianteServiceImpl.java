package mx.edu.uacm.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uacm.app.models.dao.IEstudianteDao;
import mx.edu.uacm.app.models.entity.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired 
	private IEstudianteDao estudianteDao;
	
	@Override
	@Transactional(readOnly=true)
	public Estudiante findOne(Long id) {
		return estudianteDao.findById(id).orElse(null); //findById por interfaz crudRepository
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Estudiante> findAll() {
		return (List<Estudiante>) estudianteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Estudiante cliente) {
		estudianteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		estudianteDao.deleteById(id); //deleteById por interfaz crudRepository
	} 

}
