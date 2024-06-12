CREATE TABLE public.usuario
(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  email character varying(100),
  senha character varying(100),
  data_cadastro date default now(),
  role varchar(100)
);
