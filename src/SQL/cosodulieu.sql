CREATE DATABASE `UNGDUNGWEBBANHANG`;
use `UNGDUNGWEBBANHANG`;
CREATE TABLE CATALOG
(
    ID   varchar(255) PRIMARY KEY,
    NAME VARCHAR(255)
);

delimiter //
create procedure PROC_GETALLCATALOG()
begin
    select *from catalog;
end //

delimiter //
create procedure PROC_CREATECATALOG(newId varchar(255), newName varchar(255))
begin
    insert into catalog values (newId, newName);
end //
delimiter //
create procedure PROC_UPDATECATALOG(idUp varchar(255), nameUp varchar(255))
begin
    update catalog set NAME =nameUp where ID = idUp;
end //
delimiter //
create procedure PROC_FINDBYIDCATALOG(IdSearch varchar(255))
begin
    select *from catalog where ID like concat('%' + IdSearch + '%');
end //
delimiter //
create procedure PROC_DELETECATATLOG(idDel varchar(255))
begin
    delete from catalog where ID = idDel;
end //
delimiter //
create procedure PROC_SEARCHBYNAMECATALOG(nameSearch varchar(255))
begin
    select *from catalog where NAME like concat('%' + nameSearch + '%');
end //

insert into CATALOG
values ('CA001', 'Thời Trang'),
       ('CA002', 'Thiết bị điện tử');
create table product
(
    id        varchar(255) primary key,
    name      varchar(255),
    price     float,
    catalogId varchar(255),
    foreign key (catalogId) references CATALOG (id),
    quantity  int,
    image     text
);
delimiter //
create procedure PROC_GETALLPRODUCT()
begin
    select *from product;
end //

delimiter //
create procedure PROC_CREATEPRODUCT(newId varchar(255), newName varchar(255), newPrice float, catalogId varchar(255),
                                    quantity int, newimage text)
begin
    insert into product values (newId, newName, newPrice, catalogId, quantity, newimage);
end //

delimiter //
create procedure PROC_UPDATEPRODUCT(newId varchar(255), newName varchar(255), newPrice float, newcatalogId varchar(255),
                                    newquantity int, newimage text)
begin
    update product
    set NAME     = newName,
        price=newPrice,
        catalogId=newcatalogId,
        quantity=newquantity,
        image=newimage
    where ID = newId;
end //

delimiter //
create procedure PROC_DELETEPRODUCT(idDel varchar(255))
begin
    delete from product where ID = idDel;
end //

delimiter //
create procedure PROC_SEARCHBYNAMEPRODUCT(nameSearch varchar(255))
begin
    select *from product where NAME like concat('%', nameSearch, '%');
end //
delimiter //
create procedure PROC_FINDBYIDPRODUCT(idUpdate varchar(255))
begin
    select * from product where product.id like idUpdate;
end //

delimiter //
create procedure PROC_UPDATEPRODUCT(upId varchar(255), upName varchar(255), upPrice float,
                                    upCatalogId varchar(255), upQuantity int, upImage text)
begin
    update product
    set name=upName,
        price=upPrice,
        catalogId=upCatalogId,
        quantity=upQuantity,
        image=upImage
    where id = upId;
end //

insert into product
values ('P001', 'Váy dạ hội', 100, 'CA001', 10, ''),
       ('P002', 'váy bầu', 100, 'CA001', 20, ''),
       ('P003', 'Iphone 12', 100, 'CA002', 11, ''),
       ('P004', 'laptop', 100, 'CA002', 30, ''),
       ('P005', 'tai nghe', 100, 'CA002', 38, '');
create table User
(
    id         int primary key auto_increment,
    username   varchar(255),
    email      varchar(255),
    password   varchar(255),
    userStatus bit default 1
);

delimiter //
create procedure PROC_FindByIdUSER(idFind int)
begin
    select *from user where id = idFind;
end //
delimiter //
create procedure PROC_CREATEUSER(newUserName varchar(255), newEmail varchar(255), newPassword varchar(255))
begin
    insert into user(username, email, password) values (newUserName, newEmail, newPassword);
end //
delimiter //
create procedure PROC_LOGIN(loginUserName varchar(255), LoginPassword varchar(255))
begin
    select * from user where username = loginUserName and password = LoginPassword;
end //
delimiter //
create procedure PROC_FindByUserName(newUserName varchar(255))
begin
    select * from user where username like newUserName;
end //
delimiter //
create procedure PROC_FindByEmail(newEmail varchar(255))
begin
    select * from user where email like newEmail;
end //
delimiter //
create procedure PROC_UPDATEUSER(idUp int, newUserName varchar(255), newPassword varchar(255))
begin
    update user set username = newUserName and password = newPassword where id = idUp;
end //

insert into User(username, email, password, userStatus)
values ('admin', 'admin@gmail.com', 'admin', 1),
       ('tuananh', 'tuananh@gmail.com', 'tuananh', 1);
insert into user(User.userStatus)
values (1);

create table CartItem
(
    id          int primary key auto_increment,
    quantity    int,
    productId   varchar(255),
    foreign key (productId) references product (id),
    image       text,
    productName varchar(255),
    userId      int,
    foreign key (userId) REFERENCES user (id)
);
alter table CartItem
    add column price float;

