-- INSERT 문
insert into IOESENSORTYPE values(1, 'ANALOG_SOUND', null);
insert into IOESENSORTYPE values(2, 'DUST', 'mg/m3');
insert into IOESENSORTYPE values(3, 'FLAME', null);
insert into IOESENSORTYPE values(4, 'HUMIDITY', '%');
insert into IOESENSORTYPE values(5, 'LIGHT_BRIGHTNESS', null);
insert into IOESENSORTYPE values(6, 'RAINDROP', null);
insert into IOESENSORTYPE values(7, 'TEMPERATURE_CELSIUS', 'C');
insert into IOESENSORTYPE values(8, 'TEMPERATURE_FAHRENHEIT', 'F');
insert into IOESENSORTYPE values(9, 'ACCELEROMETER', null);
insert into IOESENSORTYPE values(10, 'DIGITAL_TILT', null);
insert into IOESENSORTYPE values(11, 'DIGITAL_VIBRATION', null);
insert into IOESENSORTYPE values(12, 'INFRARED_MOTION', null);
insert into IOESENSORTYPE values(13, 'TOUCH', null);
insert into IOESENSORTYPE values(14, 'PRESSURE', 'hPA');

-- SELECT 문
select * from IOESENSORTYPE
where sensor_type_num > 0 and sensor_type_num < 14;