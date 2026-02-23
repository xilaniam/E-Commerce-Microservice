-- Ensure the 'product' table exists
CREATE TABLE IF NOT EXISTS product
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255)        NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2)      NOT NULL,
    quantity    INTEGER             NOT NULL
);

-- Insert well-known UUIDs for specific products
INSERT INTO product (id, name, description, price, quantity)
SELECT '323e4567-e89b-12d3-a456-426614174000',
       'Laptop',
       '15-inch business laptop',
       1200.00,
       10
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE id = '323e4567-e89b-12d3-a456-426614174000'
);

INSERT INTO product (id, name, description, price, quantity)
SELECT '323e4567-e89b-12d3-a456-426614174001',
       'Smartphone',
       'Android smartphone with 5G',
       699.99,
       25
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE id = '323e4567-e89b-12d3-a456-426614174001'
);

INSERT INTO product (id, name, description, price, quantity)
SELECT '323e4567-e89b-12d3-a456-426614174002',
       'Headphones',
       'Noise-cancelling wireless headphones',
       199.50,
       40
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE id = '323e4567-e89b-12d3-a456-426614174002'
);

INSERT INTO product (id, name, description, price, quantity)
SELECT '323e4567-e89b-12d3-a456-426614174003',
       'Keyboard',
       'Mechanical gaming keyboard',
       129.99,
       30
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE id = '323e4567-e89b-12d3-a456-426614174003'
);

INSERT INTO product (id, name, description, price, quantity)
SELECT '323e4567-e89b-12d3-a456-426614174004',
       'Mouse',
       'Wireless ergonomic mouse',
       59.99,
       50
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE id = '323e4567-e89b-12d3-a456-426614174004'
);
