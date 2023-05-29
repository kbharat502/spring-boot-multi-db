CREATE SEQUENCE locale_seq
	INCREMENT BY 1
	START WITH 1;

CREATE TABLE locale (
	locale_id numeric NOT NULL DEFAULT nextval('locale_seq'),
	lang_code varchar(2) NOT NULL,
	country varchar(2) NULL,
	CONSTRAINT locale_pk PRIMARY KEY (locale_id),
	CONSTRAINT locale_un UNIQUE (lang_code, country)
);

CREATE SEQUENCE msg_seq
	INCREMENT BY 1
	START WITH 1;

CREATE TABLE validation_msgs (
	id numeric NOT NULL DEFAULT nextval('msg_seq'),
	msg_key varchar(2000) NOT NULL,
	msg_value varchar(4000) NOT NULL,
	locale_id numeric(10) NOT NULL,
	created_by varchar(100) NOT NULL,
	created_ts timestamp(0) NOT NULL,
	updated_by varchar(100) NULL,
	updated_ts timestamp(0) NULL,
	app_name varchar(100) NOT NULL,
	CONSTRAINT validation_msgs_pk PRIMARY KEY (id),
	CONSTRAINT validation_msgs_un UNIQUE (msg_key, locale_id)
);


-- validation_msgs foreign keys

ALTER TABLE validation_msgs ADD CONSTRAINT validation_msgs_fk FOREIGN KEY (locale_id) REFERENCES locale(locale_id);

