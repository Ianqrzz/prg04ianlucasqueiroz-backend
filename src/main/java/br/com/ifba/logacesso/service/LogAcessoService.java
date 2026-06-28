package br.com.ifba.logacesso.service;

import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.logacesso.entity.LogAcesso;
import br.com.ifba.logacesso.repository.LogAcessoIRepository;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioIRepository;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class LogAcessoService implements LogAcessoIService {

    private static final Logger logger = Logger.getLogger(LogAcessoService.class.getName());

    @Autowired
    private LogAcessoIRepository logAcessoIRepository;

    @Autowired
    private UsuarioIRepository usuarioIRepository;

    @Override
    @Transactional
    public LogAcesso save(LogAcesso logAcesso) {
        logger.info("Iniciando o registro do Log de Acesso");

        if (StringUtil.isNullOrEmpty(logAcesso.getAcao())) {
            throw new BusinessException("Ação não preenchida");
        }

        if (logAcesso.getUsuario() == null || logAcesso.getUsuario().getId() == null) {
            throw new BusinessException("Usuário não informado");
        }

        Usuario usuario = usuarioIRepository.findById(logAcesso.getUsuario().getId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        logAcesso.setUsuario(usuario);
        logAcesso.setDataHora(LocalDateTime.now());

        logger.info("Log de Acesso registrado com sucesso");

        return logAcessoIRepository.save(logAcesso);
    }

    @Override
    @Transactional
    public Page<LogAcesso> findAll(Pageable pageable) {
        return logAcessoIRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public LogAcesso update(Long id, LogAcesso logAcesso) {
        LogAcesso logAcessoAtualizado = logAcessoIRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Log de Acesso não encontrado"));

        if (StringUtil.isNullOrEmpty(logAcesso.getAcao())) {
            throw new BusinessException("Ação não preenchida");
        }

        if (logAcesso.getUsuario() == null || logAcesso.getUsuario().getId() == null) {
            throw new BusinessException("Usuário não informado");
        }

        Usuario usuario = usuarioIRepository.findById(logAcesso.getUsuario().getId())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        logAcessoAtualizado.setAcao(logAcesso.getAcao());
        logAcessoAtualizado.setIpOrigem(logAcesso.getIpOrigem());
        logAcessoAtualizado.setUsuario(usuario);

        return logAcessoIRepository.save(logAcessoAtualizado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logAcessoIRepository.deleteById(id);
    }
}