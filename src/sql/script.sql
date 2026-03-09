#bài 18
SELECT Users.ID_USER , Users.fullname,SUM(product.price * cart_items.quantity) as total_carts
FROM users
JOIN carts ON carts.ID_USER = users.ID_USER
JOIN cart_items ON carts.ID_CART = cart_items.ID_CART
JOIN product ON cart_items.ID_PRODUCT = product.ID_PRODUCT
GROUP BY Users.ID_USER, Users.fullname;

select MIN(TOTAL_AMOUNT)  from orders;



SELECT users.ID_USER, users.FULLNAME, SUM(orders.TOTAL_AMOUNT) AS TOTAL_AMOUNT
FROM users
JOIN orders ON orders.USER_ID = users.ID_USER
GROUP BY users.ID_USER, users.FULLNAME
HAVING SUM(orders.TOTAL_AMOUNT) > 500000;#lọc ra những total_amount ở bảng order có giá trị trên 500000

select min(total_amount) from orders;

SELECT  * FROM PRODUCT WHERE stock = 30;

SELECT users.ID_USER ,users.FULLNAME,SUM(cart_items.QUANTITY) AS items_quantity
FROM users
JOIN carts ON carts.ID_USER = users.ID_USER
JOIN cart_items on cart_items.ID_CART = carts.ID_CART
group by users.ID_USER, users.FULLNAME;

SELECT product.ID_PRODUCT,product.stock,SUM((product.ORIGINAL_PRICE - product.PRICE) * product.stock) as profit
FROM product
GROUP BY product.ID_PRODUCT,product.stock;



SELECT product.category, COUNT(*)# count(*) đếm số lượng ban ghi trong cùng 1 nhóm
FROM product
GROUP BY product.category;#gom nhóm tất cả các cái cùng loại ở cột category ở bang product về cùng một nhóm

#Buổi 19demo

Select * from Book;
Select Name, Address, Gender from member where Gender = 'M';
Select Sum(price) from book;
Select Name , Birthday_Date From employee Order By Birthday_Date DESC ;
Select Title , Price,Type From book where type = 'novel' ORDER BY Price ASC;
Select Count(*) FROM publisher;
SELECT Book.Title, Book_Invoice.Due_Date
FROM Book_Invoice
         INNER JOIN Book ON Book_Invoice.Book_Id = Book.Book_Id
WHERE CURRENT_DATE > Book_Invoice.Due_Date;
Select Type,Count(*) from book  group by type;
Select book.Book_Id, Type, Price, Title, Pub_Id
from book
Inner JOIN book_invoice on  Book_invoice.Book_id = Book.Book_id
Where CardNo = 'M0001';

Update book
Set Price = 25.99
where type = 'historical';

Delete from book where Price < 10.000;

Select book.title, book_invoice.Date_out
from book
Inner JOIN book_invoice ON book.Book_Id = book_invoice.Book_ID
where book.Type = 'science fiction';

Select AVG(price) from book;

Select book.title, publisher.Name
from book
Inner Join publisher on book.Pub_Id = publisher.Pub_Id
where Book.title LIKE 'The%';# Tìm tất cả các quyển sách có tên băt đầu bằng The


Select Book.Title, Book_invoice.date_out
from book
Inner JOIN book_invoice on book.Book_Id = book_invoice.Book_Id
where book_invoice.CardNo = 'M0002' and book.price > 20.000

#Buổi 19 homeword
#1
Select book.Book_Id, book.Type, book.Price, book.Title, publisher.name
From book
Inner Join publisher on book.Pub_Id = publisher.Pub_Id
JOIN book_invoice on book.Book_Id = book_invoice.Book_Id
where Invoice_No is not null;
#2
Select publisher.name,Count(*)
From publisher
Inner Join book on publisher.Pub_Id = book.pub_id
Group by book.Pub_Id;
#3
SELECT book.Title, publisher.Name,book.Price
FROM book
INNER JOIN publisher ON publisher.Pub_Id = book.Pub_Id
GROUP BY book.Title, publisher.Name,book.Price
HAVING book.Price > (SELECT AVG(Price) FROM book);
#4
Select member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No,Count(*)
from member
Inner Join book_invoice on member.CardNo = book_invoice.CardNo
Group By member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No,book_invoice.CardNo;
#5
Select book.title, publisher.name
from book
Inner Join publisher on book.Pub_Id = publisher.Pub_Id
Where Price = (select max(Price) from book) or Price = (select min(Price) from book);

#6
SELECT count(*) from book;;
#7
Select book.Title, publisher.name
from book
Inner join publisher on book.Pub_Id = publisher.Pub_Id
Inner Join book_invoice on book.Book_Id = book_invoice.Book_Id
where CURRENT_DATE > book_invoice.Due_Date;
#8
SELECT member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No,count(*)
From member
Inner Join book_invoice on book_invoice.CardNo = member.CardNo
group by member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No
Order by count(*) DESC ;
#9
Select publisher.name,count(*) as quantity
from publisher
Inner Join book on book.Pub_Id  = publisher.Pub_Id
GROUP BY publisher.name
Having count(*) >= 2;
#10
Select member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No
from member
Inner join book_invoice on member.CardNo = book_invoice.CardNo
Inner join book on book_invoice.Book_Id = book.Book_Id
Where book.Type = 'crime story'
group by member.CardNo, Surname, Name, Address, Birthday_Date, Gender, Phone_No
HAVING count(*) >= 1;
#11
Select book.titel, book_invoice.date_out ,Sum(current_date - book_invoice.due_date)
From book
Inner join book_invoice on book.Book_Id = book_invoice.Book_Id
where

#12
Select employee.name,count(*)
From employee
Inner join book_invoice on book_invoice.Emp_Id = employee.Emp_Id
where employee.Emp_Date = current_date
group by employee.name

#14
Select book.Title, count(*) as quantity_book
from book
inner join book_invoice on book.Book_Id = book_invoice.Book_Id
inner join publisher on book.Pub_Id = publisher.Pub_Id
where publisher.City = 'Hanoi'
group by book.title;

#15
Select book.title ,book.price
from book
inner join publisher on book.Pub_Id = publisher.Pub_Id
where publisher.City = 'Hanoi'
group by book.title, book.price
having book.Price > (select AVG(Price) FROM book);

Select book.title, book.price
from book
inner join publisher on book.Pub_Id = publisher.Pub_Id
where publisher.City = 'Hanoi' and book.Price > (SELECT AVG(Price) FROM BOOK);

SELECT p.*
FROM cart_items ci
         JOIN carts c ON ci.ID_CART = c.ID_CART
         JOIN product p on ci.ID_PRODUCT = p.ID_PRODUCT
WHERE c.ID_USER = 2;