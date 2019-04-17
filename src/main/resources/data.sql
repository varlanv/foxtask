INSERT INTO foxtask.category (name, price) VALUES
  ('STANDARD', '50'),
  ('DOUBLE', '75'),
  ('LUX', '120');


INSERT INTO foxtask.room (number, category_id) VALUES
  ('1', '1'),
  ('5', '3'),
  ('10', '2'),
  ('6', '2'),
  ('15', '2'),
  ('20', '1'),
  ('3', '3'),
  ('25', '1'),
  ('21', '3'),
  ('13', '1'),
  ('19', '2'),
  ('22', '3');


INSERT INTO foxtask.extra_services (service, service_price) VALUES
  ('cleaning', '10'),
  ('breakfast', '5');