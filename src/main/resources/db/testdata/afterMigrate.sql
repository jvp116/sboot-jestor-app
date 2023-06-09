set foreign_key_checks = 0;

delete from lancamento;
delete from categoria;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table lancamento auto_increment = 1;
alter table categoria auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;

insert ignore into  usuario (nome, email, senha, data_cadastro)
values
    ('João VP', 'joao@gmail.com', '12345678', utc_timestamp);

insert ignore into categoria (descricao, icone, cor, tipo)
values
    ('Renda', 'money', '196E19', 'E'),
    ('Bônus', 'gift', '00A5CF', 'E'),
    ('Vendas', 'shopping_bag', 'D10202', 'E'),
    ('Rendimentos', 'growth_curve', '25A539', 'E'),
    ('Outras entradas', 'others', '175D91', 'E');

insert ignore into categoria (descricao, icone, cor, tipo)
values
    ('Moradia', 'home', 'C46B0F', 'S'),
    ('Saúde', 'heart', '25A539', 'S'),
    ('Educação', 'study', '7F6BB3', 'S'),
    ('Lazer', 'vacation', 'CCB801', 'S'),
    ('Transporte', 'car', '00A5CF', 'S'),
    ('Alimentação', 'food', 'D10202', 'S'),
    ('Compras', 'shopping_cart', '4E196E', 'S'),
    ('Pet', 'pet', '7E4409', 'S'),
    ('Outras saídas', 'others', '175D91', 'S');