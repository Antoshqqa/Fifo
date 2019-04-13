create sequence hibernate_sequence start 1 increment 1;

create table products (
                          id int8 not null unique,
                          name varchar(255),
                          primary key (id)
);

create table purchase (
                          id int8 not null,
                          purchase_id int8 not null,
                          amount int4,
                          buyPrice float8,
                          buyDate date
);

create table demand (
                        id int8 not null,
                        demand_id int8 not null,
                        amount int4,
                        demandPrice float8,
                        demandDate date
);

create table sold (
                      id int8 not null,
                      sold_id int8 not null,
                      profit float8,
                      saleDate date
);


alter table if exists purchase
    add constraint products_purchase_fk
        foreign key (id) references products;

alter table if exists demand
    add constraint products_demand_fk
        foreign key (id) references products;

alter table if exists sold
    add constraint products_sold_fk
        foreign key (id) references products;
