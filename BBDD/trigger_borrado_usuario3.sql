USE app_proy_soft;
DELIMITER \\
CREATE TRIGGER trigger_borrado_usuario3 BEFORE DELETE ON Usuarios
FOR EACH ROW
BEGIN	
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM Libros  
	WHERE nick_duenyo = old.nickname ;
	SET SQL_SAFE_UPDATES=1;
END \\
DELIMITER ;