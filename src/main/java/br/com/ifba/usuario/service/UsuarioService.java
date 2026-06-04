package br.com.ifba.usuario.service;


import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioIRepository;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Usuario> findAll() {
        return usuarioIRepository.findAll();
    }

    @Override
    public Usuario update(Usuario usuario) {
        return null;
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
