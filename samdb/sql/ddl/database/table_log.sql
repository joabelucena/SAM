/**
 * Description: Generates a query for LOG from all SAM log fields
 * Date:		30/10/2015
 * Usage: 		Spago
 * Author:		Joabe Lucena
 */

SELECT
	'SELECT '''||TRIM(rdb$relation_name) ||''' AS "TABELA", '|| TRIM(rdb$field_name) || ' AS "CODIGO" , USR_INSERT AS "USU. INSERC." , DTI_INSERT AS "DT. INSERC." , USR_UPDATE AS "USU. ALTER." , DTI_UPDATE AS "DT. ALTER." FROM ' || TRIM(rdb$relation_name) || ' UNION ALL'
FROM
	rdb$relation_fields
WHERE
	rdb$relation_name IN 
					(SELECT
						TRIM(f.rdb$relation_name)
					FROM
						rdb$relation_fields f
					JOIN
						rdb$relations r
					ON
						f.rdb$relation_name = r.rdb$relation_name
						AND r.rdb$view_blr IS NULL 
						AND (r.rdb$system_flag IS NULL OR r.rdb$system_flag = 0)
					WHERE
						LEFT(f.rdb$field_name,3) = 'USR'
					GROUP BY
						TRIM(f.rdb$relation_name)
					ORDER BY
						TRIM(f.rdb$relation_name))
	AND SUBSTRING(rdb$field_name FROM 5 FOR 2) = 'ID';
