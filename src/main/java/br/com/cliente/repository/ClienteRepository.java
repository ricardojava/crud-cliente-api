package br.com.cliente.repository;

import br.com.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

    Optional<Cliente> findClienteByCpf(String cpf);

    Page<Cliente> findClienteByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
