-- https://www.delish.com/food/a42612/dunkin-donuts-classic-donuts-ranked/
-- https://www.delish.com/food-news/g20980027/best-krispy-kreme-donuts-ranked/

create schema donuts;

set schema donuts;

create table donut_shop_detail
(
    detail_id    int generated always as identity
        constraint donut_shop_detail_pk
            primary key,
    year_founded int          not null,
    hometown     varchar(100) not null
);

create table donut_shop
(
    shop_id   int generated always as identity
        constraint shop_pk
            primary key,
    nm        varchar(30) not null,
    detail_id int references donut_shop_detail (detail_id)
);

create table image
(
    image_id int generated always as identity
        constraint image_pk
            primary key,
    nm      varchar(100) not null,
    mime_type   varchar(100)  not null,
    file blob not null
);

create table donut
(
    donut_id   int generated always as identity
        constraint donut_pk
            primary key,
    shop_id    int references donut_shop (shop_id),
    nm         varchar(30) not null,
    calories   int         not null,
    image_id   int references image (image_id),
    date_added date
);

create table donut_review
(
    review_id   int generated always as identity
        constraint donut_review_pk
            primary key,
    donut_id    int references donut (donut_id),
    review      varchar(500) not null,
    stars       double       not null,
    review_date date
);

create table city
(
    city_id int generated always as identity
        constraint city_pk
            primary key,
    nm      varchar(50) not null,
    state   varchar(2)  not null
);

create table donut_shop_city
(
    city_id int not null references city (city_id),
    shop_id int not null references donut_shop (shop_id),
    primary key (city_id, shop_id)
);

insert into city (nm, state)
values ('Waukesha', 'WI');

insert into city (nm, state)
values ('West Allis', 'WI');

insert into city (nm, state)
values ('San Antonio', 'TX');

insert into city (nm, state)
values ('Little Rock', 'AR');

insert into city (nm, state)
values ('Chicago', 'IL');

insert into donut_shop(nm)
values ('Dunkin Donuts');

insert into donut_shop(nm)
values ('Krispy Kreme');

insert into donut (shop_id, nm, calories, date_added)
VALUES (1, 'Glazed Donut', 260, '10/01/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (1,
        'The Zeus on the mountaintop of donuts, Dunkin''s version of glazed is pretty great. It has a nice distribution of glaze, a fluffy texture, and is not too greasy. We won''t call it perfect, but there''s a reason this glazed donut is the baseline all other Dunkin donuts are measured against.',
        4.9, '10/27/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (1, 'French Cruller', 220, '10/28/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (2,
        'Like funnel cake in donut form, this favorite is often sold out of Dunkin'' locations. Perfectly sweet with a great texture, we also love to pull the layers apart to make sure we are getting maximum sugar in every bite.',
        4.7, '10/27/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (1, 'Sour Cream', 360, '10/22/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (3,
        'This was unexpected. In fact, it had us reconsidering our life choices because we clearly hadn''t been eating enough sour cream donuts. Cake-y, perfectly frosted and with a unique texture, consider us converts.',
        4.6, '10/26/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (1, 'Bavarian Kreme-Filled', 270, '10/22/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (4,
        'We had low expectations for this one, but surprisingly it was inoffensive—not great, not terrible, mediocre to its cream-filled core.',
        3.2, '10/23/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (1, 'Jelly', 280, '10/30/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (5,
        'One taster remarked that the jelly in this confection tasted like a pudding cup. With glops of fake-tasting jelly spilling out, we''re confident that was not a compliment.',
        2.9, '10/24/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (2, 'Original Glazed', 190, '10/17/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (6, 'There''s nothing in the world better than a hot-out-of-the-oven original glazed donut.', 4.9, '10/28/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (2, 'Chocolate Iced Glazed', 240, '10/11/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (7,
        'While it might not seem like it from things like pickle-wrapped hot dogs, we''re apparently purists over here at Delish. One editor recalls a time before these existed, and called their existence a gamechanger. Big talk, but they are pretty darn great.',
        4.1, '10/27/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (2, 'Cake Batter', 360, '10/14/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (8,
        'Personally I thought this was even sweeter than the Chips Ahoy, but a lot of other editors were into it. The filling tastes like actual cake batter, and the sprinkles don''t hurt.',
        3.8, '10/22/2019');

insert into donut (shop_id, nm, calories, date_added)
VALUES (2, 'Apple Fritter', 350, '10/03/2019');

insert into donut_review(donut_id, review, stars, review_date)
values (9,
        'This donut is thick, doughy, and equal parts apple flavored and cinnamon-y. It''s almost in a different category than the other donuts, but as far as flavor, it''s solidly in the middle.',
        3.2, '10/21/2019');

insert into donut_shop_detail (year_founded, hometown)
values (1950, 'Canton, MA');

insert into donut_shop_detail (year_founded, hometown)
values (1937, 'Winston-Salem, NC');

update DONUT_SHOP
set detail_id = 1
where SHOP_ID = 1;

update DONUT_SHOP
set detail_id = 2
where SHOP_ID = 2;

insert into donut_shop_city (city_id, shop_id)
values (1, 1);

insert into donut_shop_city (city_id, shop_id)
values (2, 1);

insert into donut_shop_city (city_id, shop_id)
values (3, 1);

insert into donut_shop_city (city_id, shop_id)
values (4, 1);

insert into donut_shop_city (city_id, shop_id)
values (5, 1);

insert into donut_shop_city (city_id, shop_id)
values (2, 2);

insert into donut_shop_city (city_id, shop_id)
values (3, 2);

insert into donut_shop_city (city_id, shop_id)
values (5, 2);