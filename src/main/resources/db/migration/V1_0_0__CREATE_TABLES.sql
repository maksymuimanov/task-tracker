CREATE TABLE tasks(
    id TEXT PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    priority TEXT NOT NULL,
    status TEXT NOT NULL,
    completed INTEGER NOT NULL,
    parent_id TEXT,
    CONSTRAINT completed_ck CHECK (completed BETWEEN 0 AND 100),
    CONSTRAINT parent_id_fk FOREIGN KEY (parent_id) REFERENCES tasks(id)
)

CREATE TABLE base64_task_attachments(
    id TEXT PRIMARY KEY,
    content TEXT NOT NULL,
    task_id TEXT NOT NULL,
    CONSTRAINT base64_attachment_task_id_fk FOREIGN KEY (task_id) REFERENCES tasks(id)
)

CREATE TABLE url_task_attachments(
    id TEXT PRIMARY KEY,
    url TEXT NOT NULL,
    task_id TEXT NOT NULL,
    CONSTRAINT url_attachment_id_fk FOREIGN KEY (task_id) REFERENCES tasks(id)
)