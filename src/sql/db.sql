-- Truy vấn tạo ra database
create database shoppingonline;

USE shoppingonline;
#Bài 18
-- Truy vấn tạo bảng user
create table Users(
    id INT UNIQxnot null

);
Drop table Users ;
ALTER TABLE users CHANGE ID_USER id int;








create table product(
    ID_PRODUCT INT  UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name varchar(500)not null,
    description text,
    price numeric(18,3) not null,
    stock int,
    original_price numeric(18,3) not null,
    category varchar(255)

);
INSERT INTO product (ID_PRODUCT, name, description, price, stock, original_price, category) VALUES
        (1, 'Tai nghe Bluetooth', NULL, 399000, 0, 499000, 'Âm thanh'),
        (2, 'Bàn phím cơ RGB', NULL, 890000, 20, 1090000, 'Thiết bị'),
        (3, 'Sạc dự phòng 10000mAh', NULL, 299000, 0, 350000, 'Phụ kiện'),
        (14, 'Chuột gaming Razer', NULL, 690000, 30, 850000, 'Thiết bị'),
        (15, 'Ống kính điện thoại', NULL, 110000, 100, 150000, 'Phụ kiện'),
        (16, 'Đồng hồ thông minh', NULL, 1850000, 25, 2090000, 'Thiết bị'),
        (17, 'Tai nghe True Wireless', NULL, 799000, 45, 999000, 'Âm thanh'),
        (18, 'Loa mini Xiaomi', NULL, 340000, 60, 420000, 'Âm thanh'),
        (19, 'Miếng dán cường lực', NULL, 50000, 150, 70000, 'Phụ kiện'),
        (20, 'Cáp sạc USB-C', NULL, 70000, 120, 90000, 'Phụ kiện'),
        (21, 'Balo thời trang', NULL, 320000, 35, 390000, 'Phụ kiện'),
        (22, 'Quạt mini USB', NULL, 150000, 80, 190000, 'Thiết bị'),
        (23, 'Sổ tay da A5', NULL, 130000, 90, 180000, 'Phụ kiện'),
        (24, 'Bàn gập học sinh', NULL, 390000, 25, 490000, 'Nội thất'),
        (25, 'Chuột không dây Apple', NULL, 1250000, 0, 1390000, 'Thiết bị'),
        (26, 'Kính chống ánh sáng xanh', NULL, 230000, 100, 290000, 'Phụ kiện'),
        (27, 'Hub chuyển USB-C to HDMI', NULL, 520000, 70, 620000, 'Phụ kiện'),
        (28, 'Máy tính cầm tay Casio', NULL, 450000, 40, 490000, 'Thiết bị'),
        (29, 'Loa di động Bose Mini', NULL, 2790000, 15, 3290000, 'Âm thanh'),
        (30, 'Cân điện tử thông minh', NULL, 880000, 0, 990000, 'Thiết bị'),
        (31, 'Sách lập trình Python', NULL, 210000, 80, 259000, 'Sách'),
        (32, 'Khăn thể thao gym', NULL, 95000, 100, 125000, 'Thể thao'),
        (33, 'Vỏ laptop 14 inch', NULL, 210000, 75, 290000, 'Phụ kiện'),
        (34, 'Túi giữ nhiệt mini', NULL, 180000, 60, 220000, 'Gia dụng'),
        (35, 'Máy tạo độ ẩm USB', NULL, 330000, 0, 410000, 'Thiết bị'),
        (36, 'Giá đỡ laptop nhôm', NULL, 430000, 50, 520000, 'Thiết bị'),
        (37, 'Bộ vệ sinh laptop 5 món', NULL, 170000, 0, 210000, 'Phụ kiện'),
        (38, 'Đèn cảm biến chuyển động', NULL, 310000, 40, 360000, 'Thiết bị'),
        (39, 'Ổ cứng SSD 512GB', NULL, 1450000, 25, 1650000, 'Thiết bị'),
        (40, 'Bình giữ nhiệt Lock&Lock', NULL, 290000, 0, 350000, 'Gia dụng'),
        (41, 'Sạc nhanh 33W Xiaomi', NULL, 390000, 50, 450000, 'Phụ kiện'),
        (42, 'Áo mưa tiện lợi', NULL, 50000, 200, 70000, 'Tiện ích'),
        (43, 'Bàn phím Bluetooth mini', NULL, 540000, 40, 650000, 'Thiết bị');






