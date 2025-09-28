alter table users
add column board_id uuid unique;

alter table users
add constraint fk_users_board
foreign key (board_id) references boards(id);