insert into categories (category_name)
VALUES ('category_1'),
       ('category_2'),
       ('category_3'),
       ('category_4'),
       ('category_5');

insert into manufacturers (manufacturer_name)
VALUES ('manufacturer_1'),
       ('manufacturer_2'),
       ('manufacturer_3'),
       ('manufacturer_4'),
       ('manufacturer_5');

insert into models (model_name)
VALUES ('model_1'),
       ('model_2'),
       ('model_3'),
       ('model_4'),
       ('model_5');

insert into cars (year, manufacturer_id, model_id, category_id)
VALUES (2022, 1, 1, 1),
       (2021, 2, 2, 2),
       (2020, 3, 3, 3),
       (2019, 4, 4, 4),
       (2018, 5, 5, 5);

