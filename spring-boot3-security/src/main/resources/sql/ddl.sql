create table tb_user
(
    user_sq serial
        constraint tb_user_pk
            primary key,
    user_id text,
    user_pw text,
    user_nm text,
    user_st text
);

alter table tb_user
    owner to localmaster;