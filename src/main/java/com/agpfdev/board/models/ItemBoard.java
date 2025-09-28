package com.agpfdev.board.models;

import com.agpfdev.board.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder
@ToString
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

}
