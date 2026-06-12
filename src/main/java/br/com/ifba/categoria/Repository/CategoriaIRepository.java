package br.com.ifba.categoria.Repository;

import br.com.ifba.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaIRepository extends JpaRepository<Categoria, Long> {

}
