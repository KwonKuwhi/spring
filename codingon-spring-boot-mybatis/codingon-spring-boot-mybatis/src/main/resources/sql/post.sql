use kdt;
-- 미리 테이블 만들어놔야함
create table post(
    id bigint not null auto_increment primary key,
    title varchar(20) not null,
    content varchar(100) not null,
    writer varchar(10) not null,

);