--create schema emp_dept authorization sa;
--DROP SCHEMA IF EXISTS emp_dept;
--CREATE SCHEMA IF NOT EXISTS emp_dept AUTHORIZATION sa;

create sequence DEPARTMENT_SEQ increment by 10 start with 10;
create table department (dept_id numeric not null, dept_name varchar(100) not null);
alter table department ADD CONSTRAINT dept_pk PRIMARY KEY (dept_id);


create sequence employee_seq;
create table employee (
								emp_id numeric not null default nextval('employee_seq'),
								first_name varchar(50) not null,
								middle_initial varchar(1),
								last_name varchar(50) not null,
								suffix varchar(5),
								date_of_birth date,
								join_date date not null,
								dept_id numeric not null,
								created_date timestamp not null,
								created_by varchar(10) not null,
								updated_date timestamp not null,
								updated_by varchar(10) not null
							);
alter table employee ADD CONSTRAINT employee_pk PRIMARY KEY (emp_id);
ALTER TABLE employee ADD CONSTRAINT dept_fk FOREIGN KEY (dept_id) REFERENCES department(dept_id);

create sequence contact_seq;
create table contact(
								contact_id numeric not null default nextval('contact_seq'),
								email varchar(100),
								CONSTRAINT contact_pk PRIMARY KEY (contact_id)
							);

alter table contact add column emp_id numeric not null;
ALTER TABLE contact ADD CONSTRAINT contact_un UNIQUE (emp_id);
ALTER TABLE contact ADD CONSTRAINT contact_fk FOREIGN KEY (emp_id) REFERENCES employee(emp_id);


create sequence address_seq;
create table address (
								address_id numeric not null default nextval('address_seq'),
								street1 varchar(100) not null,
								street2 varchar(100),
								city varchar(50) not null,
								state varchar(2) not null,
								zip_code varchar(5) not null,
								contact_id numeric not null,
								CONSTRAINT address_pk PRIMARY KEY (address_id),
								CONSTRAINT address_fk FOREIGN KEY (contact_id) REFERENCES contact(contact_id)
							);

alter table address add column addr_type varchar(20) not null;

create sequence phone_seq;
create table phone(
								phone_id numeric not null default nextval('phone_seq'),
								phone_no numeric not null,
								phone_type varchar(10) not null,
								contact_id numeric not null,
								CONSTRAINT phone_pk PRIMARY KEY (phone_id),
								CONSTRAINT phone_fk FOREIGN KEY (contact_id) REFERENCES contact(contact_id)
							);

create sequence project_seq;
create table projects (
								prj_id numeric not null default nextval('project_seq'),
								prj_name varchar(100) not null,
								expected_start_date date not null,
								actual_start_date date,
								expected_finish_date date not null,
								actual_finish_date date,
								created_date timestamp not null,
								created_by varchar(10) not null,
								updated_date timestamp not null,
								updated_by varchar(10) not null
							);
alter table projects ADD CONSTRAINT projects_pk PRIMARY KEY (prj_id);

create table emp_prj (
			emp_id numeric not null,
			prj_id numeric not null,
			CONSTRAINT emp_fk FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
			CONSTRAINT prj_fk FOREIGN KEY (prj_id) REFERENCES projects(prj_id),
			constraint emp_prj_uk unique (emp_id, prj_id)
			);

COMMIT;