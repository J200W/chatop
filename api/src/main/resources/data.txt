use chatop;

DROP TABLE IF EXISTS message;

DROP TABLE IF EXISTS rental;

DROP TABLE IF EXISTS user_role;

DROP TABLE IF EXISTS role;

DROP TABLE IF EXISTS user;

-- Create the user table
CREATE TABLE user
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    email      VARCHAR(250) NOT NULL UNIQUE,
    password   VARCHAR(250) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX `USERS_index` ON user (`email`);

-- Create the role table

CREATE TABLE role
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

-- Create the user_role table

CREATE TABLE user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

-- Create the rental table

CREATE TABLE rental
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50)    NOT NULL,
    description VARCHAR(2000) DEFAULT 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies.  eget cursus nulla tincidunt. ',
    picture     VARCHAR(250) DEFAULT 'https://picsum.photos/500/300',
    price       DECIMAL(10, 2) NOT NULL,
    surface     DECIMAL(10, 1) NOT NULL,
    created_at  DATETIME       NOT NULL,
    updated_at  DATETIME       NOT NULL,
    owner_id    INT            NOT NULL,
    FOREIGN KEY (owner_id)
        REFERENCES user (id)
);

-- Create the message table

CREATE TABLE message
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    content     VARCHAR(250) NOT NULL,
    sender_id   INT          NOT NULL,
    send_at     DATETIME     NOT NULL,
    rental_id   INT          NOT NULL,
    FOREIGN KEY (sender_id)
        REFERENCES user (id),
    FOREIGN KEY (rental_id)
        REFERENCES rental (id)
);

-- Inserting users

-- Inserting users
INSERT INTO user (name, email, password)
VALUES ('John Doe', 'john.doe@example.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
       ('Jane Smith', 'jane.smith@example.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
       ('Alice Johnson', 'alice.johnson@example.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
       ('Bob Brown', 'bob.brown@example.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
       ('Emma Wilson', 'emma.wilson@example.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC'),
       ('Grace Lee', 'grace.lee@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Matthew Clark', 'matthew.clark@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Olivia Martinez', 'olivia.martinez@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('William Rodriguez', 'william.rodriguez@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Sophia Hernandez', 'sophia.hernandez@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Michael Gonzalez', 'michael.gonzalez@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Emily Smith', 'emily.smith@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('James Johnson', 'james.johnson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Ella Anderson', 'ella.anderson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Daniel Martinez', 'daniel.martinez@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Ava Wilson', 'ava.wilson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Alexander Thompson', 'alexander.thompson@example.com',
        '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Isabella Lewis', 'isabella.lewis@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('William Moore', 'william.moore@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('Mia White', 'mia.white@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('James Anderson', 'james.anderson@example.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('dbuser', 'user@gmail.com', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.'),
       ('James Wellington', 'admin@gmail.com', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC');

-- Inserting roles
INSERT INTO role(name)
VALUES ('ROLE_USER'),
       ('ROLE_OWNER'),
       ('ROLE_ADMIN');

-- Assigning roles
INSERT INTO user_role (user_id, role_id)
VALUES (1, 3),  -- Admin
       (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),  -- Owners
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1), -- Basic Users
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (23, 2),
       (23, 3);

-- Inserting rentals owned by the admins

INSERT INTO rental (name, description, picture, price, surface, created_at, updated_at, owner_id)
VALUES ('Cozy Apartment 1', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 1', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 1', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 1', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 1', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 2', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 2', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 2', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 2', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 2', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 3', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 3', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 3', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 3', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 3', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 4', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 4', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 4', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 4', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 4', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 5', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 5', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 5', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 5', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 5', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 6', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 6', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 6', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 6', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 6', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 7', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 7', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 7', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 7', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 7', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
       ('Cozy Apartment 8', 'A cozy apartment located in the city center.', 'https://picsum.photos/400/300', 1000.00,
        75.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       ('Modern Loft 8', 'A modern loft with minimalist design.', 'https://picsum.photos/450/350', 1500.00, 90.0,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       ('Beach House 8', 'A spacious beach house with stunning ocean views.', 'https://picsum.photos/500/400', 2000.00,
        120.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
       ('Mountain Cabin 8', 'A charming cabin nestled in the mountains.', 'https://picsum.photos/420/320', 1200.00,
        80.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
       ('Country Villa 8', 'A luxurious villa surrounded by serene countryside.', 'https://picsum.photos/480/380',
        1800.00, 100.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);

-- Inserting messages from users
INSERT INTO message (content, sender_id, send_at, rental_id)
VALUES ('Hi, I am interested in renting the apartment for a few months.', 6, CURRENT_TIMESTAMP, 1),
       ('What is the policy on subletting the loft?', 7, CURRENT_TIMESTAMP, 2),
       ('Are there any upcoming renovations planned for the beach house?', 8, CURRENT_TIMESTAMP, 3),
       ('Do you offer a discount for long-term stays at the cabin?', 9, CURRENT_TIMESTAMP, 4),
       ('Could you provide information on public transportation near the apartment?', 10, CURRENT_TIMESTAMP, 1),
       ('Is there a gym or fitness center in the building of the loft?', 11, CURRENT_TIMESTAMP, 2),
       ('Are there any restrictions on noise levels at the beach house?', 12, CURRENT_TIMESTAMP, 3),
       ('Is there a fireplace or wood stove in the cabin?', 13, CURRENT_TIMESTAMP, 4),
       ('Can you provide references for previous tenants of the apartment?', 14, CURRENT_TIMESTAMP, 1),
       ('What is the policy on guests staying at the loft overnight?', 15, CURRENT_TIMESTAMP, 2),
       ('Are there any local events or festivals near the beach house?', 16, CURRENT_TIMESTAMP, 3),
       ('Do you provide firewood for the cabin during winter months?', 17, CURRENT_TIMESTAMP, 4),
       ('I am interested in leasing the villa starting next month.', 18, CURRENT_TIMESTAMP, 1),
       ('Is there a balcony or outdoor space at the loft?', 19, CURRENT_TIMESTAMP, 2),
       ('Is the beach house wheelchair accessible?', 20, CURRENT_TIMESTAMP, 3),
       ('Are there any pet-friendly trails near the cabin?', 6, CURRENT_TIMESTAMP, 4),
       ('Could you provide more details on the security deposit for the apartment?', 7, CURRENT_TIMESTAMP, 1),
       ('What is the process for reporting maintenance issues in the loft?', 8, CURRENT_TIMESTAMP, 2),
       ('Are there lifeguards on duty at the beach near the beach house?', 9, CURRENT_TIMESTAMP, 3),
       ('Is there a barbecue grill available for use at the cabin?', 10, CURRENT_TIMESTAMP, 4);

-- Update the default values for the rental table

ALTER TABLE rental
    ALTER COLUMN owner_id SET DEFAULT 1;

ALTER TABLE rental
    MODIFY COLUMN created_at DATETIME DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE rental
    MODIFY COLUMN updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Update the default values for the message table

ALTER TABLE message
    MODIFY COLUMN send_at DATETIME DEFAULT CURRENT_TIMESTAMP;
