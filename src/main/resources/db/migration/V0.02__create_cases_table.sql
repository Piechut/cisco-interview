CREATE SEQUENCE cases_id_seq;

CREATE TABLE cases
(
    case_id     NUMERIC NOT NULL DEFAULT nextval('cases_id_seq'),
    title       VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    severity    NUMERIC NOT NULL,
    status      VARCHAR NOT NULL,
    user_id     NUMERIC NOT NULL,

    PRIMARY KEY (case_id),
    CONSTRAINT fk_cases_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);