DROP TABLE IF EXISTS currency;

CREATE TABLE currency (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  origincurrency VARCHAR(50) NOT NULL,
  destinationcurrency VARCHAR(50) NOT NULL,
  exchangerate NUMBER NOT NULL
);

INSERT INTO currency (origincurrency, destinationcurrency, exchangerate)
VALUES ('soles', 'dolares', 0.2589),
       ('soles', 'euros', 0.2110),
       ('dolares', 'soles', 3.862),
       ('dolares', 'euros', 0.850),
       ('euros', 'soles', 4.750),
       ('euros', 'dolares', 1.150);