create table carts(
    ID_CART INT AUTO_INCREMENT PRIMARY KEY,
    ID_USER INT,
    FOREIGN KEY (ID_USER) REFERENCES Users(ID_USER)
);



INSERT INTO carts (ID_CART, ID_USER) VALUES
                                    (1, 1),
                                    (2, 2),
                                    (13, 14),
                                    (14, 15),
                                    (15, 16),
                                    (16, 17),
                                    (17, 18),
                                    (18, 19),
                                    (19, 20),
                                    (20, 21),
                                    (21, 22),
                                    (22, 23),
                                    (23, 24),
                                    (24, 25),
                                    (25, 26),
                                    (26, 27),
                                    (27, 28),
                                    (28, 29),
                                    (29, 30),
                                    (30, 31),
                                    (31, 32),
                                    (32, 33),
                                    (33, 34),
                                    (34, 35),
                                    (35, 36),
                                    (36, 37),
                                    (37, 38),
                                    (38, 39),
                                    (39, 40),
                                    (40, 41),
                                    (41, 42),
                                    (42, 43);





create table cart_items
(
    ID          INT AUTO_INCREMENT PRIMARY KEY,
    ID_PRODUCT INT,
    QUANTITY    INT,
    ID_CART     INT,
    FOREIGN KEY (ID_CART) REFERENCES carts(ID_CART),
    FOREIGN KEY (ID_PRODUCT) REFERENCES product(ID_PRODUCT) ON DELETE CASCADE #ON DELETE CASCADE này sẽ xoá tất cả những j liên quan khi bên product bị xoá đi 1 instance nào đó


);



INSERT INTO cart_items (id,ID_PRODUCT, quantity, ID_CART) VALUES
            (1, 1, 2, 1),
            (2, 2, 1, 1),
            (3, 3, 3, 2),
            (14, 14, 1, 13),
            (15, 15, 2, 14),
            (16, 16, 1, 15),
            (17, 17, 1, 16),
            (18, 19, 3, 17),
            (19, 21, 1, 18),
            (20, 18, 2, 19),
            (21, 20, 1, 20),
            (22, 22, 1, 21),
            (23, 23, 4, 22),
            (24, 24, 1, 23),
            (25, 25, 2, 24),
            (26, 26, 1, 25),
            (27, 28, 1, 26),
            (28, 30, 2, 27),
            (29, 33, 1, 28),
            (30, 36, 1, 29),
            (31, 35, 2, 30),
            (32, 29, 1, 31),
            (33, 34, 3, 32),
            (34, 31, 1, 33),
            (35, 37, 1, 34),
            (36, 38, 1, 35),
            (37, 39, 2, 36),
            (38, 41, 2, 37),
            (39, 40, 1, 38),
            (40, 43, 1, 39),
            (41, 27, 1, 40),
            (42, 42, 1, 41),
            (43, 32, 3, 42);






create table orders(
    ID_ORDER INT AUTO_INCREMENT PRIMARY KEY,
    DESCRIPTION VARCHAR(150),
    STATUS VARCHAR(150),
    TOTAL_AMOUNT NUMERIC(19,6),
    USER_ID INT

);

INSERT INTO orders (ID_ORDER, description, status, total_amount, user_id) VALUES
          (1, 'Đơn hàng tháng 7', 'delivered', 1688000, 1),
          (2, NULL, 'processing', 897000, 2),
          (13, 'Đơn hàng Flash Sale', 'delivered', 890000, 14),
          (14, NULL, 'processing', 220000, 15),
          (15, 'Đơn combo phụ kiện', 'pending', 190000, 16),
          (16, NULL, 'delivered', 799000, 17),
          (17, NULL, 'shipped', 150000, 18),
          (18, 'Ưu đãi sinh viên', 'delivered', 320000, 19),
          (19, NULL, 'processing', 680000, 20),
          (20, NULL, 'shipped', 70000, 21),
          (21, NULL, 'delivered', 150000, 22),
          (22, 'Tặng phẩm', 'processing', 520000, 23),
          (23, 'Thanh toán học cụ', 'delivered', 450000, 24),
          (24, NULL, 'processing', 1250000, 25),
          (25, 'Giao dịch phụ kiện', 'pending', 710000, 26),
          (26, NULL, 'delivered', 450000, 27),
          (27, 'Thiết bị gia dụng', 'shipped', 880000, 28),
          (28, 'Flash sale mùa hè', 'delivered', 330000, 29),
          (29, NULL, 'processing', 210000, 30),
          (30, NULL, 'shipped', 2790000, 31),
          (31, NULL, 'delivered', 290000, 32),
          (32, 'Đơn hàng thể thao', 'processing', 95000, 33),
          (33, NULL, 'shipped', 1450000, 34),
          (34, NULL, 'processing', 210000, 35),
          (35, NULL, 'delivered', 540000, 36),
          (36, NULL, 'processing', 390000, 37),
          (37, NULL, 'shipped', 170000, 38),
          (38, NULL, 'pending', 50000, 39),
          (39, NULL, 'delivered', 430000, 40),
          (40, NULL, 'delivered', 990000, 41),
          (41, NULL, 'shipped', 390000, 42),
          (42, NULL, 'delivered', 220000, 43);



