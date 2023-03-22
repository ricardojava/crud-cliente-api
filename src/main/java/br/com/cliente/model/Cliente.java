package br.com.cliente.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "sexo")
    private String sexo;

}
