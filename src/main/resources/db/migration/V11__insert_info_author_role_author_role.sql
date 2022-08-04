INSERT INTO author (name, email, password) VALUES ('admin', 'admin@email.com', '$2y$12$rnZ4zWxhPv.7iSOhBKkGqu2PVsJf./W78TScYJd.EWdaEeWhnhQW.');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO author_role (author_id, role_id) VALUES (2, 2);