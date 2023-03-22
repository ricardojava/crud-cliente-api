package br.com.cliente;

import br.com.cliente.dto.ClienteDto;
import br.com.cliente.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteControllerTests {

	@Autowired
	private ClienteService service;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	@SneakyThrows
	@Test
	void erroNomeVazio() {
		ClienteDto cliente = ClienteDto
				.builder()
				.sexo("M")
				.cpf("93608402039")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("O campo nome e obrigatorio!")));
	}

	@SneakyThrows
	@Test
	void erroNomeMaior() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo teste. ipowie oiwq eopqiw eoqpiw epoqi woei qpowie poqiwp eoipoqwieopiqpowiepoqiwe")
				.sexo("M")
				.cpf("93608402039")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("O campo nome deve ter o tamanho minimo de 3 e máximo de 40 posições")));
	}

	@SneakyThrows
	@Test
	void erroDataNascimentoVazia() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo Almeida")
				.sexo("M")
				.cpf("93608402039")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("O campo dataNascimento e obrigatorio!")));
	}

	@SneakyThrows
	@Test
	void erroCpfVazio() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo Almeida")
				.sexo("M")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("O campo cpf e obrigatorio!")));
	}

	@SneakyThrows
	@Test
	void erroSexoVazio() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo ALmeida")
				.cpf("93608402039")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("O campo sexo e obrigatorio!")));
	}

	@SneakyThrows
	@Test
	void erroSexoDiferente() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo Almeida")
				.cpf("93608402039")
				.sexo("X")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("Valores aceitos apenas M ou F!")));
	}

	@SneakyThrows
	@Test
	void erroCpfInvalido() {
		ClienteDto cliente = ClienteDto
				.builder()
				.nome("Ricardo Almeida")
				.cpf("23898271823")
				.sexo("M")
				.dataNascimento("1974-06-30T13:00:00")
				.build();

		mockMvc.perform(post("/clientes")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.errors[0].defaultMessage", is("CPF invalido!")));
	}

}
