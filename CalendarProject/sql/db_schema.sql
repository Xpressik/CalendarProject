CREATE DATABASE IF NOT EXISTS prokom;

use prokom;

CREATE TABLE IF NOT EXISTS events2(
  id mediumint not null auto_increment,
  description varchar(250) not null,
  place varchar(250),
  dt_start datetime not null,
  dt_end datetime not null,
  primary key(id)
);