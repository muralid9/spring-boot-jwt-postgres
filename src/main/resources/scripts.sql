CREATE SCHEMA bank
    AUTHORIZATION postgres;
	
-- Table: bank.account_master

-- DROP TABLE bank.account_master;

CREATE TABLE bank.account_master
(
    id integer NOT NULL DEFAULT nextval('bank.account_master_id_seq'::regclass),
    account_type text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_master_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE bank.account_master
    OWNER to postgres;

-- Table: bank.role_master

-- DROP TABLE bank.role_master;

CREATE TABLE bank.role_master
(
    role_id integer NOT NULL DEFAULT nextval('bank.role_master_role_id_seq'::regclass),
    role_name text COLLATE pg_catalog."default",
    CONSTRAINT role_master_pkey_1 PRIMARY KEY (role_id)
)

TABLESPACE pg_default;

ALTER TABLE bank.role_master
    OWNER to postgres;

-- Table: bank.bank_employee_details

-- DROP TABLE bank.bank_employee_details;

CREATE TABLE bank.bank_employee_details
(
    employee_id integer NOT NULL DEFAULT nextval('bank.bank_employee_details_employee_id_seq'::regclass),
    employee_name text COLLATE pg_catalog."default",
    email_id text COLLATE pg_catalog."default",
    contact_details text COLLATE pg_catalog."default",
    login_username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT bank_employee_details_table_pkey_1 PRIMARY KEY (employee_id)
)

TABLESPACE pg_default;

ALTER TABLE bank.bank_employee_details
    OWNER to postgres;

	
	
-- Table: bank.employee_roles

-- DROP TABLE bank.employee_roles;

CREATE TABLE bank.employee_roles
(
    role_id integer NOT NULL,
    employee_id integer NOT NULL,
    CONSTRAINT employee_id FOREIGN KEY (employee_id)
        REFERENCES bank.bank_employee_details (employee_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT role_id FOREIGN KEY (role_id)
        REFERENCES bank.role_master (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE bank.employee_roles
    OWNER to postgres;
	
-- Table: bank.role_master

-- DROP TABLE bank.role_master;

-- Table: bank.customer_details

-- DROP TABLE bank.customer_details;

CREATE TABLE bank.customer_details
(
    id integer NOT NULL DEFAULT nextval('bank.customer_details_id_seq'::regclass),
    customer_id integer NOT NULL,
    customer_name text COLLATE pg_catalog."default" NOT NULL,
    customer_email text COLLATE pg_catalog."default",
    address text COLLATE pg_catalog."default" NOT NULL,
    kyc_status text COLLATE pg_catalog."default" NOT NULL,
    contact_details text COLLATE pg_catalog."default",
    kyc_id text COLLATE pg_catalog."default" NOT NULL,
    error_status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT customer_details_pkey PRIMARY KEY (customer_id)
)

TABLESPACE pg_default;

ALTER TABLE bank.customer_details
    OWNER to postgres;

-- Table: bank.account_details

-- DROP TABLE bank.account_details;

CREATE TABLE bank.account_details
(
    bank_account_number integer NOT NULL DEFAULT nextval('bank.account_details_bank_account_number_seq'::regclass),
    balance numeric(25,2),
    customer_id integer,
    interest_rate numeric(6,2),
    account_type text COLLATE pg_catalog."default",
    CONSTRAINT account_details_pkey_1 PRIMARY KEY (bank_account_number),
    CONSTRAINT account_details_customer_id_fkey_1 FOREIGN KEY (customer_id)
        REFERENCES bank.customer_details (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE bank.account_details
    OWNER to postgres;	
	
-- Table: bank.transactions_details

-- DROP TABLE bank.transactions_details;

CREATE TABLE bank.transactions_details
(
    id integer NOT NULL,
    source_account integer NOT NULL,
    destination_account integer NOT NULL,
    source_type text COLLATE pg_catalog."default" NOT NULL,
    destination_type text COLLATE pg_catalog."default" NOT NULL,
    amount numeric(25,2) NOT NULL,
    transaction_date date NOT NULL,
    CONSTRAINT transactions_details_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE bank.transactions_details
    OWNER to postgres;	
