-- Table: public.posts

-- DROP TABLE public.posts;

CREATE TABLE public.posts
(
    id integer NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    body character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.posts
    OWNER to postgres;

insert into posts (id, title, body) values (1, 'Post A', 'Post A Content');
insert into posts (id, title, body) values (2, 'Post B', 'Post B Content');
insert into posts (id, title, body) values (3, 'Post C', 'Post C Content');
insert into posts (id, title, body) values (4, 'Post D', 'Post D Content');

select * from posts;