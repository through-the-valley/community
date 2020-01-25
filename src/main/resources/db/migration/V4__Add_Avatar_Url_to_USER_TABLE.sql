alter table USER
	add avatar_url varchar(100);

comment on column USER.avatar_url is '用户头像地址';