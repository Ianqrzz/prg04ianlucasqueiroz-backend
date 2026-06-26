package br.com.ifba.categoria.service;


import br.com.ifba.categoria.Repository.CategoriaIRepository;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioService;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CategoriaService implements CategoriaIService{


    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private CategoriaIRepository categoriaIRepository;


    @Override
    @Transactional
    public Categoria save(Categoria categoria){
        logger.info("Iniciando o Registro da Categoria");
        if (StringUtil.isNullOrEmpty(categoria.getNome())){
            throw  new BusinessException("Nome não preenchido");
        }
        return categoriaIRepository.save(categoria);
    }

    @Override
    public Page<Categoria> findAll(Pageable pageable){
        return categoriaIRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id){

        if (id == null){
            throw new BusinessException("Categoria não encontrada");
        }
        categoriaIRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Categoria update(Long id,Categoria categoria){
        Categoria categoriaAtualizado = categoriaIRepository.findById(id).orElseThrow();
        categoriaAtualizado.setNome(categoria.getNome());
        categoriaAtualizado.setDescricao(categoria.getDescricao());
        return categoriaIRepository.save(categoriaAtualizado);
    }
}
