UPDATE Usuarios
SET Usuarios.valoracion = 
(SELECT if(t1.valoracion >0, (t1.valoracion + 'valoracion_anya')/2,'valoracion_anya')
	FROM (SELECT * FROM Usuarios) as t1 where t1.nickname = 'valor_nick')
WHERE (Usuarios.nickname='valor_nick');