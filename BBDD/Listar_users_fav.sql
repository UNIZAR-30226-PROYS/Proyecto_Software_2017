SELECT * FROM Usuarios as t1 
WHERE t1.nickname =(SELECT user_fav FROM Lista_users
WHERE nickname = 'nickname_valor');