delimiter //
create procedure PROC_GetAllCartItem()
begin
    select *from CartItem;
end //


delimiter //
create procedure PROC_GetCartTotal()
begin
    select sum(CartItem.quantity * p.price) as total
    from CartItem
             join product p on p.id = CartItem.productId
    group by CartItem.id;
end //;
call PROC_GetCartTotal();


delimiter //
create procedure PROC_AddToCart(userId int)
begin
    select p.image                    as image,
           p.name                     as name,
           p.price                    as price,
           ci.quantity                as quantity,
           sum(p.price * ci.quantity) as total
    from cartitem ci
             join cart c on ci.id = c.cartItemId
             join product p on ci.productId = p.id
    where c.id = userId;
end //

delimiter //
create procedure PROC_CreateCartItem(newQuantity int, newProductId varchar(255), newImage text,
                                     newProductname varchar(255), userId int, newPrice float)
begin
    insert into CartItem values (newQuantity, newProductId, newImage, newProductname, userId, newPrice);
end //

delimiter //

create procedure PROC_blockuser(idBlock int)
begin
    update user set userStatus = 0 where user.id = idBlock;
end //

alter table cartitem
    modify quantity int default 1;

delimiter //
create procedure PROC_DeleteCartItem(idDel int)
begin
    delete from CartItem where id = idDel;
end //
insert into cartitem
values (null, 2, '1', null, 'Iphone 10', 2, 200);
delimiter //
create procedure PROC_FindListCartItem(cartItemId int)
begin
    select quantity, productId, image, productName, userId, CartItem.price from cartitem where id = cartItemId;
end //
use UNGDUNGWEBBANHANG;
delimiter //
create procedure PROC_ChangeQuantity(idUp int, quantityUp int)
begin
    update CartItem set quantity=quantityUp where id = idUp;
end //;
delimiter //
create procedure PROC_DeleteCartItem(cartItemId int)
begin
    delete from CartItem where id = cartItemId;
end //;
use UNGDUNGWEBBANHANG;
call PROC_FINDBYIDPRODUCT('1');
delimiter //
create procedure PROC_CreateCartItem(IN newQuantity int, IN newProductId varchar(255),
                                     IN newImage text, IN newProductname varchar(255),
                                     IN userId int, IN newPrice float)
begin
    insert into CartItem(quantity, productId, image, productName, userId, price)
    values (newQuantity, newProductId, newImage, newProductname, userId, newPrice);
end;
delimiter //
create procedure PROC_FindListCartItem(IN Id int)
begin
    select * from cartitem where userId = Id ;
end //;
delimiter //
create procedure proc_setquantity(pid varchar(255))
begin
    update cartitem set quantity = quantity + 1 where productId = pid;
end //;
delimiter //
create procedure PROC_totalAmout(id int)
begin
    select sum(CartItem.price * CartItem.quantity) as total from cartitem group by userId having userId = id;
end //
delimiter //
create procedure PROC_blockUser(idBlock int)
begin
    update user set userStatus =0 where id = idBlock;
end //
delimiter //
create procedure PROC_UnBlockUser(idUnBlock int)
begin
    update user set userStatus =1 where id = idUnBlock;
end //
delimiter //
create procedure PROC_totalCartItemPrice(nid int)
begin
    select CartItem.price * CartItem.quantity as totalPrice from cartitem where id = nid;
end //;
call PROC_totalCartItemPrice();
drop procedure PROC_totalCartItemPrice;
CREATE TABLE userDetail
(
    id int auto_increment primary key,
    userDetailId int  ,
    foreign key (userDetailId) references User(id),
    fullName varchar(255),
    address text,
    email varchar(255),
    phoneNumber varchar(255),
    noteOder text
);
delimiter //
create procedure proc_oderListDetail(id int)
    begin
        select ci.userId as id,uD.address as address,
               ci.productName as`productName`,uD.fullName as `name`,
               ci.quantity as quantity,phoneNumber,
               date(now()) as `date`, noteOder from cartitem ci
                   join user u on ci.userId=u.id
        join userDetail uD on u.id = uD.userDetailId where ci.userId = id;
    end //


delimiter //
create procedure PROC_GetAllDetailUser()
begin
    select *from userDetail;
end //
delimiter //
create procedure PROC_CreateUserDetail(userid int, newFullname varchar(255),newAddress text,newEmail varchar(255),newPhoneNumber varchar(255),newNoteOder text)
begin
    insert into userDetail( userDetailId,fullName, address, email, phoneNumber, noteOder) values (userid,newFullname,newAddress,newEmail,newPhoneNumber,newNoteOder);
end //
delimiter //
create procedure proc_setQuantityCartItem(nId int, nQ int)
begin
    update cartitem set quantity = nQ where id = nId;
end //;
truncate table user;
truncate table cartitem;
delimiter //
create procedure proc_showUserManagement()
    begin
        select u.id as id,u.username as `name`,sum(quantity*price) as `quantity` from cartitem ci join user u on ci.userId = u.id group by userId;
    end //
delimiter //
create procedure proc_deleteCartItemFindUserId(Id int)
begin
    delete from cartitem where cartitem.userId=Id;
end //
call proc_deleteCartItemFindUserId(12)