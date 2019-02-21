-- Populate knows_undirected table
create table knows_undirected (
  k_person1id bigint not null,
  k_person2id bigint not null,
  k_creationdate bigint not null
);

COPY knows_undirected ( k_person1id, k_person2id, k_creationdate) FROM 'PATHVAR/person_knows_person_0_0.csv' WITH DELIMITER '|' CSV HEADER;
COPY knows_undirected ( k_person2id, k_person1id, k_creationdate) FROM 'PATHVAR/person_knows_person_0_0.csv' WITH DELIMITER '|' CSV HEADER;

ALTER TABLE knows_undirected ADD PRIMARY KEY (k_person1id, k_person2id);

CREATE INDEX knows_undirected_person1id ON knows_undirected (k_person1id);
CREATE INDEX knows_undirected_person2id ON knows_undirected (k_person2id);

vacuum analyze;
