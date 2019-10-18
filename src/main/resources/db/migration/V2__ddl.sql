CREATE SCHEMA dc;

CREATE TABLE dc.automaker (
                              id bigserial NOT NULL,
                              name character varying NOT NULL,
                              country varchar(100),
                              CONSTRAINT pk_automaker PRIMARY KEY (id)
);

CREATE TABLE dc.collection (
                               id bigserial NOT NULL,
                               name character varying NOT NULL,
                               year int,
                               CONSTRAINT pk_collection PRIMARY KEY (id)
);

CREATE TABLE dc.brand (
                          id bigserial NOT NULL,
                          name character varying NOT NULL,
                          CONSTRAINT pk_brand PRIMARY KEY (id)
);

CREATE TABLE dc.model (
                          id bigserial NOT NULL,
                          name character varying NOT NULL,
                          model_year int,
                          scale varchar(10),
                          color_rgba varchar(7),
                          automaker_id bigint,
                          collection_id bigint,
                          brand_id bigint,
                          CONSTRAINT pk_model PRIMARY KEY (id),
                          CONSTRAINT fk_automaker FOREIGN KEY (automaker_id) REFERENCES dc.automaker (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION,
                          CONSTRAINT fk_collection FOREIGN KEY (collection_id) REFERENCES dc.collection (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION,
                          CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES dc.brand (id) MATCH SIMPLE
                              ON UPDATE NO ACTION ON DELETE NO ACTION
);