DROP table product;
DROP table cart_items;
drop table oders_items;
create table oders_items(
    id INT AUTO_INCREMENT PRIMARY KEY,
    ID_PRODUCT INT,
    ID_ORDER INT,
    ORIGINAL_PRICE NUMERIC(19,3),
    PRICE NUMERIC(19,3),
    FOREIGN KEY (ID_PRODUCT) REFERENCES product (ID_PRODUCT) ON DELETE CASCADE,
    FOREIGN KEY (ID_ORDER) REFERENCES orders(ID_ORDER)
);

INSERT INTO oders_items (id, ID_PRODUCT, ID_ORDER, original_price, price) VALUES
            (1, 1, 1, 499000, 399000),
            (2, 2, 1, 1090000, 890000),
            (3, 3, 2, 350000, 299000),
            (14, 14, 13, 850000, 690000),
            (15, 15, 14, 150000, 110000),
            (16, 19, 15, 70000, 50000),
            (17, 17, 16, 999000, 799000),
            (18, 22, 17, 190000, 150000),
            (19, 21, 18, 390000, 320000),
            (20, 16, 19, 2090000, 1850000),
            (21, 20, 20, 90000, 70000),
            (22, 18, 21, 420000, 340000),
            (23, 23, 22, 180000, 130000),
            (24, 20, 23, 300000, 250000),
            (25, 21, 24, 1200000, 950000),
            (26, 22, 25, 250000, 180000),
            (27, 23, 26, 499000, 399000),
            (28, 24, 27, 129000, 99000),
            (29, 25, 28, 590000, 490000),
            (30, 26, 29, 390000, 320000),
            (31, 27, 30, 1090000, 890000),
            (32, 28, 31, 180000, 120000),
            (33, 29, 32, 299000, 220000),
            (34, 17, 33, 999000, 799000),
            (35, 16, 34, 2090000, 1850000),
            (36, 15, 35, 150000, 110000),
            (37, 14, 36, 850000, 690000),
            (38, 19, 37, 70000, 50000),
            (39, 20, 38, 90000, 70000),
            (40, 18, 39, 420000, 340000),
            (41, 21, 40, 390000, 320000),
            (42, 22, 41, 190000, 150000),
            (43, 23, 42, 180000, 130000);




#alter table oders_items  add column id INT;

DROP TABLE product ;
TRUNCATE TABLE carts;
DROP TABLE carts;

#Bài 19 (bt trong slide)
create database Test;
USE Test;

CREATE TABLE Member (
                        CardNo VARCHAR(5) PRIMARY KEY  NOT NULL,
                        Surname VARCHAR(15) NOT NULL,
                        Name VARCHAR(15) NOT NULL,
                        Address VARCHAR(50) NOT NULL,
                        Birthday_Date DATE NOT NULL,
                        Gender CHAR(1) CHECK(Gender IN ('M', 'F')),# là CHECK sẽ chỉ cho cột gender được phép insert 2 kí tự là  'M' và 'F'.
                        Phone_No VARCHAR(15) NOT NULL

);

INSERT INTO Member (CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No) VALUES
                                                                                         ('M0001', 'Nguyen', 'Van A', '123 Main Street', '1990-01-15', 'M', '0123456789'),
                                                                                         ('M0002', 'Tran', 'Thi B', '456 Oak Avenue', '1985-05-20', 'F', '0987654321'),
                                                                                         ('M0003', 'Le', 'Van C', '789 Elm Lane', '1992-09-10', 'M', '0112233445'),
                                                                                         ('M0004', 'Pham', 'Thi D', '987 Pine Road', '1988-03-25', 'F', '0765432109'),
                                                                                         ('M0005', 'Hoang', 'Van E', '654 Cedar Street', '1995-07-12', 'M', '0345678901'),
                                                                                         ('M0006', 'Do', 'Thi F', '321 Birch Blvd', '1983-11-05', 'F', '0556677889'),
                                                                                         ('M0007', 'Truong', 'Van G', '876 Maple Ave', '1998-02-18', 'M', '0234567890'),
                                                                                         ('M0008', 'Nguyen', 'Thi H', '1234 Oak St', '1980-06-30', 'F', '0888777666'),
                                                                                         ('M0009', 'Le', 'Van I', '456 Elm Lane', '1993-12-08', 'M', '0999888777'),
                                                                                         ('M0010', 'Pham', 'Thi K', '765 Pine Road', '1987-04-22', 'F', '0444333222');

