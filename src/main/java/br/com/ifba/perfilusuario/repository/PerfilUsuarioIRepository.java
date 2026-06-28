package br.com.ifba.perfilusuario.repository;

import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioIRepository extends JpaRepository<PerfilUsuario, Long> {
}