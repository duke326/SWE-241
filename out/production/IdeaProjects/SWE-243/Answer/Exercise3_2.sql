-- exercises 1
CREATE INDEX vendors_zip_code_index ON vendors (vendor_zip_code);


-- exercises 2
USE ex;
DROP TABLE IF EXISTS members_committees;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS committees;

create table members(
	member_id int primary key not null auto_increment,
    first_name    varchar(45)   NOT NULL, 
  last_name    varchar(45)   NOT NULL, 
  address       varchar(45)   NOT NULL, 
  city         varchar(45)   NOT NULL, 
  state         varchar(2), 
  phone         varchar(45)
);

create table committees(
	committee_id int primary key not null auto_increment,
    committee_name varchar(45)
);

create table members_committees(
	member_id int not null,
    committee_id int not null,
    constraint members_committees_fk_members_id foreign key(member_id) 
    references members(member_id),
    constraint members_committees_fk_committees_id foreign key(committee_id)
    references committees(committee_id)
    
    
);

-- exercises 3

insert into members
values(default, "test1", "test1LastName","address1", "Irvine","CA","1234567"),
   (default, "test2", "test2LastName","address2", "LA","CA","1234567");

insert into committees
values(default, "committee1"),(default, "committee2");

insert into members_committees
values(1,2),(2,1),(2,2);

select committee_name, last_name, first_name
from committees c join members_committees mc on c.committee_id=mc.committee_id
				join	members m on mc.member_id=m.member_id
order by c.committee_name, m.last_name, m.first_name;

-- exercise4
alter table members
add annual_dues decimal(5,2) default (52.50);

alter table members 
add payment_date Date;

-- exercise5
alter table committees
modify committee_name varchar(45) unique not null;


INSERT INTO committees (committee_name)
VALUES ('committee2');

