create table if not exists movie (
  id        int auto_increment primary key,
  imdb_id   varchar(32) not null
);

create table if not exists show (
  id        int auto_increment primary key,
  movie_id  int not null,
  time      timestamp not null,
  price     decimal(20, 2) not null,
  foreign key(movie_id) references movie
);

create table if not exists review_rating (
  id        int auto_increment primary key,
  movie_id  int not null,
  value     decimal(3, 2) not null,
  foreign key(movie_id) references movie
);