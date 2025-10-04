alter table boards
add column user_id uuid;

alter table boards
add constraint fk_users_board
foreign key (user_id) references users(id);