CREATE TABLE acted_in
(
    role varchar(100),
    person_id INTEGER,
    movie_id INTEGER
);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Neo', 1, 1);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Trinity', 2, 1);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Morpheus', 3, 1);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Agent Smith', 4, 1);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Kevin Lomax', 1, 2);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Mary Ann Lomax', 8, 2);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('John Milton', 9, 2);
INSERT INTO acted_in (role, person_id, movie_id) VALUES ('Aileen', 8, 3);
CREATE TABLE directed
(
    person_id INTEGER,
    movie_id INTEGER
);
INSERT INTO directed (person_id, movie_id) VALUES (5, 1);
INSERT INTO directed (person_id, movie_id) VALUES (6, 1);
INSERT INTO directed (person_id, movie_id) VALUES (10, 2);
CREATE TABLE movie
(
    id INTEGER,
    title VARCHAR(100),
    released INTEGER,
    tagline VARCHAR(100)
);
INSERT INTO movie (id, title, released, tagline) VALUES (1, 'The Matrix', 1999, 'Welcome to the Real World');
INSERT INTO movie (id, title, released, tagline) VALUES (2, 'The Devil s Advocate', 1997, 'Evil has its winning ways');
INSERT INTO movie (id, title, released, tagline) VALUES (3, 'Monster', 2003, 'The first female serial killer of America');
CREATE TABLE person
(
    id INTEGER,
    name VARCHAR(100),
    born INTEGER
);
INSERT INTO person (id, name, born) VALUES (1, 'Keanu Reeves', 1964);
INSERT INTO person (id, name, born) VALUES (2, 'Carrie-Anne Moss', 1967);
INSERT INTO person (id, name, born) VALUES (3, 'Laurence Fishburne', 1961);
INSERT INTO person (id, name, born) VALUES (4, 'Hugo Weaving', 1960);
INSERT INTO person (id, name, born) VALUES (5, 'Andy Wachowski', 1967);
INSERT INTO person (id, name, born) VALUES (6, 'Lana Wachowski', 1965);
INSERT INTO person (id, name, born) VALUES (7, 'Joel Silver', 1952);
INSERT INTO person (id, name, born) VALUES (8, 'Charlize Theron', 1975);
INSERT INTO person (id, name, born) VALUES (9, 'Al Pacino', 1940);
INSERT INTO person (id, name, born) VALUES (10, 'Taylor Hackford', 1944);
CREATE TABLE produced
(
    person_id INTEGER,
    movie_id INTEGER
);
INSERT INTO produced (person_id, movie_id) VALUES (7, 1);
INSERT INTO produced (person_id, movie_id) VALUES (8, 3);