CREATE TABLE Employee (
                          Emp_Id INT AUTO_INCREMENT PRIMARY KEY,
                          Surname VARCHAR(15) NOT NULL,
                          Name VARCHAR(15) NOT NULL,
                          Birthday_Date DATE NOT NULL, #Date là kiểu ngày thôi.
                          Emp_Date timestamp #timestamp là kiểu giữ liệu ngày và giờ cụ thể.
);

INSERT INTO Employee (Surname, Name, Birthday_Date, Emp_Date) VALUES
                                                                  ('Tran', 'Van A', '1985-03-10', '2022-01-01 08:00:00'),
                                                                  ('Nguyen', 'Thi B', '1990-07-25', '2022-02-15 09:30:00'),
                                                                  ('Hoang', 'Van C', '1983-11-18', '2022-03-20 10:45:00'),
                                                                  ('Le', 'Thi D', '1992-05-01', '2022-04-10 11:15:00'),
                                                                  ('Do', 'Van E', '1992-09-14', '2022-05-05 13:30:00'),
                                                                  ('Pham', 'Thi F', '1995-01-28', '2022-06-22 13:45:00'),
                                                                  ('Truong', 'Van G', '1987-03-08', '2022-07-15 14:20:00'),
                                                                  ('Nguyen', 'Thi H', '1998-12-03', '2022-08-15 15:00:00'),
                                                                  ('Le', 'Van I', '1987-04-10', '2022-09-08 16:10:00'),
                                                                  ('Pham', 'Thi K', '1992-08-22', '2022-10-05 17:25:00');

CREATE TABLE Publisher (
                           Pub_Id INT AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(50) NOT NULL,
                           City VARCHAR(50) NOT NULL,
                           Phone_No VARCHAR(15) NOT NULL
);

INSERT INTO Publisher (Name, City, Phone_No) VALUES
                                                 ('ABC Publications', 'Hanoi', '0123456789'),
                                                 ('XYZ Publishing', 'Ho Chi Minh City', '0987654321'),
                                                 ('PQR Books', 'Da Nang', '0112233445'),
                                                 ('LMN Press', 'Hue', '0765432109'),
                                                 ('OPQ Printers', 'Can Tho', '0345678901'),
                                                 ('RST Publishers', 'Nha Trang', '0556677889'),
                                                 ('UVW Books', 'Vung Tau', '0234567890'),
                                                 ('HIJ Press', 'Phan Thiet', '0888777666'),
                                                 ('EFG Publishers', 'Bien Hoa', '0999888777'),
                                                 ('KLM Printing', 'Long Xuyen', '0444333222');


CREATE TABLE Book (
                      Book_Id VARCHAR(5) NOT NULL,
                      Type VARCHAR(20) CHECK(Type IN ('novel', 'historical', 'for kids', 'poems', 'crime story', 'science fiction', 'science')),
                      Price DECIMAL(8, 3),
                      Title VARCHAR(255) NOT NULL,
                      Pub_Id INT NOT NULL,
                      PRIMARY KEY (Book_Id),
                      FOREIGN KEY (Pub_Id) REFERENCES Publisher(Pub_Id)
);

