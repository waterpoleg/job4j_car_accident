CREATE TABLE accident_type (
    id serial primary key,
    name text
);

CREATE TABLE accident (
    id serial primary key,
    name text,
    text text,
    address text,
    type int references accident_type(id)
);

CREATE TABLE rule (
    id serial primary key,
    name text
);

CREATE TABLE accident_rule (
    id serial primary key,
    accident_id int not null references accident(id),
    rule_id int references rule(id)
);



insert into accident_type (name) values ('Car & Car');
insert into accident_type (name) values ('Car & human');
insert into accident_type (name) values ('Car & bike');

insert into rule (name) values ('Rule 1');
insert into rule (name) values ('Rule 2');
insert into rule (name) values ('Rule 3');
