-- Create the "landmarks" table
CREATE TABLE IF NOT EXISTS landmarks
(
    creation_year SMALLINT,
    type_id       INTEGER,
    landmark_id   BIGSERIAL PRIMARY KEY,
    settlement_id BIGINT,
    description   VARCHAR(255),
    name          VARCHAR(255)
);

-- Create the "landmarks_services" table
CREATE TABLE IF NOT EXISTS landmarks_services
(
    landmark_landmark_id BIGINT NOT NULL,
    services_service_id  BIGINT NOT NULL UNIQUE
);

-- Create the "services" table
CREATE TABLE IF NOT EXISTS services
(
    landmark_id BIGINT,
    service_id  BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    name        VARCHAR(255)
);

-- Create the "settlements" table
CREATE TABLE IF NOT EXISTS settlements
(
    has_subway    BOOLEAN,
    population    BIGINT,
    settlement_id BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) UNIQUE
);

-- Create the "landmark_types" table
CREATE TABLE IF NOT EXISTS landmark_types
(
    type_id SMALLSERIAL PRIMARY KEY,
    name    VARCHAR(255) UNIQUE
);

-- Add foreign key constraint for "landmarks" table
ALTER TABLE landmarks
    ADD CONSTRAINT fk_landmarks_types FOREIGN KEY (type_id) REFERENCES landmark_types (type_id);

-- Add foreign key constraint for "landmarks_services" table (landmark_landmark_id)
ALTER TABLE landmarks_services
    ADD CONSTRAINT fk_landmarks_services_landmark FOREIGN KEY (landmark_landmark_id) REFERENCES landmarks (landmark_id);

-- Add foreign key constraint for "landmarks_services" table (services_service_id)
ALTER TABLE landmarks_services
    ADD CONSTRAINT fk_landmarks_services_service FOREIGN KEY (services_service_id) REFERENCES services (service_id);