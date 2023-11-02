CREATE TABLE customer (
    id INT,
    full_name VARCHAR(255),
    gender VARCHAR(10),
    email VARCHAR(255),
    date_time VARCHAR(50)
);

CREATE TABLE outbound_item (
    id INT,
    item_name VARCHAR(255),
    vendor VARCHAR(255),
    unit_price DECIMAL(13,2),
    qty INT,
    date_time VARCHAR(50),
    cus_id INT
);