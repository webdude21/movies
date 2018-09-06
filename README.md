# Movies
Expose the IMDB movies database

### 1. Install mysql if you don't already have it. ###
### 2. Create db and user: ###
CREATE DATABASE movies CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;  
CREATE USER 'movies'@'localhost' identified by 'movies';  
GRANT INDEX, ALTER, CREATE, DROP, SELECT, INSERT, DELETE, UPDATE, REFERENCES, LOCK TABLES ON movies.* TO 'movies'@'localhost';
