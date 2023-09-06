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

INSERT IGNORE INTO _user (email, password, register_date, role)
VALUES
    ('joao@gmail.com', '$2a$10$FXJrw9MAQ8QmJY7.oXy.ZOcVrvZFveNHDZa.1b42xnEn/V1uwC5Cm', utc_timestamp, 'USER');

INSERT IGNORE INTO category (description, icon, color, type)
VALUES
    ('Alimentação', 'food', 'D10202', 'S'),
    ('Bônus', 'gift', '00A5CF', 'E'),
    ('Compras', 'shopping_cart', '4E196E', 'S'),
    ('Educação', 'study', '7F6BB3', 'S'),
    ('Lazer', 'vacation', 'CCB801', 'S'),
    ('Moradia', 'home', 'C46B0F', 'S'),
    ('Outras entradas', 'others', '175D91', 'E'),
    ('Outras saídas', 'others', '175D91', 'S'),
    ('Pet', 'pet', '7E4409', 'S'),
    ('Renda', 'money', '196E19', 'E'),
    ('Rendimentos', 'growth_curve', '25A539', 'E'),
    ('Saúde', 'heart', '25A539', 'S'),
    ('Transporte', 'car', '00A5CF', 'S'),
    ('Vendas', 'shopping_bag', 'D10202', 'E');

INSERT IGNORE INTO financial_record (value, description, date, category_id, user_id)
VALUES
    ('123.45', 'aaa', DATE '2023-09-01', 1, 1),
    ('100.45', 'aaa', DATE '2023-09-02', 1, 1),
    ('123.45', 'aaa', DATE '2023-08-01', 1, 1),
    ('100.45', 'aaa', DATE '2023-08-02', 1, 1),
    ('456.78', 'bbb', DATE '2023-09-01', 2, 1),
    ('456.78', 'bbb', DATE '2023-08-01', 2, 1),
    ('910.11', 'ccc', DATE '2023-09-02', 3, 1),
    ('910.11', 'ccc', DATE '2023-08-03', 3, 1),
    ('910.11', 'ccc', DATE '2023-08-04', 3, 1),
    ('910.11', 'ccc', DATE '2023-10-04', 4, 1),
    ('910.11', 'ccc', DATE '2023-10-05', 5, 1),
    ('910.11', 'ccc', DATE '2023-10-07', 6, 1),
    ('1213.14', 'ddd', DATE '2023-11-08', 7, 1),
    ('1213.14', 'ddd', DATE '2023-11-18', 8, 1),
    ('1213.14', 'ddd', DATE '2023-12-28', 9, 1),
    ('1516.17', 'eee', DATE '2023-10-30', 10, 1),
    ('1516.17', 'eee',DATE '2023-11-08', 11, 1),
    ('18.19', 'fff', DATE '2023-08-08', 12, 1),
    ('18.19', 'fff', DATE '2023-08-11', 13, 1),
    ('18.19', 'fff', DATE '2023-08-11', 14, 1);