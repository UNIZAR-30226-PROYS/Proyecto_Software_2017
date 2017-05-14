USE app_proy_soft;
DELIMITER \\
CREATE TRIGGER trigger_borrado_usuario1 BEFORE DELETE ON Usuarios
FOR EACH ROW
BEGIN	
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM Lista_users 
	WHERE nickname = old.nickname OR user_fav = old.nickname;
	SET SQL_SAFE_UPDATES=1;
END \\
DELIMITER ;