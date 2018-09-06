# Movies
Expose the IMDB movies database

### 1. Create db and user: ###
The required database is mysql.

CREATE DATABASE movies CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;  
CREATE USER 'movies'@'localhost' identified by 'movies';  
GRANT INDEX, ALTER, CREATE, DROP, SELECT, INSERT, DELETE, UPDATE, REFERENCES, LOCK TABLES ON movies.* TO 'movies'@'localhost';
