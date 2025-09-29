package com.agpfdev.board.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "titulo")
    @Length(min = 3, max = 25)
    private String titulo;

    @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @OneToOne(
            mappedBy = "board",
            cascade = CascadeType.ALL,
            optional = false,
            fetch = FetchType.LAZY
    )
    private User user;

    @OneToMany(
            mappedBy = "board",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<ItemBoard> itensBoard;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", user=" + user +
                ", itensBoard=" + itensBoard +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(titulo, board.titulo) && Objects.equals(descricao, board.descricao) && Objects.equals(user, board.user) && Objects.equals(itensBoard, board.itensBoard) && Objects.equals(createdAt, board.createdAt) && Objects.equals(updatedAt, board.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, user, itensBoard, createdAt, updatedAt);
    }
}
