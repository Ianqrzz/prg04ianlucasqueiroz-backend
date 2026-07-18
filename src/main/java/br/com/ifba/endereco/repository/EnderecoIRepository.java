package br.com.ifba.endereco.repository;

import br.com.ifba.endereco.entity.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoIRepository extends JpaRepository <Endereco, Long>{
    Page<Endereco> findByUsuarioId(Long usuarioId, Pageable pageable);
}
