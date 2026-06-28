package br.com.ifba.perfilusuario.service;

import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.perfilusuario.repository.PerfilUsuarioIRepository;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class PerfilUsuarioService implements PerfilUsuarioIService {

    private static final Logger logger = Logger.getLogger(PerfilUsuarioService.class.getName());

    @Autowired
    private PerfilUsuarioIRepository perfilUsuarioIRepository;

    @Override
    @Transactional
    public PerfilUsuario save(PerfilUsuario perfilUsuario) {
        logger.info("Iniciando o Registro do Perfil de Usuário");

        if (StringUtil.isNullOrEmpty(perfilUsuario.getNome())) {
            throw new BusinessException("Nome do perfil não preenchido");
        }

        logger.info("Perfil de Usuário registrado com sucesso");
        return perfilUsuarioIRepository.save(perfilUsuario);
    }

    @Override
    public Page<PerfilUsuario> findAll(Pageable pageable) {
        return perfilUsuarioIRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public PerfilUsuario update(Long id, PerfilUsuario perfilUsuario) {
        PerfilUsuario perfilUsuarioAtualizado = perfilUsuarioIRepository.findById(id).orElseThrow();

        perfilUsuarioAtualizado.setNome(perfilUsuario.getNome());
        perfilUsuarioAtualizado.setDescricao(perfilUsuario.getDescricao());
        perfilUsuarioAtualizado.setPermissoes(perfilUsuario.getPermissoes());

        logger.info("Perfil de Usuário atualizado com sucesso");
        return perfilUsuarioIRepository.save(perfilUsuarioAtualizado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new BusinessException("ID do perfil de usuário não informado");
        }

        PerfilUsuario perfilUsuario = perfilUsuarioIRepository.findById(id).orElseThrow();
        perfilUsuarioIRepository.delete(perfilUsuario);

        logger.info("Perfil de Usuário deletado com sucesso");
    }
}