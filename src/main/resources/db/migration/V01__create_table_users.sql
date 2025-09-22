create table users (
    id uuid not null,
    nome varchar(30) not null,
    nome_completo varchar not null,
    email varchar not null unique,
    username varchar(20) not null unique,
    senha varchar not null,
    numero_telefone varchar(11) unique,
    data_nascimento date not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    constraint pk_users primary key (id)
);