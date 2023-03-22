package br.com.cliente.controller;

import br.com.cliente.model.Cliente;
import br.com.cliente.dto.ClienteDto;
import br.com.cliente.service.ClienteService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
@Api(value = "API Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @ApiOperation(value = "Buscar todos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 409, message = "409 – Conflito de dados"),
            @ApiResponse(code = 204, message = "204 não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Cliente> buscarTodos(@RequestParam(required = false) String nome, Pageable pageable) {
        return this.clienteService.buscarTodos(nome, pageable);
    }

    @ApiOperation(value = "Consulta por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 409, message = "409 – Conflito de dados"),
            @ApiResponse(code = 204, message = "204  não encontrado")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable("cpf") String cpf) {
        final Optional<ClienteDto> clienteDTO = this.clienteService.buscarPorCpf(cpf);
        if (clienteDTO.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(clienteDTO.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Criar um novo Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "sucesso"),
            @ApiResponse(code = 409, message = "409 – Conflito de dados")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteDto salver(@Valid @RequestBody ClienteDto cliente) {
        return this.clienteService.salvar(cliente);
    }

    @ApiOperation(value = "Atualizar um Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sucesso"),
            @ApiResponse(code = 409, message = "409 – Conflito de dados"),
            @ApiResponse(code = 204, message = "204  não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ClienteDto atualizarCliente(@Valid @RequestBody ClienteDto cliente, @PathVariable("id") Long id) {
        return this.clienteService.atualizarCliente(id, cliente);
    }

    @ApiOperation(value = "Remover um Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "sucesso"),
            @ApiResponse(code = 409, message = "409 – Conflito de dados"),
            @ApiResponse(code = 204, message = "204  não encontrado")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        this.clienteService.removerCliente(id);
    }

}
