CREATE SEQUENCE notes_id_seq;

CREATE TABLE notes
(
    note_id NUMERIC NOT NULL DEFAULT nextval('notes_id_seq'),
    case_id NUMERIC NOT NULL,
    details VARCHAR NOT NULL,

    PRIMARY KEY (note_id),
    CONSTRAINT fk_notes_case FOREIGN KEY (case_id) REFERENCES cases (case_id)
);