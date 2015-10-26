INSERT INTO User(id, login, password, firstName, lastName, mail, phone, isAdmin) VALUES (1, 'admin', 'admin', 'Administrateur', '', 'noreply@javaee.emn', '+33123456789', 1);
INSERT INTO User(id, login, password, firstName, lastName, mail, phone, isAdmin) VALUES (2, 'user', 'user', 'Utilisateur', 'Lambda', 'user@javaee.emn', '+33987654321', 0);
INSERT INTO ResourceType(id, name) VALUES (1, 'Matériel vidéo');
INSERT INTO Resource(id, description, location, name, type_id, responsible_id) VALUES (1, 'Projecteur vidéo Epson', 'B118', 'Projecteur vidéo', 1, 1);
INSERT INTO Reservation(id, begin, end, reserved_id, reserver_id) VALUES (1, TIMESTAMPADD(DAY, 1, TRUNCATE(CURRENT_TIMESTAMP())), TIMESTAMPADD(DAY, 3, TRUNCATE(CURRENT_TIMESTAMP())), 1, 1);