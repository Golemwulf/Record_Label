DROP TABLE IF EXISTS album_songs;
DROP TABLE IF EXISTS albums;
DROP TABLE IF EXISTS songs;
DROP TABLE IF EXISTS musicians;
DROP TABLE IF EXISTS bands;

CREATE TABLE bands (
	band_id int NOT NULL AUTO_INCREMENT,
	genre varchar(128) NOT NULL,
	years_active int NOT NULL,
);

CREATE TABLE musicians(
	musician_id int NOT NULL AUTO_INCREMENT,
	first_name varchar(128) NOT NULL,
	last_name varchar(128) NOT NULL,
	instrument varchar (128) NOT NULL
);

CREATE TABLE songs (
	song_id int NOT NULL AUTO_INCREMENT,
	song_title varchar(255) NOT NULL,
	FOREIGN KEY (band_id) REFERENCES band_id (band_id) ON DELETE CASCADE
);

CREATE TABLE albums (
	album_id int NOT NULL AUTO_INCREMENT,
	album_title varchar (126) NOT NULL,
	year_released int NOT NULL
);

CREATE TABLE album_songs (
	album_id int NOT NULL,
	song_id int NOT NULL,
	FOREIGN KEY (album_id) REFERENCES album_id (album_id) ON DELETE CASCADE,
	FOREIGN KEY (song_id) REFERENCES song_id (song_id) ON DELETE CASCADE
);
