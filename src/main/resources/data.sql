INSERT INTO roles (name) VALUES ('user');
INSERT INTO roles (name) VALUES ('admin');

INSERT INTO users (email, password) VALUES ('user1@example.com', 'password1');
INSERT INTO users (email, password) VALUES ('user2@example.com', 'password2');
INSERT INTO users (email, password) VALUES ('user3@example.com', 'password3');
INSERT INTO users (email, password) VALUES ('user4@example.com', 'password4');
INSERT INTO users (email, password) VALUES ('user5@example.com', 'password5');

-- Suponiendo que los IDs de usuario van del 1 al 5
INSERT INTO user_rol (user_id, rol_id) VALUES (1, 1);
INSERT INTO user_rol (user_id, rol_id) VALUES (2, 1);
INSERT INTO user_rol (user_id, rol_id) VALUES (3, 1);
INSERT INTO user_rol (user_id, rol_id) VALUES (4, 2);
INSERT INTO user_rol (user_id, rol_id) VALUES (5, 2);

-- Notas para el Usuario 1
INSERT INTO notes (title, content, user_id) VALUES ('Note 1', 'Content of Note 1', 1);
INSERT INTO notes (title, content, user_id) VALUES ('Note 2', 'Content of Note 2', 1);
INSERT INTO notes (title, content, user_id) VALUES ('Note 3', 'Content of Note 3', 1);
INSERT INTO notes (title, content, user_id) VALUES ('Note 4', 'Content of Note 4', 1);

-- Notas para el Usuario 2
INSERT INTO notes (title, content, user_id) VALUES ('Note 5', 'Content of Note 5', 2);
INSERT INTO notes (title, content, user_id) VALUES ('Note 6', 'Content of Note 6', 2);
INSERT INTO notes (title, content, user_id) VALUES ('Note 7', 'Content of Note 7', 2);
INSERT INTO notes (title, content, user_id) VALUES ('Note 8', 'Content of Note 8', 2);

-- Notas para el Usuario 3
INSERT INTO notes (title, content, user_id) VALUES ('Note 9', 'Content of Note 9', 3);
INSERT INTO notes (title, content, user_id) VALUES ('Note 10', 'Content of Note 10', 3);
INSERT INTO notes (title, content, user_id) VALUES ('Note 11', 'Content of Note 11', 3);
INSERT INTO notes (title, content, user_id) VALUES ('Note 12', 'Content of Note 12', 3);

-- Notas para el Usuario 4
INSERT INTO notes (title, content, user_id) VALUES ('Note 13', 'Content of Note 13', 4);
INSERT INTO notes (title, content, user_id) VALUES ('Note 14', 'Content of Note 14', 4);
INSERT INTO notes (title, content, user_id) VALUES ('Note 15', 'Content of Note 15', 4);
INSERT INTO notes (title, content, user_id) VALUES ('Note 16', 'Content of Note 16', 4);

-- Notas para el Usuario 5
INSERT INTO notes (title, content, user_id) VALUES ('Note 17', 'Content of Note 17', 5);
INSERT INTO notes (title, content, user_id) VALUES ('Note 18', 'Content of Note 18', 5);
INSERT INTO notes (title, content, user_id) VALUES ('Note 19', 'Content of Note 19', 5);
INSERT INTO notes (title, content, user_id) VALUES ('Note 20', 'Content of Note 20', 5);
