


INSERT INTO foxtask.category (name) VALUES
  ('STANDARD'),
  ('DOUBLE'),
  ('LUX');


INSERT INTO foxtask.room (number, category_id, price, available) VALUES
  ('1', '1', '50', true),
  ('2', '3', '120', true),
  ('3', '2', '75', true),
  ('4', '2', '75', true),
  ('5', '2', '75', true),
  ('6', '1', '50', true),
  ('7', '3', '120', true),
  ('8', '1', '50', true),
  ('9', '3', '120', true),
  ('10', '1', '50', true),
  ('11', '2', '75', true),
  ('12', '3', '120', true),
  ('13', '1', '50', true),
  ('14', '3', '120', true),
  ('15', '2', '75', true),
  ('16', '2', '75', true),
  ('17', '2', '75', true),
  ('18', '1', '50', true),
  ('19', '3', '120', true),
  ('20', '1', '50', true);


INSERT INTO foxtask.extra_services (name, price) VALUES
  ('Cleaning', '10'),
  ('Breakfast', '5');