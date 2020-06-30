package com.celiosato.exames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.celiosato.exames.domain.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly = true)
	Usuario findByEmail(String email);
	@Query("SELECT obj FROM Usuario obj WHERE nome_completo LIKE %:nome%")
			List<Usuario> search(@Param("nome") String nome);

}
