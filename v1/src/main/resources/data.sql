-- --------------------
-- WAREHOUSES
-- --------------------
INSERT INTO Warehouses (name, location, capacity) VALUES
('Central Warehouse', '123 Main St, Springfield', 500),
('East Side Warehouse', '456 East Ave, Springfield', 300),
('West Side Warehouse', '789 West Blvd, Springfield', 400);

-- --------------------
-- PRODUCTS
-- --------------------
-- BALLS
INSERT INTO Products (name, warehouse_id, product_type, size, weight, color, price, ballType, brand, length) VALUES
('Soccer Ball', 1, 'BALL', '5', 0.45, 'White/Black', 29.99, 'Soccer', 'Adidas', NULL),
('Basketball', 1, 'BALL', '7', 0.62, 'Orange', 39.99, 'Basketball', 'Spalding', NULL),
('Tennis Ball', 2, 'BALL', NULL, 0.06, 'Yellow', 9.99, 'Tennis', 'Wilson', NULL);

-- BATS
INSERT INTO Products (name, warehouse_id, product_type, size, weight, color, price, brand, length) VALUES
('Baseball Bat', 2, 'BAT', '34in', 0.9, 'Natural', 49.99, 'Rawlings', 34.0),
('Softball Bat', 3, 'BAT', '32in', 0.8, 'Black', 44.99, 'Easton', 32.0);

-- GLOVES
INSERT INTO Products (name, warehouse_id, product_type, size, color, price, brand) VALUES
('Baseball Glove', 1, 'GLOVE', 12, 'Brown', 59.99, 'Wilson'),
('Softball Glove', 2, 'GLOVE', 12, 'Black', 64.99, 'Mizuno');

-- HATS
INSERT INTO Products (name, warehouse_id, product_type, size, color, price, brand) VALUES
('Baseball Cap', 3, 'HAT', 'L/XL', 'Red', 19.99, 'New Era'),
('Sun Hat', 1, 'HAT', 'M', 'Beige', 14.99, 'Columbia');
