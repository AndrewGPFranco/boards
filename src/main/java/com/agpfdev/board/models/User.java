package com.agpfdev.board.models;

import com.agpfdev.board.utils.UtilsValidation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

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

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Board> boards = new ArrayList<>();

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", senha='" + senha + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", boards=" + boards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nome, user.nome) && Objects.equals(nomeCompleto, user.nomeCompleto) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(senha, user.senha) && Objects.equals(numeroTelefone, user.numeroTelefone) && Objects.equals(dataNascimento, user.dataNascimento) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(boards, user.boards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nomeCompleto, email, username, senha, numeroTelefone, dataNascimento, createdAt, updatedAt, boards);
    }
}
