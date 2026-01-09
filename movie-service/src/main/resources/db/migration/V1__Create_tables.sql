CREATE TABLE IF NOT EXISTS simplified_directors (
    id UUID PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS movies (
    id UUID PRIMARY KEY,
    title VARCHAR(255),
    release_year INTEGER,
    director_id UUID,
    CONSTRAINT fk_director_id FOREIGN KEY (director_id) REFERENCES simplified_directors(id) ON DELETE SET NULL
);