SET foreign_key_checks = 0;

DELETE FROM lancamento;
DELETE FROM categoria;
DELETE FROM usuario;

SET foreign_key_checks = 1;

ALTER TABLE lancamento auto_increment = 1;
ALTER TABLE categoria auto_increment = 1;
ALTER TABLE usuario auto_increment = 1;

INSERT IGNORE INTO  usuario (email, senha, data_cadastro)
VALUES
    ('joao@gmail.com', '12345678', utc_timestamp);

INSERT IGNORE INTO categoria (descricao, icone, cor, tipo)
VALUES
    ('Renda', 'money', '196E19', 'E'),
    ('Bônus', 'gift', '00A5CF', 'E'),
    ('Vendas', 'shopping_bag', 'D10202', 'E'),
    ('Rendimentos', 'growth_curve', '25A539', 'E'),
    ('Outras entradas', 'others', '175D91', 'E');

INSERT IGNORE INTO categoria (descricao, icone, cor, tipo)
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