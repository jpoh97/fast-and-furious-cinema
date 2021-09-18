create table if not exists movie (
  id        int auto_increment primary key,
  imdb_id   varchar(32) not null
);