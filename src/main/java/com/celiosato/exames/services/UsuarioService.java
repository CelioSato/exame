package com.celiosato.exames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celiosato.exames.domain.Usuario;
import com.celiosato.exames.domain.enums.Perfil;
import com.celiosato.exames.domain.enums.TipoUsuario;
import com.celiosato.exames.dto.UsuarioNewDTO;
import com.celiosato.exames.repositories.UsuarioRepository;
import com.celiosato.exames.security.UserSS;
import com.celiosato.exames.services.exception.AuthorizationException;
import com.celiosato.exames.services.exception.DataIntegrityException;
import com.celiosato.exames.services.exception.ObjectNotFoundException;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;


//Buscar um unico usuario (Paciente ou Administrador) por ID
	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if( user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId() ) ) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado, por favor confira os dados: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
//Listar todos os usuarios (Paciente ou Administrador) 
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
//Listar usuarios procurando por nome (Administrador) 
	public List<Usuario> search(String nome) {
		return usuarioRepository.search(nome);
	}

	
//Inserir um usuario (Paciente ou Administrador) 
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return usuarioRepository.save(obj);
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getNomeCompleto(), objDto.getCpf(), passEncoder.encode(objDto.getSenha()), 
				                      objDto.getEmail(), objDto.getTelefone(), TipoUsuario.toEnum(objDto.getTipo()));
		return usuario;
	}

//Alterar um usuario (Paciente ou Administrador) 
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return usuarioRepository.save(newObj);
	}
//Alterar somente os campos citados abaixo (Paciente ou Administrador) 
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNomeCompleto(obj.getNomeCompleto());
		newObj.setEmail(obj.getEmail());
		newObj.setTelefone(obj.getTelefone());
	}

//Deletar um usuario (Paciente ou Administrador) 
	public void delete(Integer id) {
		System.out.println("Rebendo aqui um id PARA DELETAR DO SISTEMA 12345665787687667: " + id);
		find(id);
		try {
			usuarioRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um usuario que possui exames!");
		}	
	}
	
//Buscar Usuario com paginação (Paciente ou Administrador) 
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findAll(pageRequest);	
	}
	
}
