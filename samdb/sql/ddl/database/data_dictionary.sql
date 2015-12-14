/**
 * Description: Generates a data dictionrary
 * Date:		30/10/2015
 * Usage: 		-
 * Author:		Joabe Lucena
 */

SELECT
	f.rdb$relation_name
	,f.rdb$field_name
FROM
	rdb$relation_fields f
JOIN
	rdb$relations r
ON
	f.rdb$relation_name = r.rdb$relation_name
	AND r.rdb$view_blr IS NULL 
	AND (r.rdb$system_flag IS NULL OR r.rdb$system_flag = 0)
ORDER BY
	1, f.rdb$field_position;