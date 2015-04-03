-- TABLE users
create table IOEUSER (
	user_serial_num number primary key,
	user_name varchar2(100) not null, 
	user_wifi_ssid varchar2(100) not null,
	user_password varchar2(100) not null,
	user_timestamp date not null,
	constraint unique_user_name_wifi_ssid unique(user_name, user_wifi_ssid)
);

select * from IOEUSER;
drop table IOEUSER;

-- SEQUENCE user
create sequence seq_ioeuser
start with 1
increment by 1
minvalue 1
nocache;

drop sequence seq_ioeuser;

-- TABLE sensor types
create table IOESENSORTYPE (
	sensor_type_num number primary key,
	sensor_type varchar2(100) not null,
	sensor_type_unit varchar2(100)
);

select * from IOESENSORTYPE;
drop table IOESENSORTYPE;

-- TABLE actuator types
create table IOEACTUATORTYPE (
	actuator_type_num number primary key,
	actuator_type varchar2(100) not null,
	actuator_type_unit varchar2(100)
);

drop table IOEACTUATORTYPE;

-- TABLE sensor data
create table IOESENSORDATA (
	sensor_data_serial_num number primary key,
	sensor_data_value number not null,
	sensor_data_timestamp date not null,
	sensor_serial_num number not null
);

drop table IOESENSORDATA;

-- SEQUENCE sensor data
create sequence seq_ioesensordata
start with 1
increment by 1
minvalue 1
nocache;

drop sequence seq_ioesensordata;

-- TABLE actuator preset data
create table IOEACTUATORDATA (
	actuator_data_serial_num number primary key,
	actuator_data_value number not null,
	actuator_data_timestamp date not null,
	actuator_serial_num number not null
);

drop table IOEACTUATORDATA;

-- SEQUENCE actuator preset data
create sequence seq_ioeactuatordata
start with 1
increment by 1
minvalue 1
nocache;

drop sequence seq_ioeactuatordata;