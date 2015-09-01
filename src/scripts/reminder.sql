create table reminder_request (
	id int not null auto_increment primary key ,
	owner varchar(30) not null,
	subject varchar(100) not null,
	description text,
	rep boolean not null ,
	daily boolean not null,
	weekly boolean not null,
	monthlyByDay boolean not null,
	monthlyByDate boolean not null,
	startDay date not null,
	endDay date not null,
	createDate date not null
);

create index ind_req_owner on reminder_request(owner);

create table reminder_member (
	id int not null auto_increment primary key,
	request_id int not null,
	name varchar(30) not null,
	constraint fk_request_id foreign key (request_id) references reminder_request(id)
);

create index ind_req_id on reminder_member(request_id);
create unique index ind_req_name on reminder_member(request_id, name);

create table reminder_entry (
	id int not null auto_increment primary key,
	request_id int not null,
	day date not null,
	bSent boolean not null,
	sentDay date,
	constraint fk_reminder_entry_request_id foreign key (request_id) references reminder_request(id)
);

create index ind_req_rem_ent_id on reminder_entry(day);

create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null default true
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);



create table groups (
	id int not null auto_increment primary key,
	group_name varchar(50) not null
);

create table group_authorities (
	group_id int not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id int not null auto_increment primary key,
	username varchar(50) not null,
	group_id int not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);

create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);
