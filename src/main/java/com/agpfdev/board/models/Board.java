package com.agpfdev.board.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

}
