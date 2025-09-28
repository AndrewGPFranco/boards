package com.agpfdev.board.models;

import com.agpfdev.board.utils.UtilsValidation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome")
    @Length(max = 30, min = 3)
    private String nome;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "email", unique = true)
    private String email;

    @Length(max = 20, min = 3)
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

    @OneToOne(
            cascade = CascadeType.ALL,
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "board_id")
    private Board board;

    public User(String nome, String nomeCompleto, String email, String senha,
                String numeroTelefone, LocalDate dataNascimento, String username) {
        boolean isEmailValid = UtilsValidation.isValidEmail(email);
        if (!isEmailValid) {
            throw new IllegalArgumentException(
                    """
                                O email informado não esta no padrão necessário!
                                Padrão correto: 'email@email.com'
                            """
            );
        }

        boolean isAgeValid = UtilsValidation.isValidAge(dataNascimento);
        if (!isAgeValid)
            throw new IllegalArgumentException("Para utilizar o sistema é necessário ter ao menos 10 anos de idade!");

        this.nome = nome;
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.dataNascimento = dataNascimento;
    }


}
