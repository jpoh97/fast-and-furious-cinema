create table if not exists movie (
  id        int auto_increment primary key,
  imdb_id   varchar(32) not null
);

create table if not exists show (
  movie_id  int not null,
  time      timestamp not null,
  price     decimal(20, 2) not null
);

create table if not exists review_rating (
  movie_id  int not null,
  value     decimal(3, 2) not null
);