package com.agpfdev.board.models;

import com.agpfdev.board.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens_board")
public class ItemBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 25)
    @Column(name = "titulo")
    private String titulo;

    @Length(min = 1, max = 5000)
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoryType categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "finalized_at")
    private Instant finalizedAt;

    @Override
    public String toString() {
        return "ItemBoard{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", finalizedAt=" + finalizedAt +
                ", board=" + board +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemBoard itemBoard = (ItemBoard) o;
        return Objects.equals(id, itemBoard.id) && Objects.equals(titulo, itemBoard.titulo) && Objects.equals(descricao, itemBoard.descricao) && categoria == itemBoard.categoria && Objects.equals(board, itemBoard.board) && Objects.equals(createdAt, itemBoard.createdAt) && Objects.equals(updatedAt, itemBoard.updatedAt) && Objects.equals(finalizedAt, itemBoard.finalizedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, categoria, board, createdAt, updatedAt, finalizedAt);
    }

}
