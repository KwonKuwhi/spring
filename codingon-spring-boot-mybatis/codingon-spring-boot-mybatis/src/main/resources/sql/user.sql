use kdt;
-- 미리 테이블 만들어놔야함
create table user(
    id bigint not null auto_increment primary key,
    name varchar(255) not null,
    nickname varchar(255) not null
);