SELECT fullName, roleID FROM [User]
WHERE id='admin' AND password='1'

SELECT id, password, fullName, gender, phone, address, roleID FROM [User]
Where roleID='AD'

UPDATE [User]
SET password='1', fullName='', gender = '', phone = '', address = ''
WHERE id = 'admin'

