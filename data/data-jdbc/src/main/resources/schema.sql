DROP TABLE IF EXISTS car;

CREATE TABLE IF NOT EXISTS car (
	id bigint NOT NULL,
	make varchar(100),
	model varchar(100),
	year int,
	PRIMARY KEY (id)
);