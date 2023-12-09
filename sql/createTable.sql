CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username varchar(20) NOT NULL,
    password varchar(200) NOT NULL
);

CREATE TABLE resources (
    item_id SERIAL PRIMARY KEY,
    resource varchar(2000) NOT NULL,
    description varchar(2000) NOT NULL,
    hours varchar(2000) NOT NULL
);
