-- Insert data into settlements table
INSERT INTO settlements (has_subway, population, name)
VALUES (TRUE, 1000000, 'Settlement A'),
       (TRUE, 2000000, 'Settlement B'),
       (TRUE, 3000000, 'Settlement C'),
       (TRUE, 4000000, 'Settlement D'),
       (TRUE, 5000000, 'Settlement E');

-- Insert data into landmark_types table
INSERT INTO landmark_types (name)
VALUES ('ARCHITECTURE'),
       ('MUSEUMS_AND_GALLERIES'),
       ('BEACHES_AND_PARKS'),
       ('ARCHEOLOGICAL'),
       ('HISTORICAL');

-- Insert data into landmarks table
INSERT INTO landmarks (creation_year, type_id, settlement_id, description, name)
VALUES (1990, 1, 1, 'Description for Landmark A', 'Landmark A'),
       (1991, 2, 2, 'Description for Landmark B', 'Landmark B'),
       (1992, 3, 3, 'Description for Landmark C', 'Landmark C'),
       (1992, 4, 4, 'Description for Landmark D', 'Landmark D'),
       (1992, 5, 5, 'Description for Landmark E', 'Landmark E');

-- Insert data into services table
INSERT INTO services (landmark_id, description, name)
VALUES (1, 'Description for Service A', 'Service A'),
       (2, 'Description for Service B', 'Service B'),
       (3, 'Description for Service C', 'Service C'),
       (4, 'Description for Service D', 'Service D'),
       (5, 'Description for Service E', 'Service E');

-- Insert data into landmarks_services table
INSERT INTO landmarks_services (landmark_landmark_id, services_service_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);