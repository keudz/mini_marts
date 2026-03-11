-- Truy vấn tạo ra database
create database shoppingonline;

USE shoppingonline;
#Bài 18
-- Truy vấn tạo bảng user
create table Users(
    id INT UNIQUE not null

);









create table product(
    ID_PRODUCT INT  UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name varchar(500)not null,
    description text,
    price numeric(18,3) not null,
    stock int,
    original_price numeric(18,3) not null,
    category varchar(255) not null,
    image_link text not null,
    sub_category varchar(255) not null


);



INSERT INTO product (ID_PRODUCT, name, description, price, stock, original_price, category,image_link,sub_category) VALUES
(NULL,'Tai nghe Bluetooth',NULL,399000,0,499000,'Âm thanh','https://images.unsplash.com/photo-1518444028785-8fbcd101ebb9','Tai nghe'),
(NULL,'Bàn phím cơ RGB',NULL,890000,20,1090000,'Thiết bị','https://bizweb.dktcdn.net/thumb/1024x1024/100/466/510/products/1-mode-xanh-trang.jpg?v=1693476924560','Bàn phím'),
(NULL,'Sạc dự phòng 10000mAh',NULL,299000,0,350000,'Phụ kiện','https://images.unsplash.com/photo-1583394838336-acd977736f90','Sạc dự phòng'),
(NULL,'Chuột gaming Razer',NULL,690000,30,850000,'Thiết bị','https://images.unsplash.com/photo-1587202372775-e229f172b9d7','Chuột'),
(NULL,'Ống kính điện thoại',NULL,110000,100,150000,'Phụ kiện','https://images.unsplash.com/photo-1519183071298-a2962be90b8e','Ống kính'),
(NULL,'Đồng hồ thông minh',NULL,1850000,25,2090000,'Thiết bị','https://images.unsplash.com/photo-1516574187841-cb9cc2ca948b','Đồng hồ'),
(NULL,'Tai nghe True Wireless',NULL,799000,45,999000,'Âm thanh','https://images.unsplash.com/photo-1505740420928-5e560c06d30e','Tai nghe'),
(NULL,'Loa mini Xiaomi',NULL,340000,60,420000,'Âm thanh','https://images.unsplash.com/photo-1608156639585-34a07dead35b','Loa'),
(NULL,'Miếng dán cường lực',NULL,50000,150,70000,'Phụ kiện','https://images.unsplash.com/photo-1580910051074-3eb694886505','Dán màn hình'),
(NULL,'Cáp sạc USB-C',NULL,70000,120,90000,'Phụ kiện','https://images.unsplash.com/photo-1583863788434-e58a36330cf0','Cáp sạc'),
(NULL,'Balo thời trang',NULL,320000,35,390000,'Phụ kiện','https://images.unsplash.com/photo-1509762774605-f07235a08f1f','Balo'),
(NULL,'Quạt mini USB',NULL,150000,80,190000,'Thiết bị','https://images.unsplash.com/photo-1582719478250-c89cae4dc85b','Quạt'),
(NULL,'Sổ tay da A5',NULL,130000,90,180000,'Phụ kiện','https://tse2.mm.bing.net/th/id/OIP.-_6gZzHN9Fsa6mIN8gseGQHaHa?pid=Api&P=0&h=180','Sổ tay'),
(NULL,'Bàn gập học sinh',NULL,390000,25,490000,'Nội thất','https://images.unsplash.com/photo-1505691938895-1758d7feb511','Bàn'),
(NULL,'Chuột không dây Apple',NULL,1250000,0,1390000,'Thiết bị','https://images.unsplash.com/photo-1527814050087-3793815479db','Chuột'),
(NULL,'Kính chống ánh sáng xanh',NULL,230000,100,290000,'Phụ kiện','https://images.unsplash.com/photo-1511499767150-a48a237f0083','Kính'),
(NULL,'Hub chuyển USB-C to HDMI',NULL,520000,70,620000,'Phụ kiện','https://unsplash.com/fr/photos/gros-plan-dun-routeur-sur-une-table-o-qB6ikmn8w','Hub chuyển'),
(NULL,'Máy tính bảng ipa',NULL,450000,40,490000,'Thiết bị','https://makelifeclick.com/wp-content/uploads/2022/11/ipad-pro-11-m1-back-design.jpg','Máy tính'),
(NULL,'Loa di động Bose Mini',NULL,2790000,15,3290000,'Âm thanh','https://images.unsplash.com/photo-1512446733611-9099a758e5b0','Loa'),
(NULL,'Cân điện tử thông minh',NULL,880000,0,990000,'Thiết bị','https://mivietnam.vn/wp-content/uploads/2020/06/MIVIETNAM-CAN-DIEN-TU-THONG-MINH-XIAOMI-SMART-SCALE-GEN-2-09.jpg','Cân'),
(NULL,'Sách lập trình Python',NULL,210000,80,259000,'Sách','https://images.unsplash.com/photo-1512820790803-83ca734da794','Sách lập trình'),
(NULL,'Khăn thể thao gym',NULL,95000,100,125000,'Thể thao','https://images.unsplash.com/photo-1571019613914-85f342c55f2e','Khăn'),
(NULL,'Vỏ laptop 14 inch',NULL,210000,75,290000,'Phụ kiện','https://unsplash.com/fr/photos/un-dispositif-rectangulaire-blanc-ylqeFa_-PvA','Túi laptop'),
(NULL,'Túi giữ nhiệt mini',NULL,180000,60,220000,'Gia dụng','https://thuvienmuasam.com/uploads/default/original/2X/a/a20a74d97ec98677a13b5c829b4c254e39a37e43.jpeg','Túi'),
(NULL,'Máy tạo độ ẩm USB',NULL,330000,0,410000,'Thiết bị','https://images.unsplash.com/photo-1581578731548-c64695cc6952','Máy tạo ẩm'),
(NULL,'Giá đỡ laptop nhôm',NULL,430000,50,520000,'Thiết bị','https://vn.images.search.yahoo.com/search/images?p=direct+link+%E1%BA%A3nh+gi%C3%A1+%C4%91%E1%BB%A1+laptop&fr=mcafee&type=E211VN1357G0&imgurl=https%3A%2F%2Fspalaptop.com%2Fwp-content%2Fuploads%2F2021%2F07%2Fgia-do-laptop-gap-gon.jpg#id=6&iurl=https%3A%2F%2Fspalaptop.com%2Fwp-content%2Fuploads%2F2021%2F07%2Fgia-do-laptop-gap-gon.jpg&action=click','Giá đỡ'),
(NULL,'Bộ vệ sinh laptop 5 món',NULL,170000,0,210000,'Phụ kiện','https://images.unsplash.com/photo-1587614382346-4ec70e388b28','Vệ sinh laptop'),
(NULL,'Đèn cảm biến chuyển động',NULL,310000,40,360000,'Thiết bị','https://images.unsplash.com/photo-1507477338202-487281e6c27e','Đèn'),
(NULL,'Ổ cứng SSD 512GB',NULL,1450000,25,1650000,'Thiết bị','https://images.unsplash.com/photo-1591488320449-011701bb6704','SSD'),
(NULL,'Bình giữ nhiệt Lock&Lock',NULL,290000,0,350000,'Gia dụng','https://images.unsplash.com/photo-1577937927133-66ef06acdf18','Bình giữ nhiệt'),
(NULL,'Sạc nhanh 33W Xiaomi',NULL,390000,50,450000,'Phụ kiện','https://images.unsplash.com/photo-1583863788434-e58a36330cf0','Sạc'),
(NULL,'Áo mưa tiện lợi',NULL,50000,200,70000,'Tiện ích','https://down-vn.img.susercontent.com/file/vn-11134201-7r98o-lvgcseuqc19h45','Áo mưa'),
(NULL,'Bàn phím Bluetooth mini',NULL,540000,40,650000,'Thiết bị','https://images.unsplash.com/photo-1517336714731-489689fd1ca8','Bàn phím'),
(NULL,'Chuột văn phòng Logitech',NULL,220000,60,270000,'Thiết bị','https://images.unsplash.com/photo-1527814050087-3793815479db','Chuột'),
(NULL,'Loa bluetooth mini',NULL,260000,55,320000,'Âm thanh','https://images.unsplash.com/photo-1512446733611-9099a758e5b0','Loa'),
(NULL,'Đế tản nhiệt laptop',NULL,320000,40,390000,'Thiết bị','https://www.istockphoto.com/vi/anh/khung-l%C3%A0m-m%C3%A1t-m%C3%A1y-t%C3%ADnh-x%C3%A1ch-tay-gm1313455828-401949068?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Ffr%2Fs%2Fphotos%2F%25C4%2590%25E1%25BA%25BF-t%25E1%25BA%25A3n-nhi%25E1%25BB%2587t-laptop&utm_term=%C4%90%E1%BA%BF+t%E1%BA%A3n+nhi%E1%BB%87t+laptop%3A%3Aaffiliate-signature-content%3Aexperiment%3Afec1bec0-ee9e-41ac-956c-acc70875f7d6','Tản nhiệt'),
(NULL,'Tai nghe gaming RGB',NULL,650000,30,750000,'Âm thanh','https://images.unsplash.com/photo-1505740420928-5e560c06d30e','Tai nghe'),
(NULL,'Đèn bàn học LED',NULL,210000,70,260000,'Thiết bị','https://images.unsplash.com/photo-1507477338202-487281e6c27e','Đèn'),
(NULL,'Ổ cắm điện thông minh',NULL,280000,65,340000,'Thiết bị','https://unsplash.com/fr/photos/une-prise-murale-branchee-sur-un-plancher-en-bois-kdyeST0MRUY','Ổ cắm'),
(NULL,'Pin sạc dự phòng 20000mAh',NULL,420000,50,490000,'Phụ kiện','https://images.unsplash.com/photo-1583394838336-acd977736f90','Sạc dự phòng'),
(NULL,'Cáp Lightning iPhone',NULL,120000,100,160000,'Phụ kiện','https://images.unsplash.com/photo-1583863788434-e58a36330cf0','Cáp sạc'),
(NULL,'Balo laptop chống nước',NULL,390000,45,450000,'Phụ kiện','https://images.unsplash.com/photo-1509762774605-f07235a08f1f','Balo'),
(NULL,'Chuột gaming RGB Pro',NULL,480000,35,560000,'Thiết bị','https://images.unsplash.com/photo-1587202372775-e229f172b9d7','Chuột'),
(NULL,'Áo thun nam basic',NULL,180000,50,230000,'Áo','https://images.unsplash.com/photo-1520975916090-3105956dac38?w=400','Áo thun'),
(NULL,'Áo thun nam cotton',NULL,190000,45,240000,'Áo','https://images.unsplash.com/photo-1503341504253-dff4815485f1?w=400','Áo thun'),
(NULL,'Áo thun nam form rộng',NULL,200000,40,250000,'Áo','https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?w=400','Áo thun'),
(NULL,'Áo thun nam thể thao',NULL,210000,35,260000,'Áo','https://images.unsplash.com/photo-1520975916090-3105956dac38?w=400','Áo thun'),
(NULL,'Áo thun nam cổ tròn',NULL,220000,30,270000,'Áo','https://images.unsplash.com/photo-1503341504253-dff4815485f1?w=400','Áo thun'),
(NULL,'Áo thun nam oversize',NULL,230000,25,280000,'Áo','https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?w=400','Áo thun'),
(NULL,'Áo thun nam streetwear',NULL,240000,28,290000,'Áo','https://images.unsplash.com/photo-1520975916090-3105956dac38?w=400','Áo thun'),
(NULL,'Áo thun nam minimal',NULL,250000,20,300000,'Áo','https://images.unsplash.com/photo-1503341504253-dff4815485f1?w=400','Áo thun'),
(NULL,'Áo thun nam graphic',NULL,260000,22,310000,'Áo','https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?w=400','Áo thun'),
(NULL,'Áo thun nam local brand',NULL,270000,20,320000,'Áo','https://images.unsplash.com/photo-1520975916090-3105956dac38?w=400','Áo thun'),
(NULL,'Áo sơ mi nam trắng',NULL,280000,30,340000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam công sở',NULL,290000,25,350000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam slimfit',NULL,300000,20,360000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam kẻ sọc',NULL,310000,18,370000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam denim',NULL,320000,16,380000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam ngắn tay',NULL,330000,15,390000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam dài tay',NULL,340000,14,400000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam form rộng',NULL,350000,12,410000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam casual',NULL,360000,10,420000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo sơ mi nam thời trang',NULL,370000,10,430000,'Áo','https://images.unsplash.com/photo-1593032465171-8c0f4a0e6c76?w=400','Áo sơ mi'),
(NULL,'Áo khoác hoodie nam',NULL,420000,30,480000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam thể thao',NULL,430000,28,490000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác gió nam',NULL,440000,25,500000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác bomber nam',NULL,450000,22,520000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác jean nam',NULL,460000,20,530000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam mùa đông',NULL,470000,18,540000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam basic',NULL,480000,16,550000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam local brand',NULL,490000,15,560000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam form rộng',NULL,500000,14,570000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Áo khoác nam streetwear',NULL,520000,12,590000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5?w=400','Áo khoác'),
(NULL,'Chuột văn phòng',NULL,250000,50,300000,'Thiết bị','https://images.unsplash.com/photo-1587202372616-b43abea06c2a','Chuột'),
(NULL,'Bàn phím giả cơ',NULL,890000,20,1050000,'Thiết bị','https://cf.shopee.vn/file/vn-11134207-7qukw-lj6xcv089vrgec','Bàn phím'),
(NULL,'Bàn phím gaming',NULL,920000,25,1100000,'Thiết bị','https://images.unsplash.com/photo-1518770660439-4636190af475','Bàn phím'),
(NULL,'Bàn phím bluetooth',NULL,760000,30,880000,'Thiết bị','https://images.unsplash.com/photo-1587829741301-dc798b83add3','Bàn phím'),
(NULL,'Bình nước thể thao',NULL,190000,50,240000,'Gia dụng','https://aolikessports.com/wp-content/uploads/2022/10/A-SC1500-1.jpg','Bình nước'),
(NULL,'Bình giữ nhiệt inox',NULL,290000,30,350000,'Gia dụng','https://images.unsplash.com/photo-1602143407151-7111542de6e8','Bình nước'),
(NULL,'Bình pha trà',NULL,220000,35,270000,'Gia dụng','https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lk9q23tlesnw2f','Bình nước'),
(NULL,'Nồi cơm điện Sharp',NULL,890000,20,1050000,'Gia dụng','https://mediamart.vn/images/uploads/2024/cc384e28-72ae-49a3-b4a1-3e7b1dd4a69f.jpg','Nồi cơm điện'),
(NULL,'Ấm siêu tốc Philips',NULL,450000,30,520000,'Gia dụng','https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=400','Ấm siêu tốc'),
(NULL,'Quạt điện Panasonic',NULL,720000,25,820000,'Gia dụng','https://images.unsplash.com/photo-1582719478250-c89cae4dc85b?w=400','Quạt điện'),
(NULL,'Máy xay sinh tố',NULL,560000,18,650000,'Gia dụng','https://images.unsplash.com/photo-1570222094114-d054a817e56b?w=400','Máy xay'),
(NULL,'Bếp điện từ mini',NULL,780000,22,900000,'Gia dụng','https://images.unsplash.com/photo-1585659722983-3a675dabf23d?w=400','Bếp điện'),
(NULL,'Đèn bàn học LED',NULL,220000,40,260000,'Học tập','https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=400','Đèn bàn'),
(NULL,'Balo laptop chống nước',NULL,480000,35,550000,'Phụ kiện','https://images.unsplash.com/photo-1514477917009-389c76a86b68?w=400','Balo'),
(NULL,'Túi chống sốc laptop',NULL,190000,50,240000,'Phụ kiện','https://images.unsplash.com/photo-1587202372775-e229f172b9d7?w=400','Túi laptop'),
(NULL,'Giá đỡ laptop nhôm',NULL,320000,28,380000,'Phụ kiện','https://images.unsplash.com/photo-1515879218367-8466d910aaa4?w=400','Giá đỡ'),
(NULL,'Miếng lót chuột gaming',NULL,150000,45,190000,'Phụ kiện','https://images.unsplash.com/photo-1613145993480-6c1c1c2de142?w=400','Lót chuột'),
(NULL,'Bóng đá tiêu chuẩn',NULL,280000,40,330000,'Thể thao','https://images.unsplash.com/photo-1517649763962-0c623066013b?w=400','Bóng đá'),
(NULL,'Vợt cầu lông Yonex',NULL,650000,18,750000,'Thể thao','https://images.unsplash.com/photo-1612874742237-6526221588e3?w=400','Vợt cầu lông'),
(NULL,'Dây nhảy thể dục',NULL,90000,60,120000,'Thể thao','https://images.unsplash.com/photo-1599058917765-a780eda07a3e?w=400','Dây nhảy'),
(NULL,'Thảm tập yoga',NULL,350000,25,420000,'Thể thao','https://images.unsplash.com/photo-1599447421416-3414500d18a5?w=400','Thảm yoga'),
(NULL,'Bình nước thể thao',NULL,120000,50,160000,'Thể thao','https://images.unsplash.com/photo-1526401485004-2fda9f0f5e6f?w=400','Bình nước'),
(NULL,'Sổ tay ghi chép',NULL,45000,80,60000,'Văn phòng','https://cf.shopee.vn/file/4776c51dde15cb3d9f54f26b4537404b','Sổ tay'),
(NULL,'Bút bi cao cấp',NULL,25000,100,35000,'Văn phòng','https://images.unsplash.com/photo-1583484963886-cfe2bff2945f?w=400','Bút bi'),
(NULL,'Hộp bút học sinh',NULL,85000,60,110000,'Văn phòng','https://images.unsplash.com/photo-1588072432836-e10032774350?w=400','Hộp bút'),
(NULL,'Máy tính cầm tay Casio',NULL,320000,30,380000,'Văn phòng','https://vn-test-11.slatic.net/p/35007d2d10364116693a654557e8818f.jpg','Máy tính'),
(NULL,'Kẹp tài liệu văn phòng',NULL,30000,90,45000,'Văn phòng','https://images.unsplash.com/photo-1554224155-1696413565d3?w=400','Kẹp giấy'),
(NULL,'Đồng hồ treo tường',NULL,210000,25,260000,'Trang trí','https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=400','Đồng hồ'),
(NULL,'Đèn ngủ để bàn',NULL,180000,35,230000,'Trang trí','https://images.unsplash.com/photo-1519710164239-da123dc03ef4?w=400','Đèn ngủ'),
(NULL,'Chậu cây mini để bàn',NULL,95000,50,130000,'Trang trí','https://images.unsplash.com/photo-1501004318641-b39e6451bec6?w=400','Cây cảnh'),
(NULL,'Khung ảnh để bàn',NULL,120000,40,150000,'Trang trí','https://images.unsplash.com/photo-1513519245088-0e12902e5a38?w=400','Khung ảnh'),
(NULL,'Nến thơm thư giãn',NULL,160000,30,200000,'Trang trí','https://images.unsplash.com/photo-1602874801006-3b0d8e1ed7e7?w=400','Nến thơm'),
(NULL,'Máy sấy tóc',NULL,390000,20,460000,'Gia dụng','https://images.unsplash.com/photo-1580618672591-eb180b1a973f?w=400','Máy sấy'),
(NULL,'Bàn ủi hơi nước',NULL,520000,18,600000,'Gia dụng','https://images.unsplash.com/photo-1581578731548-c64695cc6952?w=400','Bàn ủi'),
(NULL,'Máy lọc không khí ',NULL,1100000,12,1250000,'Gia dụng','https://airsafe.vn/wp-content/uploads/2023/04/airsafe-may-loc-khong-khi-la-gi-9.jpg','Máy lọc'),
(NULL,'Cân tiểu ly',NULL,240000,25,290000,'Gia dụng','https://cf.shopee.vn/file/3c870ca00f1a5e519b696283d163a337','Cân điện tử'),
(NULL,'Máy hút bụi mini',NULL,760000,15,850000,'Gia dụng','https://images.unsplash.com/photo-1581578017093-cd30fce4eebc?w=400','Máy hút bụi'),
(NULL,'Bình giữ nhiệt inox',NULL,210000,40,260000,'Đồ dùng','https://images.unsplash.com/photo-1587731340320-78e6a6a14c6c?w=400','Bình giữ nhiệt'),
(NULL,'Hộp cơm giữ nhiệt',NULL,170000,35,210000,'Đồ dùng','https://images.unsplash.com/photo-1604908176997-4312a4f96f8c?w=400','Hộp cơm'),
(NULL,'Ô dù gấp gọn',NULL,120000,50,150000,'Đồ dùng','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lma7sa8qnr33ac','Ô dù'),
(NULL,'Đèn pin siêu sáng',NULL,180000,40,220000,'Đồ dùng','https://images.unsplash.com/photo-1508896694512-1eade558679c?w=400','Đèn pin'),
(NULL,'Ổ cắm điện đa năng',NULL,95000,60,120000,'Đồ dùng','https://images.unsplash.com/photo-1581093588401-22c8a6b5b5df?w=400','Ổ cắm'),
(NULL,'Tai nghe gaming',NULL,680000,25,780000,'Công nghệ','https://images.unsplash.com/photo-1599669454699-248893623440?w=400','Tai nghe'),
(NULL,'Webcam máy tính',NULL,540000,20,620000,'Công nghệ','https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=400','Webcam'),
(NULL,'Đèn LED RGB trang trí',NULL,260000,35,320000,'Công nghệ','https://images.unsplash.com/photo-1606046604972-77cc76aee944?w=400','Đèn LED'),
(NULL,'Hub USB 4 cổng',NULL,190000,45,240000,'Công nghệ','http://phukiencom.vn/wp-content/uploads/2024/03/bo-chia-usb-1-4-hud3.0-4ports-phukiencom.vn.png','Hub USB'),
(NULL,'Thẻ nhớ microSD 128GB',NULL,280000,40,340000,'Công nghệ','https://cameraphuongdong.vn/storage/logo-icon/the-sandisk-128g.jpeg','Thẻ nhớ'),
(NULL,'Áo polo nam basic 2',NULL,250000,40,300000,'Áo','https://images.unsplash.com/photo-1581655353564-df123a1eb820','Áo polo'),
(NULL,'Áo polo nam thể thao 2',NULL,255000,39,305000,'Áo','https://images.unsplash.com/photo-1523381210434-271e8be1f52b','Áo polo'),
(NULL,'Áo polo nam cotton 2',NULL,260000,38,310000,'Áo','https://images.unsplash.com/photo-1581655353564-df123a1eb820','Áo polo'),
(NULL,'Áo polo nam slimfit 2',NULL,265000,37,315000,'Áo','https://images.unsplash.com/photo-1523381210434-271e8be1f52b','Áo polo'),
(NULL,'Áo polo nam kẻ sọc 2',NULL,270000,36,320000,'Áo','https://images.unsplash.com/photo-1581655353564-df123a1eb820','Áo polo'),
(NULL,'Áo polo nam form rộng 2',NULL,275000,35,325000,'Áo','https://images.unsplash.com/photo-1523381210434-271e8be1f52b','Áo polo'),
(NULL,'Áo polo nam minimal 2',NULL,280000,34,330000,'Áo','https://images.unsplash.com/photo-1581655353564-df123a1eb820','Áo polo'),
(NULL,'Áo polo nam casual 2',NULL,285000,33,335000,'Áo','https://images.unsplash.com/photo-1523381210434-271e8be1f52b','Áo polo'),
(NULL,'Áo polo nam local brand 2',NULL,290000,32,340000,'Áo','https://images.unsplash.com/photo-1581655353564-df123a1eb820','Áo polo'),
(NULL,'Áo polo nam basic 3',NULL,295000,31,345000,'Áo','https://images.unsplash.com/photo-1523381210434-271e8be1f52b','Áo polo'),
(NULL,'Áo hoodie nam basic 4',NULL,380000,40,430000,'Áo','https://images.unsplash.com/photo-1556821840-3a63f95609a7','Áo hoodie'),
(NULL,'Áo hoodie nam form rộng 2',NULL,385000,39,435000,'Áo','https://images.unsplash.com/photo-1556821957-67612681e177','Áo hoodie'),
(NULL,'Áo hoodie nam streetwear 2',NULL,390000,38,440000,'Áo','https://images.unsplash.com/photo-1556821840-3a63f95609a7','Áo hoodie'),
(NULL,'Áo hoodie nam thể thao 2',NULL,395000,37,445000,'Áo','https://images.unsplash.com/photo-1556821957-67612681e177','Áo hoodie'),
(NULL,'Áo hoodie nam cotton 2',NULL,400000,36,450000,'Áo','https://images.unsplash.com/photo-1556821840-3a63f95609a7','Áo hoodie'),
(NULL,'Áo hoodie nam minimal 2',NULL,405000,35,455000,'Áo','https://images.unsplash.com/photo-1556821957-67612681e177','Áo hoodie'),
(NULL,'Áo hoodie nam casual 2',NULL,410000,34,460000,'Áo','https://images.unsplash.com/photo-1556821840-3a63f95609a7','Áo hoodie'),
(NULL,'Áo hoodie nam local brand 2',NULL,415000,33,465000,'Áo','https://images.unsplash.com/photo-1556821957-67612681e177','Áo hoodie'),
(NULL,'Áo hoodie nam thời trang 2',NULL,420000,32,470000,'Áo','https://images.unsplash.com/photo-1556821840-3a63f95609a7','Áo hoodie'),
(NULL,'Áo hoodie nam basic 5',NULL,425000,31,475000,'Áo','https://images.unsplash.com/photo-1556821957-67612681e177','Áo hoodie'),
(NULL,'Áo khoác nam basic 2',NULL,420000,30,480000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5','Áo khoác'),
(NULL,'Áo khoác nam thể thao 2',NULL,425000,29,485000,'Áo','https://images.unsplash.com/photo-1591047134402-234dd224aa83','Áo khoác'),
(NULL,'Áo khoác gió nam 2',NULL,430000,28,490000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5','Áo khoác'),
(NULL,'Áo khoác bomber nam 2',NULL,435000,27,495000,'Áo','https://images.unsplash.com/photo-1591047134402-234dd224aa83','Áo khoác'),
(NULL,'Áo khoác jean nam 2',NULL,440000,26,500000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5','Áo khoác'),
(NULL,'Áo khoác nam mùa đông 2',NULL,445000,25,505000,'Áo','https://images.unsplash.com/photo-1591047134402-234dd224aa83','Áo khoác'),
(NULL,'Áo khoác nam form rộng 2',NULL,450000,24,510000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5','Áo khoác'),
(NULL,'Áo khoác nam minimal 2',NULL,455000,23,515000,'Áo','https://images.unsplash.com/photo-1591047134402-234dd224aa83','Áo khoác'),
(NULL,'Áo khoác nam casual 2',NULL,460000,22,520000,'Áo','https://images.unsplash.com/photo-1551028719-00167b16eac5','Áo khoác'),
(NULL,'Áo khoác nam basic 3',NULL,465000,21,525000,'Áo','https://images.unsplash.com/photo-1591047134402-234dd224aa83','Áo khoác'),
(NULL,'Áo len nam basic 4',NULL,320000,40,370000,'Áo','https://images.unsplash.com/photo-1620799140408-edc6dcb6d633','Áo len'),
(NULL,'Áo len nam cổ tròn 2',NULL,325000,39,375000,'Áo','https://images.unsplash.com/photo-1542060748-10c28b62716f','Áo len'),
(NULL,'Áo len nam cổ lọ 2',NULL,330000,38,380000,'Áo','https://images.unsplash.com/photo-1620799140408-edc6dcb6d633','Áo len'),
(NULL,'Áo len nam form rộng 2',NULL,335000,37,385000,'Áo','https://images.unsplash.com/photo-1542060748-10c28b62716f','Áo len'),
(NULL,'Áo len nam minimal 2',NULL,340000,36,390000,'Áo','https://images.unsplash.com/photo-1620799140408-edc6dcb6d633','Áo len'),
(NULL,'Áo len nam casual 2',NULL,345000,35,395000,'Áo','https://images.unsplash.com/photo-1542060748-10c28b62716f','Áo len'),
(NULL,'Áo len nam streetwear 2',NULL,350000,34,400000,'Áo','https://images.unsplash.com/photo-1620799140408-edc6dcb6d633','Áo len'),
(NULL,'Áo len nam local brand 2',NULL,355000,33,405000,'Áo','https://images.unsplash.com/photo-1542060748-10c28b62716f','Áo len'),
(NULL,'Áo len nam thời trang 2',NULL,360000,32,410000,'Áo','https://images.unsplash.com/photo-1620799140408-edc6dcb6d633','Áo len'),
(NULL,'Áo len nam basic 5',NULL,365000,31,415000,'Áo','https://images.unsplash.com/photo-1542060748-10c28b62716f','Áo len'),
(NULL,'Áo tanktop nam basic 4',NULL,150000,40,200000,'Áo','https://images.unsplash.com/photo-1562157873-818bc0726f68','Áo tanktop'),
(NULL,'Áo tanktop nam thể thao 2',NULL,155000,39,205000,'Áo','https://images.unsplash.com/photo-1503342217505-b0a15ec3261c','Áo tanktop'),
(NULL,'Áo tanktop nam cotton 2',NULL,160000,38,210000,'Áo','https://images.unsplash.com/photo-1562157873-818bc0726f68','Áo tanktop'),
(NULL,'Áo tanktop nam gym 2',NULL,165000,37,215000,'Áo','https://images.unsplash.com/photo-1503342217505-b0a15ec3261c','Áo tanktop'),
(NULL,'Áo tanktop nam form rộng 2',NULL,170000,36,220000,'Áo','https://images.unsplash.com/photo-1562157873-818bc0726f68','Áo tanktop'),
(NULL,'Áo tanktop nam minimal 2',NULL,175000,35,225000,'Áo','https://images.unsplash.com/photo-1503342217505-b0a15ec3261c','Áo tanktop'),
(NULL,'Áo tanktop nam casual 2',NULL,180000,34,230000,'Áo','https://images.unsplash.com/photo-1562157873-818bc0726f68','Áo tanktop'),
(NULL,'Áo tanktop nam streetwear 2',NULL,185000,33,235000,'Áo','https://images.unsplash.com/photo-1503342217505-b0a15ec3261c','Áo tanktop'),
(NULL,'Áo tanktop nam local brand 2',NULL,190000,32,240000,'Áo','https://images.unsplash.com/photo-1562157873-818bc0726f68','Áo tanktop'),
(NULL,'Áo tanktop nam basic 5',NULL,195000,31,245000,'Áo','https://images.unsplash.com/photo-1503342217505-b0a15ec3261c','Áo tanktop'),
(NULL,'Áo cardigan nam basic 4',NULL,330000,35,380000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam form rộng 3',NULL,335000,34,385000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam minimal 3',NULL,340000,33,390000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam casual 3',NULL,345000,32,395000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam thời trang 2',NULL,350000,31,400000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam streetwear 2',NULL,355000,30,405000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam local brand 2',NULL,360000,29,410000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam basic 5',NULL,365000,28,415000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam form rộng 4',NULL,370000,27,420000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo cardigan nam minimal 4',NULL,375000,26,425000,'Áo','https://images.unsplash.com/photo-1548883354-94bcfe321cbb','Áo cardigan'),
(NULL,'Áo gió nam basic 4',NULL,310000,35,360000,'Áo','https://images.unsplash.com/photo-1520975661595-6453be3f7070','Áo gió'),
(NULL,'Áo gió nam thể thao 3',NULL,315000,34,365000,'Áo','https://images.unsplash.com/photo-1545595346-646733611-9099a758e5b0','Áo gió'),
(NULL,'Áo gió nam chống nước 2',NULL,320000,33,370000,'Áo','https://images.unsplash.com/photo-1520975661595-6453be3f7070','Áo gió'),
(NULL,'Áo gió nam form rộng 3',NULL,325000,32,375000,'Áo','https://images.unsplash.com/photo-1545595346-646733611-9099a758e5b0','Áo gió'),
(NULL,'Áo gió nam minimal 3',NULL,330000,31,380000,'Áo','https://images.unsplash.com/photo-1520975661595-6453be3f7070','Áo gió'),
(NULL,'Áo gió nam streetwear 2',NULL,335000,30,385000,'Áo','https://images.unsplash.com/photo-1545595346-646733611-9099a758e5b0','Áo gió'),
(NULL,'Áo gió nam casual 3',NULL,340000,29,390000,'Áo','https://images.unsplash.com/photo-1520975661595-6453be3f7070','Áo gió'),
(NULL,'Áo gió nam local brand 2',NULL,345000,28,395000,'Áo','https://images.unsplash.com/photo-1545595346-646733611-9099a758e5b0','Áo gió'),
(NULL,'Áo gió nam basic 5',NULL,350000,27,400000,'Áo','https://images.unsplash.com/photo-1520975661595-6453be3f7070','Áo gió'),
(NULL,'Áo gió nam form rộng 4',NULL,355000,26,405000,'Áo','https://images.unsplash.com/photo-1545595346-646733611-9099a758e5b0','Áo gió'),
(NULL,'Áo blazer nam basic 4',NULL,520000,30,580000,'Áo','https://images.unsplash.com/photo-1507679799987-c73779587ccf','Áo blazer'),
(NULL,'Áo blazer nam công sở 2',NULL,525000,29,585000,'Áo','https://images.unsplash.com/photo-1594938298603-c8148c4dae35','Áo blazer'),
(NULL,'Áo blazer nam slimfit 2',NULL,530000,28,590000,'Áo','https://images.unsplash.com/photo-1507679799987-c73779587ccf','Áo blazer'),
(NULL,'Áo blazer nam form rộng 2',NULL,535000,27,595000,'Áo','https://images.unsplash.com/photo-1594938298603-c8148c4dae35','Áo blazer'),
(NULL,'Áo blazer nam thời trang 2',NULL,540000,26,600000,'Áo','https://images.unsplash.com/photo-1507679799987-c73779587ccf','Áo blazer'),
(NULL,'Áo blazer nam casual 2',NULL,545000,25,605000,'Áo','https://images.unsplash.com/photo-1594938298603-c8148c4dae35','Áo blazer'),
(NULL,'Áo blazer nam minimal 2',NULL,550000,24,610000,'Áo','https://images.unsplash.com/photo-1507679799987-c73779587ccf','Áo blazer'),
(NULL,'Áo blazer nam local brand 2',NULL,555000,23,615000,'Áo','https://images.unsplash.com/photo-1594938298603-c8148c4dae35','Áo blazer'),
(NULL,'Áo blazer nam basic 5',NULL,560000,22,620000,'Áo','https://images.unsplash.com/photo-1507679799987-c73779587ccf','Áo blazer'),
(NULL,'Áo blazer nam form rộng 3',NULL,565000,21,625000,'Áo','https://images.unsplash.com/photo-1594938298603-c8148c4dae35','Áo blazer');







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

SET FOREIGN_KEY_CHECKS  = 0; #lệnh này dùng khi sửa cơ sở dữ liệu mà k bị checkForeignKey

DROP TABLE product ;
TRUNCATE TABLE carts;
DROP TABLE carts;
DROP TABLE product;






