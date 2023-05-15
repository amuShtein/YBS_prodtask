CREATE TABLE "orders"
(
    "id"            serial,
    "regions"       integer,
    "cost"          integer,
    "weight"        double precision,
    "courier"       integer,
    "complete_time" timestamp,
    PRIMARY KEY ("id")
);

CREATE TABLE "couriers"
(
    "id"      serial,
    "type"    varchar(20),
    "regions" integer[],
    PRIMARY KEY ("id")
);

CREATE TABLE "schedule_couriers"
(
    "courier"     int,
    "range_start" timestamp,
    "range_end"   timestamp,
    "id"          serial,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_schedule_couriers.courier"
        FOREIGN KEY ("courier")
            REFERENCES "couriers" ("id")
);

CREATE TABLE "schedule_orders"
(
    order_id       int,
    "range_start" timestamp,
    "range_end"   timestamp,
    "id"          serial,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_schedule_orders.order"
        FOREIGN KEY (order_id)
            REFERENCES "orders" ("id")
);

