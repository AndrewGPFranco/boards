create table if not exists itens_board (
    id uuid not null,
    titulo varchar(25) not null,
    descricao varchar(5000),
    categoria varchar(10) not null,
    board_id uuid not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    finalized_at timestamp,
    constraint pk_itens_board primary key (id),
    constraint fk_itens_board_board foreign key (board_id) references boards(id)
);