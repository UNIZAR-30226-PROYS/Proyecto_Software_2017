USE app_proy_soft;
DELIMITER \\
CREATE TRIGGER trigger_borrado_usuario2 BEFORE DELETE ON Usuarios
FOR EACH ROW
BEGIN	
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM Transacciones  
	WHERE nick_comprador = old.nickname OR nick_vendedor = old.nickname ;
	SET SQL_SAFE_UPDATES=1;
END \\
DELIMITER ;