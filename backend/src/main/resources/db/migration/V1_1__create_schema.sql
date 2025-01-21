create table users
(
    user_id bigserial primary key,
    username varchar(100) not null unique,
    email varchar(100) not null unique,
    password varchar(200) not null
);

create table roles
(
    role_id serial primary key,
    name varchar(50) not null
);

create table users_roles
(
    user_id bigint not null references users,
    role_id int not null references roles,
    primary key (user_id, role_id)
);

create table wallets
(
    wallet_id bigserial primary key,
    user_id bigint not null references users,
    balance numeric(10, 2) not null,
    name varchar(100),
    issue_date date not null,
    last_update_date date not null
);

create table transaction_types
(
    type_id serial primary key,
    name varchar(50) not null
);

create table transaction_categories
(
    category_id serial primary key,
    name varchar(100) not null unique,
    description text
);

create table transactions
(
    transaction_id bigserial primary key,
    date date not null,
    type_id int not null references transaction_types,
    wallet_id bigint not null references wallets,
    amount numeric(10, 2) not null,
    category_id int references transaction_categories on delete set null
);