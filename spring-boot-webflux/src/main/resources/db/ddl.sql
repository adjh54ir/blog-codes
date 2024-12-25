-- 사용자 테이블
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

-- 코드 테이블
create table tb_code
(
    grp_cd     varchar not null,
    cd         varchar,
    grp_cd_nm  varchar,
    cd_nm      varchar,
    sort_order integer,
    reg_dt     timestamp,
    use_yn     boolean
);

alter table tb_code
    owner to localmaster;

