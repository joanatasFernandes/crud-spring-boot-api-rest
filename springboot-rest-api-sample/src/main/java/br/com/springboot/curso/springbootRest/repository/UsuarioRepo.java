package br.com.springboot.curso.springbootRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso.springbootRest.model.Usuario;


@Repository
public interface UsuarioRepo extends  JpaRepository<Usuario, Long>{	
	
	@Query("select name from Usuario name where name.name like %?1%")
	List<Usuario> findByName(String name);
}