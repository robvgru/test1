create table post (
    id int not null auto_increment,
    title varchar(100),
    content varchar,
    primary key (id)
);

create table tag (
    name varchar(100) not null,
    primary key (name)
);

create table post_tag (
    id int not null auto_increment,
    post_id int not null,
    tag_id varchar(100) not null,
    foreign key (post_id) references post(id),
    foreign key (tag_id) references tag(name)
);


