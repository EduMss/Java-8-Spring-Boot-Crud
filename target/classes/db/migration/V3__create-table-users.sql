CREATE TABLE users (
    user_id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);