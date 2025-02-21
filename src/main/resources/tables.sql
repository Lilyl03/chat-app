create table public."user"
(
    userid   bigint default nextval('users_userid_seq'::regclass)
        not null constraint users_pkey primary key,
    username varchar(50)  not null,
    password varchar(50)  not null
);

alter table public."user"
    owner to postgres;

create table public.message
(
    messageid bigint default nextval('messages_messageid_seq'::regclass)
        not null constraint messages_pkey primary key,
    content   text                                                       not null,
    receiver  bigint
        constraint fk1wn5617q01o90dwqjua8yhvux references public."user",
    sender    bigint
        constraint fkob83vkf2oo4r68pn9d69kgwf8 references public."user"
);

alter table public.message
    owner to postgres;

