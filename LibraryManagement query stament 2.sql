SELECT fullName, roleID FROM [User]
WHERE id='admin' AND password='1'

SELECT id, password, fullName, gender, phone, address, roleID FROM [User]
Where roleID='AD'

UPDATE [User]
SET password='1', fullName='', gender = '', phone = '', address = ''
WHERE id = 'admin'

SELECT '' FROM [Book]
WHERE id=2 AND available > 0

SELECT id, name, author, publisher, total, available, publishYear FROM [Book] 
Where available > 10