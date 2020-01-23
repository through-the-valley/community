create table question
(
	id int auto_increment,
	title varchar(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);

comment on column question.title is '标题
';

comment on column question.description is '问题描述';

comment on column question.gmt_create is '创建时间';

comment on column question.gmt_modified is '修改时间';

comment on column question.creator is '创建者id
';

comment on column question.comment_count is '评论数';

comment on column question.view_count is '观看数';

comment on column question.like_count is '点赞数';

comment on column question.tag is '标签';

