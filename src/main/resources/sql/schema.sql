#建表语句
create table movies
(
  id        bigint auto_increment comment '主键自增Id'
    primary key,
  movie_id  bigint                              not null comment '单部电影唯一Id',
  directors mediumtext                          null comment '导演',
  casts     mediumtext                          null comment '主要演员卡司',
  cover     varchar(150)                        null comment '封面url',
  cover_x   bigint                              null comment '封面x坐标',
  cover_y   bigint                              null comment '封面y坐标',
  rate      double                              null comment '豆瓣电影评分，10分制',
  star      int                                 null comment '豆瓣电影评分,50分制',
  title     varchar(50)                         not null comment '电影标题',
  url       varchar(50)                         not null comment '电影首页url',
  in_date   timestamp default CURRENT_TIMESTAMP not null comment '插入时间'
)
  comment '豆瓣电影' charset = utf8;

delete from movies where star=50;

#修改自增值
alter table movies auto_increment=1;
