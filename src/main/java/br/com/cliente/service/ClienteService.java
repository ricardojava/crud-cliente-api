package br.com.cliente.service;

import br.com.cliente.dto.ClienteDto;
import br.com.cliente.handler.ClienteNaoLocalizadoException;
import br.com.cliente.handler.CpfDuplicadoException;
import br.com.cliente.model.Cliente;
import br.com.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private ModelMapper modelMapper = new ModelMapper();

    public Page<Cliente> buscarTodos(String nome, Pageable pageable) {
        return StringUtils.hasText(nome) ?
                this.repository.findClienteByNomeContainingIgnoreCase(nome, pageable) :
                this.repository.findAll(pageable);
    }

    public Optional<ClienteDto> buscarPorCpf(String cpf) {
        Cliente cliente = this.repository.findClienteByCpf(cpf.replaceAll("\\D", ""))
                .orElseThrow(() -> new ClienteNaoLocalizadoException());
        ClienteDto c = this.modelMapper
                .map(cliente, ClienteDto.class);
        return Optional.ofNullable(c);
    }

    public ClienteDto salvar(ClienteDto cliente) {
        Optional<Cliente> clientePorCpf = this.repository.findClienteByCpf(cliente.getCpf().replaceAll("\\D", ""));
        if (clientePorCpf.isPresent()) {
            throw new CpfDuplicadoException();
        }
        cliente.setCpf(cliente.getCpf().replaceAll("\\D", ""));
        final Cliente c = this.repository.save(this.modelMapper.map(cliente, Cliente.class));
        return this.modelMapper.map(c, ClienteDto.class);
    }

    public ClienteDto atualizarCliente(Long id, ClienteDto cliente) {
        validaCliente(id);
        cliente.setId(id);
        cliente.setCpf(cliente.getCpf().replaceAll("\\D", ""));
        final Cliente c = this.repository.save(this.modelMapper.map(cliente, Cliente.class));
        return this.modelMapper.map(c, ClienteDto.class);
    }

    public void removerCliente(Long id) {
        validaCliente(id);
        this.repository.deleteById(id);
    }

    private void validaCliente(Long id) {
        if (this.repository.findById(id).isEmpty()) {
            throw new ClienteNaoLocalizadoException();
        }
    }

}
