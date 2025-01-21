insert into users (username, email, password)
values ('admin', 'admin@admin.com', '$2a$12$2Vnx6ipp.1PusjmlIEcmauxThm12JvnS8FuI/B34UmxPc/vMJuVWm'),
       ('user1', 'user1@user.com', '$2a$12$.AeaBisljXs1jSQwGed/1eHi1EhlgpP2OXSwTlepqRNQEkQXlfMkO'),
       ('user2', 'user2@user.com', '$2a$12$ET1wtX71amxr1OUxO.dhEuRyEwpiq0okT/PAZgMXNTxZDotI7YUbu'),
       ('user3', 'user3@user.com', '$2a$12$Uap5t4yTsUTTEvmmx.7Yc.KlAJ3OjHDBy19JtCWztFHA83.6DI89G');

insert into users_roles (user_id, role_id)
values (1, 1), (2, 2), (3, 2), (4, 2);

insert into wallets (user_id, balance, name, issue_date, last_update_date)
values
       (1, 100.00, 'Основной', '2023-01-01', '2023-01-10'),
       (1, 50.00, 'Сберегательный', '2023-02-01', '2023-02-10'),

       (2, 200.00, 'Для путешествий', '2023-03-01', '2023-03-15'),
       (2, 75.50, 'Игровой', '2023-04-01', '2023-04-10'),

       (3, 300.00, 'Бизнес-кошелек', '2023-05-01', '2023-05-01'),

       (4, 150.75, 'Экстренный', '2023-06-01', '2023-06-05'),
       (4, 60.25, 'Для ежедневных расходов', '2023-06-10', '2023-06-10');

insert into transactions (date, type_id, wallet_id, amount, category_id)
values
       ('2023-01-05', 1, 1, 20.00, 1),
       ('2023-01-12', 2, 1, 15.00, 2),

       ('2023-02-10', 1, 3, 20.50, 1),
       ('2023-02-15', 2, 3, 5.00, 2),

       ('2023-03-10', 1, 5, 100.00, 4),

       ('2023-04-04', 1, 5, 75.00, 3),
       ('2023-05-01', 1, 6, 50.00, 5),
       ('2023-05-02', 2, 6, 25.00, 4),

       ('2023-05-20', 1, 7, 30.00, 3);