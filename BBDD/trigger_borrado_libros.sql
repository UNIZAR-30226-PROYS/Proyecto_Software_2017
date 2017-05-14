USE app_proy_soft;
DELIMITER \\
CREATE TRIGGER trigger_borrado_libros BEFORE DELETE ON Libros
FOR EACH ROW
BEGIN	
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM Lista_libros  
	WHERE id_libro = old.idLibros;
	SET SQL_SAFE_UPDATES=1;
END \\
DELIMITER ;