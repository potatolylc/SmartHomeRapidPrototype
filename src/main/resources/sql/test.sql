select sensor_data_serial_num, sensor_data_value, 
				sensor_data_timestamp, 
				to_char(sensor_data_timestamp, 'YYYY/MM/DD HH24:MI:SS') as sensorDataTimestampStr, 
				sensor_serial_num
from IOESENSORDATA
where sensor_serial_num = '551e630de26b8049c1e9463a' and sensor_data_timestamp 
	between to_date('2015/04/04 00:00:00', 'YYYY/MM/DD HH24:MI:SS') 
	and to_date('2015/04/22 23:00:00', 'YYYY/MM/DD HH24:MI:SS')