create table i_product(
p_no  number not null PRIMARY KEY,
p_name nvarchar2(50),
p_cnt number default 0,
p_regdate date default sysdate
);

insert into i_product (p_no,p_name,p_cnt,p_regdate)
values (1,'딸기',0,'20181101');

insert into i_product (p_no,p_name,p_cnt,p_regdate)
values (2,'바나나',0,'20181102');

insert into i_product (p_no,p_name,p_cnt,p_regdate)
values (3,'맬론',0,'20181103');

insert into i_product (p_no,p_name,p_cnt,p_regdate)
values (4,'수박',0,'20181104');

create table i_import(
i_no number not null PRIMARY KEY,
p_no number,
i_cnt number default 0,
i_date date default sysdate
);

insert into i_import (i_no,p_no,i_cnt,i_date)
VALUES (1,1,1,'20181107');

insert into i_import (i_no,p_no,i_cnt,i_date)
VALUES (2,2,2,'20181107');

insert into i_import (i_no,p_no,i_cnt,i_date)
VALUES (3,3,3,'20181107');

insert into i_import (i_no,p_no,i_cnt,i_date)
VALUES (4,4,4,'20181107');

create table i_export(
e_no number not null PRIMARY KEY,
p_no number,
e_cnt number default 0,
e_date date default sysdate
);

insert into i_export (e_no,p_no,e_cnt,e_date)
VALUES (1,1,1,'20181108');

insert into i_export (e_no,p_no,e_cnt,e_date)
VALUES (2,2,2,'20181108');

insert into i_export (e_no,p_no,e_cnt,e_date)
VALUES (3,3,3,'20181108');

insert into i_export (e_no,p_no,e_cnt,e_date)
VALUES (4,4,4,'20181108');

--물품등록--
insert into i_product (p_no,p_name)
VALUES ((SELECT nvl(max(p_no),0)+1 from i_product),?);

select * from i_product;

select * from i_import;

select * from i_export;

--물품입고--
insert into i_import (i_no,p_no,i_cnt)
VALUES ((select nvl(max(i_no),0)+1 from i_import),?,?)
;

--물품 리스트--
select p_no,p_name,p_cnt
from i_product;

--물품 출고--
insert into i_export (e_no,p_no,e_cnt)
VALUES ((select nvl(max(e_no),0)+1 from i_export),?,?)
;

--수량조절-- 입고시 증가
update i_product
set p_cnt = p_cnt + 1
where p_no = 1;

--수량조절-- 출고시 감소
update i_product
set p_cnt = p_cnt - 1
where p_no = 1;

--물품조회--
select p_no,p_name,p_cnt,to_char(p_regdate,'yyyy-mm-dd') as p_regdate
from i_product
order by p_no desc;

--입고물품 조회--
select b.i_no,a.p_name,b.i_cnt,to_char(b.i_date,'yyyy-mm-dd') as i_date
from i_product a
inner join i_import b on a.p_no = b.p_no
order by b.i_no DESC;

--출고물품 조회--
select b.e_no,a.p_name,b.e_cnt,to_char(b.e_date,'yyyy-mm-dd') as e_date
from i_product a
inner join i_export b on a.p_no = b.p_no
order by b.e_no DESC;

--물품수정을 위해 리스트 가져오기 p_no값으로 리스트 가져오기--
select p_no,p_name,p_cnt,to_char(p_regdate,'yyyy-mm-dd') as p_regdate
from i_product
where p_no = 1
order by p_no desc;

--물품수정--
update i_product
set p_name = ?
where p_no = ?;