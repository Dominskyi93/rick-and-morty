--liquibase formatted sql
--changeset <postgres>:<create-movie_character_table>
CREATE TABLE IF NOT EXISTS public.movie_character
(
    id     bigint                 NOT NULL,
    name   character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    CONSTRAINT movie_character_pk PRIMARY KEY (id)
);

--rollback DROP TABLE public.movie_character;