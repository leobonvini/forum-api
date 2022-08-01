CREATE TABLE author_role(
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `author_id` BIGINT NOT NULL,
                             `role_id` BIGINT NOT NULL,
                             PRIMARY KEY(`id`),
                             FOREIGN KEY(`author_id`) REFERENCES `author` (`id`),
                             FOREIGN KEY(`role_id`) REFERENCES `role` (`id`)
);

INSERT INTO `author_role` (`id`, `author_id`, `role_id`) VALUES (1, 1, 1);