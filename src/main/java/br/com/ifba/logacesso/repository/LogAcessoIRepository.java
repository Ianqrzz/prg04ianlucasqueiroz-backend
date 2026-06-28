package br.com.ifba.logacesso.repository;

import br.com.ifba.logacesso.entity.LogAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAcessoIRepository extends JpaRepository<LogAcesso, Long> {
}