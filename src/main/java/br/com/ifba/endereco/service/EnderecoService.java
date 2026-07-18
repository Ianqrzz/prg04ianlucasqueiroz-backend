package br.com.ifba.endereco.service;

import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.repository.EnderecoIRepository;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService implements EnderecoIService {

    private final EnderecoIRepository enderecoIRepository;
    private final UsuarioIRepository usuarioIRepository;

    @Override
    @Transactional
    public Endereco save(Endereco endereco, Long idUsuario) {
        Usuario usuario = usuarioIRepository.findById(idUsuario)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com o ID: " + idUsuario));

        endereco.setUsuario(usuario);
        return enderecoIRepository.save(endereco);
    }

    @Override
    @Transactional
    public Endereco update(Long id, Endereco enderecoAtualizado) {
        Endereco enderecoExistente = findById(id);

        enderecoExistente.setRua(enderecoAtualizado.getRua());
        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
        enderecoExistente.setCidade(enderecoAtualizado.getCidade());
        enderecoExistente.setEstado(enderecoAtualizado.getEstado());
        enderecoExistente.setCep(enderecoAtualizado.getCep());

        return enderecoIRepository.save(enderecoExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Endereco endereco = findById(id);
        enderecoIRepository.delete(endereco);
    }

    @Override
    public Endereco findById(Long id) {
        return enderecoIRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Endereço não encontrado com o ID: " + id));
    }

    @Override
    public Page<Endereco> findAll(Pageable pageable) {
        return enderecoIRepository.findAll(pageable);
    }

    @Override
    public Page<Endereco> findByUsuarioId(Long idUsuario, Pageable pageable) {
        return enderecoIRepository.findByUsuarioId(idUsuario, pageable);
    }
}