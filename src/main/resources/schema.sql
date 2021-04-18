-- Migration pada SQL membuat table baru users,rooms, dan bookings

drop table if exists bookings;
drop table if exists rooms;
drop table if exists users;


create table users (
  id int not null AUTO_INCREMENT,
  created_at datetime(6) default null,
  updated_at datetime(6) default null,
  deleted_at datetime(6) default null,
  email varchar(255) not null,
  password varchar(255) not null,
  photo varchar(255) not null,
  PRIMARY KEY ( id )
);

create table rooms (
  id int not null AUTO_INCREMENT,
  created_at datetime(6) default null,
  updated_at datetime(6) default null,
  deleted_at datetime(6) default null,
  room_name varchar(255) not null,
  room_capacity int not null,
  photo varchar(255) not null,
  PRIMARY KEY ( id )
);

create table bookings (
  id int not null AUTO_INCREMENT,
  created_at datetime(6) default null,
  updated_at datetime(6) default null,
  deleted_at datetime(6) default null,
  user_id int not null,
  room_id int not null,
  total_person int not null,
  booking_time datetime(6) default null,
  noted string default null,
  check_in_time datetime(6) default null,
  check_out_time datetime(6) default null,
  PRIMARY KEY ( id ),
  FOREIGN KEY ( user_id ) REFERENCES users( id ),
  FOREIGN KEY ( room_id ) REFERENCES rooms( id )
);
