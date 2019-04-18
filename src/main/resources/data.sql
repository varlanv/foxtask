INSERT INTO foxtask.category (name) VALUES
  ('STANDARD'),
  ('DOUBLE'),
  ('LUX');


INSERT INTO foxtask.room (number, category_id, price, available) VALUES
  ('1', '1', '50', true),
  ('5', '3', '120', true),
  ('10', '2', '75', true),
  ('6', '2', '75', true),
  ('15', '2', '75', true),
  ('20', '1', '50', true),
  ('3', '3', '120', true),
  ('25', '1', '50', true),
  ('21', '3', '120', true),
  ('13', '1', '50', true),
  ('19', '2', '75', true),
  ('22', '3', '120', true);


INSERT INTO foxtask.extra_services (name, price) VALUES
  ('Cleaning', '10'),
  ('Breakfast', '5');