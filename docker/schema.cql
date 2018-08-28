CREATE KEYSPACE IF NOT EXISTS user_details WITH REPLICATION= { 'class':'SimpleStrategy', 'replication_factor':1 };
CREATE TYPE IF NOT EXISTS user_details.company ( name varchar, catchPhrase varchar, bs varchar);
CREATE TABLE IF NOT EXISTS  user_details.users (
id int PRIMARY KEY,
company frozen <company>
);

INSERT INTO user_details.user_details (id, company) VALUES (1,{name: 'Romaguera-Crona', catchPhrase: 'Multi-layered client-server neural-net', bs: 'harness real-time e-markets'});
INSERT INTO user_details.user_details (id, company) VALUES (2,{name: 'Deckow-Crist', catchPhrase: 'Proactive didactic contingency', bs: 'synergize scalable supply-chains'});
INSERT INTO user_details.user_details (id, company) VALUES (3,{name: 'Romaguera-Jacobson',catchPhrase: 'Face to face bifurcated interface', bs: 'e-enable strategic applications'});
INSERT INTO user_details.user_details (id, company) VALUES (4,{name: 'Robel-Corkery', catchPhrase: 'Multi-tiered zero tolerance productivity', bs: 'transition cutting-edge web services'});
INSERT INTO user_details.user_details (id, company) VALUES (5,{name: 'Keebler LLC', catchPhrase: 'Users-centric fault-tolerant solution',bs: 'revolutionize end-to-end systems'});
INSERT INTO user_details.user_details (id, company) VALUES (6,{name: 'Considine-Lockman', catchPhrase: 'Synchronised bottom-line interface', bs: 'e-enable innovative applications'});