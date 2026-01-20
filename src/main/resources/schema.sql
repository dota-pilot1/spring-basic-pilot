CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed data for testing
INSERT INTO users (username, password, role) 
VALUES ('admin', '{noop}admin123', 'ROLE_ADMIN')
ON CONFLICT (username) DO NOTHING;

INSERT INTO users (username, password, role) 
VALUES ('user', '{noop}user123', 'ROLE_USER')
ON CONFLICT (username) DO NOTHING;
