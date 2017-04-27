SELECT * FROM Libros as t1 
WHERE t1.idLibros =(SELECT id_libro FROM Lista_libros
WHERE nickname = 'nickname_valor');
