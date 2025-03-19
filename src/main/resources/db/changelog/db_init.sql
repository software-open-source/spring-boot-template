-- public.vocabulary definition

-- Drop table

DROP TABLE IF EXISTS public.vocabulary;

CREATE TABLE public.vocabulary (
	id serial4 NOT NULL,
	word varchar(100) NOT NULL UNIQUE,
	phonetic varchar(50) NOT NULL,
	word_type varchar(50) NOT NULL,
	word_type_en varchar(50) NOT NULL,
	meaning_vn varchar(200) NOT NULL,
	meaning_explanation_vn text NOT NULL,
	verb_forms varchar(100) NULL,
	example text NOT NULL,
	example_meaning varchar(200) NOT NULL,
	CONSTRAINT vocabulary_pkey PRIMARY KEY (id)
);