create table news(
    id bigint primary key auto_increment,
    title text,
    content text,
    url varchar(1000),
    created_at timestamp,
    modified_at timestamp
);


create table LINKS_TO_BE_PROCESSED (
link varchar(2000)
);


create table LINKS_ALREADY_PROCESSED (
link varchar(2000)
);