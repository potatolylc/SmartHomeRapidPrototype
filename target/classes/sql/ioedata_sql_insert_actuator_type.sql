-- INSERT 문
insert into IOEACTUATORTYPE values(1, 'LIGHT_BRIGHTNESS', null);
insert into IOEACTUATORTYPE values(2, 'TEMPERATURE_CELSIUS', 'C');
insert into IOEACTUATORTYPE values(3, 'TEMPERATURE_FAHRENHEIT', 'F');
insert into IOEACTUATORTYPE values(4, 'HUMIDITY', '%');
insert into IOEACTUATORTYPE values(5, 'TV_VOLUME', null);
insert into IOEACTUATORTYPE values(6, 'TV_CHANNEL', null);
insert into IOEACTUATORTYPE values(7, 'FAN', null);

delete from IOEACTUATORTYPE where actuator_type_num = 5;

-- SELECT 문
select * from IOEACTUATORTYPE
where actuator_type_num > 0 and actuator_type_num < 14;