-- creation of table of categories
CREATE TABLE IF NOT EXISTS categories
(
    category_id   int primary key generated by default as identity,
    category_name varchar not null unique
);

-- creation of table of manufacturers
CREATE TABLE IF NOT EXISTS manufacturers
(
    manufacturer_id   int primary key generated by default as identity,
    manufacturer_name varchar not null unique
);

-- creation of table of models
CREATE TABLE IF NOT EXISTS models
(
    model_id   int primary key generated by default as identity,
    model_name varchar not null unique
);

-- creation of table of cars
CREATE TABLE IF NOT EXISTS cars
(
    car_id          varchar primary key,
    manufacturer_id int not null,
    year            int not null,
    model_id        int not null,
    category_id     int not null,
    foreign key (manufacturer_id) references manufacturers (manufacturer_id) ON DELETE CASCADE,
    foreign key (model_id) references models (model_id) ON DELETE CASCADE,
    foreign key (category_id) references categories (category_id) ON DELETE CASCADE
);

