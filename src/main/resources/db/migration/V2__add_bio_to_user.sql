alter table USER
	add bio varchar(256);

comment on column USER.bio is '简介';