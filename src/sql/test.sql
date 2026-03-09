CREATE DATABASE table_student;

USE table_student;

CREATE TABLE student (
                         id VARCHAR(50) NOT NULL PRIMARY KEY,
                         name VARCHAR(250) NULL,
                         age INT NULL,
                         adddress VARCHAR(250) NULL
);

insert into student (id, name, age, adddress) #thêm dữ liệu vào cột
value ( '12312','nguyenthedb',34,'phudien');

update student set age = 500 #thay đổi dữ liệu của age o id bằng 123
where id = '123';

update student set age = 200000 #thay đổi dữ liêu của age ở cả 2 id là 123 va 23
where id in('123','23');

delete from student #  xoa di data co  id < 40
where id < 40;

select * from student # lấy ra tất cả cac data thuộc 1 hàng nào đó trong student
         # ( dùng * đại diện cho tat cả các thuộc tính của student)

where id > 50;

select * from student
where id > 200;

select avg(age) from student;

select max(age) from student;

select * from  student  where name like 'nguyenthe';

select * from student order by age  asc limit 2;