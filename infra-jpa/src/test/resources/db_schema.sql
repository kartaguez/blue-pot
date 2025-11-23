DROP TABLE IF EXISTS pot;
DROP TABLE IF EXISTS pot_global_version;
DROP TABLE IF EXISTS pot_shareholder;

DROP SEQUENCE IF EXISTS public.hibernate_sequence;

CREATE TABLE pot(
    id BIGINT PRIMARY KEY,
    version BIGINT NOT NULL,
    uuid UUID NOT NULL,
    active_from BIGINT NOT NULL,
    inactive_from BIGINT,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT unique_pot_uuid_version_active
        UNIQUE (uuid, active_from),
    CONSTRAINT unique_pot_uuid_version_inactive
        UNIQUE (uuid, inactive_from)
);

CREATE TABLE pot_global_version(
    id BIGINT PRIMARY KEY,
    version BIGINT NOT NULL,
    pot_uuid UUID NOT NULL,
    pot_business_version_value BIGINT NOT NULL,
    pot_business_version_stamp VARCHAR(255) NOT NULL
);

CREATE TABLE pot_shareholder(
    id BIGINT PRIMARY KEY,
    version BIGINT NOT NULL,
    uuid uuid NOT NULL,
    pot_uuid UUID NOT NULL,
    active_from BIGINT NOT NULL,
    inactive_from BIGINT,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT unique_pot_shareholder_uuid_version_active
        UNIQUE (uuid, active_from),
    CONSTRAINT unique_pot_shareholder_uuid_version_inactive
        UNIQUE (uuid, inactive_from)
);

CREATE SEQUENCE public.hibernate_sequence
    START WITH 100
    INCREMENT BY 1;

