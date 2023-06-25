CREATE TABLE search_task
(
    id SERIAL PRIMARY KEY,
    pattern VARCHAR(255),
    input_text TEXT,
    status VARCHAR(15),
    progress INTEGER,
    position INTEGER,
    typos INTEGER,
    worker VARCHAR(255),
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_search_task_created_ts
    ON search_task (created_ts);