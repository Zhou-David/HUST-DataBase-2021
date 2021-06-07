-- 第1关 人流量大于30的地点
SELECT
	location.location_name,
	COUNT( itinerary.p_id ) AS visitors 
FROM
	itinerary,
	location 
WHERE
	itinerary.loc_id = location.id 
GROUP BY
	itinerary.loc_id 
HAVING
	visitors > 30 
ORDER BY
	visitors DESC,
	location.location_name ASC;
	
-- 第2关 每个隔离点正在进行隔离的人数
SELECT
	isolation_location.location_name,
	COUNT( isolation_record.p_id ) AS number 
FROM
	isolation_record,
	isolation_location 
WHERE
	isolation_record.isol_loc_id = isolation_location.id 
	AND isolation_record.state = 1
GROUP BY
	isolation_location.id 
ORDER BY
	number DESC,
	isolation_location.location_name ASC;
	
-- 第3关 接续行程
SELECT
	person.id,
	person.fullname,
	person.telephone,
	start_itinerary.e_time AS reclosing_time,
	start_location.id AS loc1,
	start_location.location_name AS address1,
	end_location.id AS loc2,
	end_location.location_name AS address2 
FROM
	person,
	itinerary AS start_itinerary,
	itinerary AS end_itinerary,
	location AS start_location,
	location AS end_location 
WHERE
	start_itinerary.p_id > 30 
	AND start_itinerary.p_id = person.id 
	AND end_itinerary.p_id = person.id 
	AND start_itinerary.loc_id = start_location.id 
	AND end_itinerary.loc_id = end_location.id 
	AND start_itinerary.e_time = end_itinerary.s_time 
ORDER BY
	person.id,
	reclosing_time;
	
-- 第4关 充珉瑶和贾涵山的行程情况
SELECT
	person.fullname,
	person.telephone,
	location.location_name,
	itinerary.s_time,
	itinerary.e_time 
FROM
	person
	LEFT JOIN itinerary ON itinerary.p_id = person.id
	LEFT JOIN location ON itinerary.loc_id = location.id 
WHERE
	person.fullname = '充珉瑶' 
	OR person.fullname = '贾涵山' 
ORDER BY
	person.id DESC,
	itinerary.s_time;
	
-- 第5关 地名中带有‘店’字的地点名称
SELECT
	location.id,
	location.location_name 
FROM
	location 
WHERE
	location.location_name LIKE '%店%' 
ORDER BY
	location.id;
	
-- 第6关 确诊者的接触者
SELECT
	person.fullname,
	person.telephone
FROM
	person
	JOIN itinerary ON person.id = itinerary.p_id
	JOIN location ON itinerary.loc_id = location.id 
WHERE
	location.location_name = '活动中心' 
	AND itinerary.e_time > '2021-02-02 20:05:40' 
	AND itinerary.s_time < '2021-02-02 21:25:40' 
ORDER BY
	person.fullname;
	
-- 第7关 仍在使用的隔离点
SELECT DISTINCT
	isolation_location.location_name 
FROM
	isolation_location 
WHERE
	isolation_location.id IN ( SELECT isolation_record.isol_loc_id FROM isolation_record WHERE isolation_record.state = 1 ) 
ORDER BY
	isolation_location.id;
	
-- 第8关 查询有出行记录的人员
SELECT
	person.fullname,
	person.telephone 
FROM
	person 
WHERE
	EXISTS ( SELECT DISTINCT itinerary.p_id FROM itinerary WHERE person.id = itinerary.p_id ORDER BY itinerary.p_id ) 
	LIMIT 0,
	30;

-- 第9关 没有去过“Today便利店“的人数
SELECT
	COUNT( person.id ) AS number 
FROM
	person 
WHERE
	NOT EXISTS (
	SELECT DISTINCT
		itinerary.p_id 
	FROM
		itinerary
		JOIN location ON itinerary.loc_id = location.id 
	WHERE
		itinerary.p_id = person.id 
		AND location.location_name = 'Today便利店' 
	);
	
-- 第10关 去过所有地点的人员
SELECT
	person.fullname 
FROM
	person 
WHERE
	NOT EXISTS (
	SELECT
		* 
	FROM
		location 
	WHERE
	NOT EXISTS ( SELECT * FROM itinerary WHERE itinerary.p_id = person.id AND itinerary.loc_id = location.id )) 
ORDER BY
	person.fullname;

-- 第11关 隔离点的现状视图
DROP VIEW
IF
	EXISTS isolation_location_status;
CREATE VIEW isolation_location_status AS SELECT
isolation_location.id,
isolation_location.location_name,
isolation_location.capacity,
COUNT( isolation_record.state = 1 OR NULL ) AS occupied 
FROM
	isolation_location
	INNER JOIN isolation_record ON isolation_location.id = isolation_record.isol_loc_id 
GROUP BY
	isolation_location.id;
	
-- 第12关 各隔离点的剩余房间数
SELECT
	isolation_location_status.location_name,
	( isolation_location_status.capacity - isolation_location_status.occupied ) AS available_rooms
FROM
	isolation_location_status
ORDER BY
	isolation_location_status.id;
	
-- 第13关与无症状感染者靳宛儿有过接触的人
SELECT
	person.fullname,
	person.telephone 
FROM
	person
	JOIN itinerary ON person.id = itinerary.p_id,
	(
	SELECT
		itinerary.loc_id,
		itinerary.s_time,
		itinerary.e_time 
	FROM
		person
		JOIN itinerary ON person.id = itinerary.p_id 
	WHERE
		person.fullname = '靳宛儿' 
	) AS temp_table 
WHERE
	person.fullname != '靳宛儿' 
	AND itinerary.loc_id = temp_table.loc_id 
	AND itinerary.s_time <= temp_table.e_time AND itinerary.e_time >= temp_table.s_time 
ORDER BY
	person.fullname;

-- 第14关 每个地点发生的密切接触者人数
SELECT
	location.location_name,
	COUNT( close_contact.p_id ) AS close_contact_number 
FROM
	close_contact
	JOIN location ON close_contact.loc_id = location.id 
GROUP BY
	close_contact.loc_id
ORDER BY
	close_contact_number DESC,
	location.location_name;
	
-- 第15关 感染人数最多的人
SELECT
	close_contact.case_p_id,
	person.fullname,
	COUNT( close_contact.p_id ) AS infected_number 
FROM
	close_contact
	JOIN person ON close_contact.case_p_id = person.id 
GROUP BY
	close_contact.case_p_id 
ORDER BY
	infected_number DESC 
	LIMIT 0,
	1;
	
-- 第16关 行程记录最频繁的3个人
SELECT
	person.fullname,
	COUNT( itinerary.id ) AS record_number 
FROM
	person
	JOIN itinerary ON person.id = itinerary.p_id 
WHERE
	itinerary.e_time >= '2021-02-02 10:00:00' 
	AND itinerary.s_time <= '2021-02-02 14:00:00' 
GROUP BY
	person.id 
ORDER BY
	record_number DESC,
	person.fullname 
	LIMIT 0,
	3;

-- 第17关 房间数第2多的隔离点
SELECT
	isolation_location.location_name,
	isolation_location.capacity
FROM
	isolation_location
WHERE
	isolation_location.capacity <( SELECT MAX( isolation_location.capacity ) FROM isolation_location )
LIMIT 0,1;