package com.agpfdev.board.models;

import com.agpfdev.board.utils.UtilsValidation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Length(min = 8)
    @Column(name = "senha")
    private String senha;

    @Column(name = "numero_telefone", unique = true, length = 11)
    private String numeroTelefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public User(String nome, String nomeCompleto, String email, String senha,
                String numeroTelefone, LocalDate dataNascimento, String username) {
        UtilsValidation.validaEmail(email);
        UtilsValidation.validaIdadeUsuario(dataNascimento);

        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.dataNascimento = dataNascimento;
    }


}
