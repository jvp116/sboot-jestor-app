SET foreign_key_checks = 0;

DELETE FROM _user;
DELETE FROM token;
DELETE FROM financial_record;
DELETE FROM category;

SET foreign_key_checks = 1;

ALTER TABLE _user auto_increment = 1;
ALTER TABLE token auto_increment = 1;
ALTER TABLE financial_record auto_increment = 1;
ALTER TABLE category auto_increment = 1;

INSERT IGNORE INTO  _user (email, password, register_date, role)
VALUES
    ('joao@gmail.com', '$2a$10$FXJrw9MAQ8QmJY7.oXy.ZOcVrvZFveNHDZa.1b42xnEn/V1uwC5Cm', utc_timestamp, 'USER');

INSERT IGNORE INTO category (description, icon, color, type)
VALUES
    ('Renda', 'money', '196E19', 'E'),
    ('Bônus', 'gift', '00A5CF', 'E'),
    ('Vendas', 'shopping_bag', 'D10202', 'E'),
    ('Rendimentos', 'growth_curve', '25A539', 'E'),
    ('Outras entradas', 'others', '175D91', 'E');

INSERT IGNORE INTO category (description, icon, color, type)
VALUES
    ('Moradia', 'home', 'C46B0F', 'S'),
    ('Saúde', 'heart', '25A539', 'S'),
    ('Educação', 'study', '7F6BB3', 'S'),
    ('Lazer', 'vacation', 'CCB801', 'S'),
    ('Transporte', 'car', '00A5CF', 'S'),
    ('Alimentação', 'food', 'D10202', 'S'),
    ('Compras', 'shopping_cart', '4E196E', 'S'),
    ('Pet', 'pet', '7E4409', 'S'),
    ('Outras saídas', 'others', '175D91', 'S');

INSERT IGNORE INTO financial_record (value, description, date, category_id, user_id)
VALUES
    ('111', 'aaa', utc_timestamp, 1, 1),
    ('222', 'bbb', utc_timestamp, 2, 1),
    ('333', 'ccc', utc_timestamp, 3, 1),
    ('444', 'ddd', utc_timestamp, 12, 1),
    ('555', 'eee', utc_timestamp, 13, 1),
    ('666', 'fff', utc_timestamp, 14, 1);