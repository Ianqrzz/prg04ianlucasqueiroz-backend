package br.com.ifba.usuario.service;


import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioIRepository;
import ch.qos.logback.core.util.StringUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UsuarioService implements UsuarioIService {


    // Ainda precisa ser finalizadO

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());
    @Autowired
    private UsuarioIRepository usuarioIRepository;


    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        logger.info("Iniciando o Registro do Usuario");

        if (StringUtil.isNullOrEmpty(usuario.getEmail())){
            throw new BusinessException("Login do Usuario não preenchido");
        }

        if (StringUtil.isNullOrEmpty(usuario.getSenha())){
            throw new BusinessException("Senha não preenchida");
        }

        logger.info("Usuario Registrado com sucesso");
        return usuarioIRepository.save(usuario);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioIRepository.findAll(pageable);
    }

    @Override
    public Usuario update( Long id, Usuario usuario) {

        Usuario usuarioAtualizado = usuarioIRepository.findById(id).orElseThrow();
        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setEmail(usuario.getEmail());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioIRepository.save(usuarioAtualizado);
        logger.info("Usuario atualizado com sucesso");
        return  usuarioIRepository.save(usuarioAtualizado);
    }

    @Override
    public void delete(Long id) {
    if (id == null) {
        throw new RuntimeException("ID do usuário não informado");
    }

    usuarioIRepository.deleteById(id);
}

    @Override
    public Usuario findByEmail(String email) {
        return usuarioIRepository.findByEmail(email);
    }

    @Override
    public boolean validarLogin(String email, String senha) {
        return false;
    }
}
