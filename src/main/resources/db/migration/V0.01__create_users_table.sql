CREATE SEQUENCE users_id_seq;

CREATE TABLE users
(
    user_id    NUMERIC NOT NULL DEFAULT nextval('users_id_seq'),
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    email      VARCHAR NOT NULL,

    PRIMARY KEY (user_id),
    CONSTRAINT unique_users_email UNIQUE (email)
);