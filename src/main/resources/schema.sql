CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed data for testing
INSERT INTO users (username, password, email, role) 
VALUES ('admin', '{noop}admin123', 'admin@example.com', 'ROLE_ADMIN')
ON CONFLICT (username) DO NOTHING;

INSERT INTO users (username, password, email, role) 
VALUES ('user', '{noop}user123', 'user@example.com', 'ROLE_USER')
ON CONFLICT (username) DO NOTHING;

CREATE TABLE IF NOT EXISTS boards (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    author_id INTEGER REFERENCES users(id),
    hit INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed data for boards
INSERT INTO boards (title, content, author_id)
VALUES ('첫 번째 게시글', '안녕하세요. 파일럿 프로젝트 게시판입니다.', 1),
       ('두 번째 게시글', 'MyBatis를 이용한 CRUD 실습 중입니다.', 2)
ON CONFLICT DO NOTHING;
