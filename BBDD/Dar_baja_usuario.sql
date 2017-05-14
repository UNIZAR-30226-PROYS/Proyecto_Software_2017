DELETE FROM Usuarios 
WHERE (nickname = 'nickname_valor_borrar' AND contrasenya = MD5('valor_contrasenya'));
