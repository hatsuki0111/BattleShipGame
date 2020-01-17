create table resultHistory(
  gamenumber serial primary key,
  winnerandloser varchar(100),
  count integer,
  result varchar(100),
);
drop table resultHistory;