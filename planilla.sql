CREATE TABLE empresa (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(50) unique,
    cod_planilla varchar(20) unique
);

CREATE TABLE persona (
    cui VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE estado(
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    descripcion varchar(20)
);

CREATE TABLE trabajador (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    persona_cui VARCHAR(50),
    
    FOREIGN KEY (persona_cui) REFERENCES persona(cui)
);

CREATE TABLE planilla(
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    anio NUMBER,
    mes NUMBER,
    empresa_id NUMBER,
    
    FOREIGN KEY (empresa_id) REFERENCES empresa (id)
);

CREATE TABLE planilla_trabajador(
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    planilla_id number,
    trabajador_id number,
    sueldo number (8,2),
    estado_id number,
    
    FOREIGN KEY (planilla_id) REFERENCES planilla (id),
    FOREIGN KEY (trabajador_id) REFERENCES trabajador (id),
    FOREIGN KEY (estado_id) REFERENCES estado (id)
);

ALTER TABLE planilla
ADD CONSTRAINT uq_anio_mes_empresa UNIQUE (anio, mes, empresa_id);

ALTER TABLE estado
ADD inicial VARCHAR2(1);

delete from planilla;
delete from planilla_trabajador;

drop table planilla_trabajador;
drop table planilla;

UPDATE estado SET inicial = 'N' WHERE id = 4;

SELECT  DISTINCT  e.id as empresa_id, e.cod_planilla, p.id as id_planilla, p.anio, p.mes, tr.trabajador_id, pt.estado_id, tr.persona_cui, per.nombre
FROM empresa e, planilla p, planilla_trabajador pt, trabajador tr, persona per
WHERE e.id = 1 AND p.id = pt.planilla_id AND pt.estado_id = 1 AND pt.trabajador_id = 1 AND per.cui = '123';


SELECT e.id as empresa_id, e.cod_planilla, p.id as id_planilla, p.anio, p.mes, pt.trabajador_id, pt.estado_id, tr.persona_cui, per.nombre
FROM empresa e
JOIN planilla p ON e.id = p.empresa_id
JOIN planilla_trabajador pt ON p.id = pt.planilla_id
JOIN trabajador tr ON pt.trabajador_id = tr.id
JOIN persona per ON tr.persona_cui = per.cui
WHERE e.id = 1 AND pt.estado_id = 1 AND tr.id = 1 AND per.cui = '123';


SELECT
    t.id AS trabajador_id,
    p.cui AS persona_cui,
    p.nombre AS nombre_persona,
    pt.sueldo,
    e.nombre AS nombre_empresa
FROM
    trabajador t
JOIN
    persona p ON t.persona_cui = p.cui
JOIN
    planilla_trabajador pt ON t.id = pt.trabajador_id
JOIN
    planilla pl ON pt.planilla_id = pl.id
JOIN
    empresa e ON pl.empresa_id = e.id
WHERE
    (pl.anio, pl.mes) = (
        SELECT MAX(anio), MAX(mes)
        FROM planilla
    )
    AND pt.sueldo IS NOT NULL;  -- Agregado para filtrar sueldos no nulos
    
    SELECT
    t.id AS trabajador_id,
    p.cui AS persona_cui,
    p.nombre AS nombre_persona,
    pt.sueldo,
    e.nombre AS nombre_empresa
FROM
    trabajador t
JOIN
    persona p ON t.persona_cui = p.cui
JOIN
    planilla_trabajador pt ON t.id = pt.trabajador_id
JOIN
    planilla pl ON pt.planilla_id = pl.id
JOIN
    empresa e ON pl.empresa_id = e.id;

SELECT
    t.id AS trabajador_id,
    p.cui AS persona_cui,
    p.nombre AS nombre_persona,
    pt.sueldo,
    e.nombre AS nombre_empresa
FROM
    trabajador t
JOIN
    persona p ON t.persona_cui = p.cui
JOIN
    planilla_trabajador pt ON t.id = pt.trabajador_id
JOIN
    planilla pl ON pt.planilla_id = pl.id
JOIN
    empresa e ON pl.empresa_id = e.id
WHERE
    (pl.anio, pl.mes) = (
        SELECT MAX(anio), MAX(mes)
        FROM planilla
    );
    
    
SELECT t.id AS trabajador_id, p.cui AS persona_cui, p.nombre AS nombre_persona, pt.sueldo, e.nombre AS nombre_empresa
FROM trabajador t
JOIN persona p ON t.persona_cui = p.cui
JOIN planilla_trabajador pt ON t.id = pt.trabajador_id
JOIN (
        SELECT pl2.id AS planilla_id, pl2.anio, pl2.mes, pl2.empresa_id
        FROM planilla pl2
        WHERE pl2.id = (
            SELECT MAX(pl3.id)
            FROM planilla pl3
            WHERE pl3.empresa_id = pl2.empresa_id
        )
    ) pl ON pt.planilla_id = pl.planilla_id
JOIN empresa e ON pl.empresa_id = e.id
WHERE sueldo > 3000;

SELECT t.id AS trabajador_id, p.cui AS persona_cui, p.nombre AS nombre_persona, es.descripcion as estado, e.nombre AS nombre_empresa
FROM trabajador t
JOIN persona p ON t.persona_cui = p.cui
JOIN planilla_trabajador pt ON t.id = pt.trabajador_id
JOIN estado es ON pt.estado_id = es.id
JOIN(
        SELECT pl2.id AS planilla_id, pl2.anio, pl2.mes, pl2.empresa_id
        FROM planilla pl2
        WHERE pl2.id = (
            SELECT MAX(pl3.id)
            FROM planilla pl3
            WHERE pl3.empresa_id = pl2.empresa_id
        )
    ) pl ON pt.planilla_id = pl.planilla_id
JOIN empresa e ON pl.empresa_id = e.id 
WHERE pt.sueldo = 2900;

SELECT COUNT(*) AS existe_registro
FROM planilla_trabajador pt1
JOIN planilla pl1 ON pt1.planilla_id = pl1.id
JOIN empresa e1 ON pl1.empresa_id = e1.id
WHERE pt1.trabajador_id = 1 -- Reemplaza 1 con el ID del trabajador deseado
AND pt1.estado_id = 1
AND EXISTS (
    SELECT 1
    FROM planilla_trabajador pt2
    JOIN planilla pl2 ON pt2.planilla_id = pl2.id
    JOIN empresa e2 ON pl2.empresa_id = e2.id
    WHERE pt2.trabajador_id = pt1.trabajador_id
    AND pt2.estado_id = 1
    AND e2.id = e1.id
    AND pt2.id <> pt1.id -- Excluir el mismo registro
);

SELECT COUNT(*) AS no_registros
FROM planilla_trabajador pt
JOIN planilla pl ON pt.planilla_id = pl.id
JOIN empresa em ON pl.empresa_id = em.id
WHERE pt.trabajador_id = 2 AND pt.estado_id = 1;


SELECT p.anio, p.mes
FROM planilla_trabajador pt_actual
JOIN planilla p ON pt_actual.planilla_id = p.id
WHERE pt_actual.trabajador_id = 22 
AND pt_actual.estado_id = 1; 

AND NOT EXISTS (
    SELECT 1
    FROM planilla_trabajador pt_anterior
    JOIN planilla p_anterior ON pt_anterior.planilla_id = p_anterior.id
    WHERE pt_anterior.trabajador_id = 22
    AND pt_anterior.estado_id = 1
    AND (p.anio > p_anterior.anio OR (p.anio = p_anterior.anio AND p.mes > p_anterior.mes))
);

SELECT p.anio, p.mes
FROM planilla_trabajador pt_actual
JOIN planilla p ON pt_actual.planilla_id = p.id
WHERE pt_actual.trabajador_id = 22 
AND pt_actual.estado_id = 1
AND NOT EXISTS (
    SELECT 1
    FROM planilla_trabajador pt_anterior
    JOIN planilla p_anterior ON pt_anterior.planilla_id = p_anterior.id
    WHERE pt_anterior.trabajador_id = 22
    AND pt_anterior.estado_id = 1
    AND (p.anio > p_anterior.anio OR (p.anio = p_anterior.anio AND p.mes > p_anterior.mes))
);

SELECT COUNT(*) as no_registros
FROM planilla_trabajador pt_actual
JOIN planilla p ON pt_actual.planilla_id = p.id
WHERE pt_actual.trabajador_id = 22 
AND pt_actual.estado_id = 1
AND NOT EXISTS (
    SELECT 1
    FROM planilla_trabajador pt_anterior
    JOIN planilla p_anterior ON pt_anterior.planilla_id = p_anterior.id
    WHERE pt_anterior.trabajador_id = 22
    AND pt_anterior.estado_id = 1
    AND (p.anio > p_anterior.anio OR (p.anio = p_anterior.anio AND p.mes > p_anterior.mes))
);


