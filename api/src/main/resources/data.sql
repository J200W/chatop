DROP TABLE IF EXISTS "message";
DROP TABLE IF EXISTS "picture";
DROP TABLE IF EXISTS "rental";
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS "role";

CREATE TABLE "role" (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(20) NOT NULL
);

CREATE TABLE "user" (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(250) NOT NULL, 
    password VARCHAR(250) NOT NULL,
    role INT NOT NULL,
    FOREIGN KEY (role) REFERENCES "role"(id)
);

CREATE TABLE "rental" (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    surface DECIMAL(10, 2) NOT NULL,
    owner_id INT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES "user"(id)
);

CREATE TABLE "picture" (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    url VARCHAR(250) NOT NULL,
    rental_id INT NOT NULL,
    FOREIGN KEY (rental_id) REFERENCES "rental"(id)
);

CREATE TABLE "message" (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    content VARCHAR(250) NOT NULL,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES "user"(id),
    FOREIGN KEY (receiver_id) REFERENCES "user"(id)
);

INSERT INTO
    "role" (name)
VALUES 
    ('ADMIN'),
    ('USER');

INSERT INTO
    "user" (name, password, role)
VALUES 
    ('dbuser', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 2),
    ('dbadmin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 1);
