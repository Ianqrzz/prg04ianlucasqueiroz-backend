package br.com.ifba.logacesso.service;

import br.com.ifba.logacesso.entity.LogAcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogAcessoIService {

    LogAcesso save(LogAcesso logAcesso);

    Page<LogAcesso> findAll(Pageable pageable);

    LogAcesso update(Long id, LogAcesso logAcesso);

    void delete(Long id);
}