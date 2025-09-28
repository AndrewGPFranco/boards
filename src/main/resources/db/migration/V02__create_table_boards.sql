create table if not exists boards (
    id uuid not null,
    titulo varchar(25) not null,
    descricao varchar not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint pk_boards primary key (id)
);