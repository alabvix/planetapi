DROP TABLE IF EXISTS planet;

CREATE TABLE planet (
   id UUID NOT NULL,
   name VARCHAR(50) NOT NULL,
   x NUMERIC(10,2) NOT NULL,
   y NUMERIC(10,2) NOT NULL,
   z NUMERIC(10,2) NOT NULL
);

INSERT INTO planet (id, name, x, y, z) VALUES
  ('3930f3f2-8b36-11ec-a8a3-0242ac120002', 'Earth', 1.70, 80.00, 27),
  ('5ad40044-8b36-11ec-a8a3-0242ac120002', 'Mars', 1.65, 65.00, 12);