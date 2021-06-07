DROP TABLE IF EXISTS itinerary;
DROP TABLE IF EXISTS diagnose_record;
DROP TABLE IF EXISTS close_contact;
DROP TABLE IF EXISTS isolation_record;
DROP TABLE IF EXISTS isolation_location;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS location;

CREATE TABLE person(
  id int NOT NULL,
  fullname char(20) NOT NULL,
  telephone char(11) NOT NULL,
  CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE TABLE location(
	id int NOT NULL,
	location_name char(20) NOT NULL,
	CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE itinerary(
	id int NOT NULL,
	p_id int NULL,
	loc_id int NULL,
	s_time datetime NULL,
	e_time datetime NULL,
	CONSTRAINT pk_itinerary PRIMARY KEY (id),
	CONSTRAINT fk_itinerary_pid FOREIGN KEY (p_id) REFERENCES person (id),
	CONSTRAINT fk_itinerary_lid FOREIGN KEY (loc_id) REFERENCES location (id)
);

CREATE TABLE diagnose_record(
	id int NOT NULL,
	p_id int NULL,
	diagnose_date datetime NULL,
	result int NULL,
	CONSTRAINT pk_diagnose_record PRIMARY KEY (id),
	CONSTRAINT fk_diagnose_pid FOREIGN KEY (p_id) REFERENCES person (id)
);

CREATE TABLE close_contact(
	id int NOT NULL,
	p_id int NULL,
	contact_date datetime NULL,
	loc_id int NULL,
	case_p_id int NULL,
	CONSTRAINT pk_close_contact PRIMARY KEY (id),
	CONSTRAINT fk_contact_pid FOREIGN KEY (p_id) REFERENCES person (id),
	CONSTRAINT fk_contact_lid FOREIGN KEY (loc_id) REFERENCES location (id),
	CONSTRAINT fk_contact_caseid FOREIGN KEY (case_p_id) REFERENCES person (id)
);

CREATE TABLE isolation_location(
	id int NOT NULL,
	location_name char(20) NULL,
	capacity int NULL,
	CONSTRAINT pk_isolation_loc PRIMARY KEY (id)
);

CREATE TABLE isolation_record(
	id int NOT NULL,
	p_id int NULL,
	s_date datetime NULL,
	e_date datetime NULL,
	isol_loc_id int NULL,
	state int NULL,
	CONSTRAINT pk_isolation PRIMARY KEY (id),
	CONSTRAINT fk_isolation_pid FOREIGN KEY (p_id) REFERENCES person (id),
	CONSTRAINT fk_isolation_lid FOREIGN KEY (isol_loc_id) REFERENCES isolation_location (id)
);
