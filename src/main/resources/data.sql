INSERT INTO foxtask.category (name, price) VALUES
  ('STANDARD', '50'),
  ('DOUBLE', '75'),
  ('LUX', '120');


INSERT INTO foxtask.room (number, category_id, available) VALUES
  ('1', '1', true),
  ('5', '3', true),
  ('10', '2', true),
  ('6', '2', true),
  ('15', '2', true),
  ('20', '1', true),
  ('3', '3', true),
  ('25', '1', true),
  ('21', '3', true),
  ('13', '1', true),
  ('19', '2', true),
  ('22', '3', true);


INSERT INTO foxtask.extra_services (name, price) VALUES
  ('Cleaning', '10'),
  ('Breakfast', '5');