INSERT INTO Book (Book_Id, Type, Price, Title, Pub_Id) VALUES
                                                           ('B0001', 'novel', 25.99, 'The Lost City', 1),
                                                           ('B0002', 'historical', 19.95, 'Ancient Chronicles', 2),
                                                           ('B0003', 'for kids', 12.50, 'Adventure in Wonderland', 3),
                                                           ('B0004', 'poems', 15.75, 'Whispers of the Heart', 4),
                                                           ('B0005', 'crime story', 30.50, 'Midnight Shadows', 5),
                                                           ('B0006', 'science fiction', 22.99, 'Galactic Odyssey', 6),
                                                           ('B0007', 'science', 18.75, 'The Quantum Universe', 3),
                                                           ('B0008', 'novel', 27.50, 'Eternal Love', 8),
                                                           ('B0009', 'historical', 21.95, 'The Silk Road', 9),
                                                           ('B0010', 'for kids', 14.99, 'Magic Kingdom', 10),
                                                           ('B0011', 'novel', 26.50, 'Beyond the Horizon', 1),
                                                           ('B0012', 'poems', 14.25, 'Voices in the Wind', 3),
                                                           ('B0013', 'science fiction', 23.75, 'Time Travelers', 3),
                                                           ('B0014', 'crime story', 32.00, 'Underworld Secrets', 4),
                                                           ('B0015', 'historical', 20.50, 'In the Shadows of Time', 5),
                                                           ('B0016', 'novel', 28.75, 'Uncharted Destinies', 6),
                                                           ('B0017', 'for kids', 16.99, 'Enchanted Forest', 2),
                                                           ('B0018', 'science', 24.50, 'Exploring the Cosmos', 8),
                                                           ('B0019', 'novel', 23.15, 'Infinite Dreams', 3),
                                                           ('B0020', 'poems', 16.50, 'Silent Whispers', 6);

CREATE TABLE Book_Invoice (
                              Invoice_No INT AUTO_INCREMENT PRIMARY KEY,
                              Due_Date timestamp,
                              Date_Out timestamp,
                              Penalty DECIMAL(8, 3) DEFAULT 0,
                              CardNo VARCHAR(5),
                              Emp_Id INT,
                              Book_Id VARCHAR(5) UNIQUE,
                              FOREIGN KEY (CardNo) REFERENCES Member(CardNo),
                              FOREIGN KEY (Emp_Id) REFERENCES Employee(Emp_Id),
                              FOREIGN KEY (Book_Id) REFERENCES Book(Book_Id),
                              CHECK (Date_Out < Due_Date)
);

INSERT INTO Book_Invoice (Due_Date, Date_Out, CardNo, Emp_Id, Book_Id) VALUES
                                                                           ('2023-01-15 17:00:00', '2023-01-05 14:30:00', 'M0001', 1, 'B0001'),
                                                                           ('2023-02-20 18:30:00', '2023-02-10 11:45:00', 'M0002', 2, 'B0002'),
                                                                           ('2023-03-10 15:15:00', '2023-03-01 10:00:00', 'M0003', 3, 'B0003'),
                                                                           ('2023-04-25 16:45:00', '2023-04-15 13:20:00', 'M0004', 4, 'B0004'),
                                                                           ('2023-05-12 17:00:00', '2023-05-02 09:30:00', 'M0005', 5, 'B0005'),
                                                                           ('2023-06-22 14:30:00', '2023-06-12 12:15:00', 'M0006', 6, 'B0006'),
                                                                           ('2023-08-18 16:15:00', '2023-08-08 15:45:00', 'M0007', 7, 'B0007'),
                                                                           ('2023-10-30 18:00:00', '2023-10-20 11:30:00', 'M0008', 8, 'B0008'),
                                                                           ('2023-12-25 20:00:00', '2023-12-15 18:30:00', 'M0009', 9, 'B0009'),
                                                                           ('2024-01-15 17:30:00', '2024-01-05 15:15:00', 'M0010', 10, 'B0010'),
                                                                           ('2024-03-10 14:15:00', '2024-03-01 09:45:00', 'M0001', 1, 'B0011'),
                                                                           ('2024-04-25 16:45:00', '2024-04-15 13:20:00', 'M0002', 2, 'B0012'),
                                                                           ('2024-05-12 17:00:00', '2024-05-02 09:15:00', 'M0003', 3, 'B0013'),
                                                                           ('2024-06-22 14:00:00', '2024-06-12 11:30:00', 'M0004', 4, 'B0014'),
                                                                           ('2024-08-18 16:15:00', '2024-08-08 15:45:00', 'M0005', 5, 'B0015'),
                                                                           ('2024-10-30 17:15:00', '2024-10-20 13:45:00', 'M0006', 6, 'B0016'),
                                                                           ('2024-12-25 19:30:00', '2024-12-15 18:00:00', 'M0007', 7, 'B0017'),
                                                                           ('2025-01-15 17:00:00', '2025-01-05 14:30:00', 'M0008', 8, 'B0018'),
                                                                           ('2025-02-18 18:30:00', '2025-02-08 11:15:00', 'M0009', 3, 'B0020');







