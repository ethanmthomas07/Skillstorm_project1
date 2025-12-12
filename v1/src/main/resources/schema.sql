/*
used by h2 during application startup 

define the schema for your data 


*/

/* DROP THE TABLES if they exist in memory on project start */

drop table if exists Products;
drop table if exists Warehouses;

-- --------------------
-- WAREHOUSE TABLE
-- --------------------
CREATE TABLE Warehouses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(150),
    capacity INT NOT NULL
    
);

-- --------------------
-- PRODUCT TABLE
-- --------------------
CREATE TABLE Products (
    id INT PRIMARY KEY AUTO_INCREMENT,

    -- Generic product fields
    name VARCHAR(100) NOT NULL,

    -- Relationship to Warehouse
    warehouse_id INT,
    CONSTRAINT FK_WAREHOUSE FOREIGN KEY (warehouse_id) REFERENCES WAREHOUSES(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    -- Subtype handling
    product_type VARCHAR(50) NOT NULL CHECK (product_type IN (
        'BALL', 'BAT', 'GLOVE', 'HAT'
    )),

    -- Subtype-specific optional fields
    size VARCHAR(50),
    weight DECIMAL(10,2),
    color VARCHAR(50),
    price DECIMAL(10,2),
    ballType VARCHAR(50),
    brand VARCHAR(50),
    length DECIMAL(10,2),

    created_at TIMESTAMP DEFAULT NOW()
);

