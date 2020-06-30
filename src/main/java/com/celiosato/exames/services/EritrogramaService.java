package com.celiosato.exames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.celiosato.exames.domain.Eritrograma;
import com.celiosato.exames.domain.Usuario;
import com.celiosato.exames.repositories.EritrogramaRepository;
import com.celiosato.exames.services.exception.EmptyResultException;
import com.celiosato.exames.services.exception.ObjectNotFoundException;


@Service
public class EritrogramaService {

	@Autowired
	private EritrogramaRepository eritrogramaRepository;

	@Autowired
	private UsuarioService usuarioService;

//Buscar um unico exame por id
	public Eritrograma find(Integer id) {
		Optional<Eritrograma> obj = eritrogramaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Exame não encontrado, por favor confira os dados: " + id + ", Tipo: " + Eritrograma.class.getName()));
	}	
	
//Buscar um unico exame por id
	public Eritrograma findExameDoUsuario(Integer id) {
		Optional<Eritrograma> obj = eritrogramaRepository.findExameDoUsuario(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Exame não encontrado, por favor confira os dados: " + id + ", Tipo: " + Eritrograma.class.getName()));
	}

//Listar todos os exames do mesmo paciente
	public List<Eritrograma> findExames(Integer id) {
		return eritrogramaRepository.findByExames(id);
	}

//Listar todos os exames
	public List<Eritrograma> findAll() {
		return eritrogramaRepository.findAll();
	}

//Inserir um exame
	public Eritrograma insert(Eritrograma obj) {
		Usuario newObj = usuarioService.find(obj.getId());
		Eritrograma newEri = new Eritrograma(null, obj.getEritrocitos(), obj.getHemoglobina(), obj.getObs(), newObj);
		obj = eritrogramaRepository.save(newEri);
		return obj;
	}

//Alterar um exame
	public Eritrograma update(Eritrograma obj) {
		Eritrograma newObj = find(obj.getId());
		updateData(newObj, obj);
		return eritrogramaRepository.save(newObj);
	}

//Alterar somente os campos citados abaixo	
	private void updateData(Eritrograma newObj, Eritrograma obj) {
		newObj.setEritrocitos(obj.getEritrocitos());
		newObj.setHemoglobina(obj.getHemoglobina());
		newObj.setObs(obj.getObs());
	}

//Deletar um exame
	public void delete(Integer id) {
		find(id);
		try {
			eritrogramaRepository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException("Não é possivel excluir o exame!");
		}
	}
}