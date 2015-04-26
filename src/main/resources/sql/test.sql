select sensor_data_serial_num, sensor_data_value, 
		sensor_data_timestamp, 
		to_char(sensor_data_timestamp, 'YYYY/MM/DD HH24:MI:SS') as sensorDataTimestampStr, 
		sensor_serial_num
from IOESENSORDATA
where sensor_serial_num = '5538ae805ace012fc8c64f8c' and sensor_data_timestamp 
		between to_date('2015/04/25 12:30:00', 'YYYY/MM/DD HH24:MI:SS') 
		and to_date('2015/04/25 13:00:00', 'YYYY/MM/DD HH24:MI:SS');
	
-- select average data value
select avg(sensor_data_value)
from IOESENSORDATA
where sensor_serial_num = '5538ae805ace012fc8c64f8c';

-- select latest data value of a sensor, 5538ae605ace012fc8c64f8b(light), 5538ae965ace012fc8c64f8d(humidity)
select rownum, sensor_data_serial_num, sensor_data_value, t
from (
	select rownum as r, sensor_data_serial_num, sensor_data_value, t
	from (
		select sensor_data_serial_num, sensor_data_value, to_char(sensor_data_timestamp, 'YYYY/MM/DD HH24:MI:SS') as t
		from IOESENSORDATA
		where sensor_serial_num = '5538ae965ace012fc8c64f8d'
		order by sensor_data_timestamp desc
	)
)
where r = 1;

update IOESENSORDATA set sensor_data_value=202 where sensor_data_serial_num = 2462;
