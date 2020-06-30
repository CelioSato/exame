package com.celiosato.exames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.celiosato.exames.domain.Eritrograma;



@Repository

public interface EritrogramaRepository extends JpaRepository<Eritrograma, Integer>{

	//Retornar o exame correto do paciente
	@Query("SELECT obj FROM Eritrograma obj WHERE obj.usuario.id = :id")
	Optional<Eritrograma> findExameDoUsuario(@Param("id") Integer id);
	
	//Listar todos os exames do mesmo paciente
	@Query("SELECT obj FROM Eritrograma obj WHERE obj.usuario.id = :id")
	List<Eritrograma> findByExames(@Param("id") Integer id);
	
}