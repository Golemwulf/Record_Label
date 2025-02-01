package record.label.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import record.label.entity.Albums;
import record.label.entity.Bands;
import record.label.entity.Musicians;
import record.label.entity.Songs;

/*This model will serve the whole application*/

@Data
@NoArgsConstructor
public class BandsData {

	private Long bandId;
	private String bandName;
	private String genre;
	private String homeTown;
	private String yearsActive;

	private Set<MusiciansData> musicians = new HashSet<>();

	private Set<SongsData> songs = new HashSet<>();

	public BandsData(Bands band) {
		this.bandId = band.getBandId();
		this.bandName = band.getBandName();
		this.genre = band.getGenre();
		this.homeTown = band.getHomeTown();
		this.yearsActive = band.getYearsActive();

		for (Musicians musician : band.getMusicians()) {
			this.musicians.add(new MusiciansData(musician));
		}

		for (Songs song : band.getSongs()) {
			this.songs.add(new SongsData(song));
		}
	}

	public Bands toBands() {
		Bands band = new Bands();
		band.setBandId(bandId);
		band.setBandName(bandName);
		band.setGenre(genre);
		band.setHomeTown(homeTown);
		band.setYearsActive(yearsActive);

		for (MusiciansData musiciansData : musicians) {
			band.getMusicians().add(musiciansData.toMusicians());
		}
		for (SongsData songData : songs) {
			band.getSongs().add(songData.toSongs());
		}
		return band;
	}

	@Data
	@NoArgsConstructor
	public static class MusiciansData {

		private Long musicianId;
		private String firstName;
		private String lastName;
		private String instrument;
		private BandsData bands;

		public MusiciansData(Musicians musician) {
			this.musicianId = musician.getMusicianId();
			this.firstName = musician.getFirstName();
			this.lastName = musician.getLastName();
			this.instrument = musician.getInstrument();

		}

		public Musicians toMusicians() {
			Musicians musicians = new Musicians();

			musicians.setMusicianId(musicianId);
			musicians.setFirstName(firstName);
			musicians.setLastName(lastName);
			musicians.setInstrument(instrument);

			return musicians;
		}
	}

	@Data
	@NoArgsConstructor
	public static class AlbumsData {

		private Long albumId;
		private String albumTitle;
		private String yearReleased;
		private Set<SongsData> songs = new HashSet<>();

		public AlbumsData(Albums album) {
			this.albumId = album.getAlbumId();
			this.albumTitle = album.getAlbumTitle();
			this.yearReleased = album.getYearReleased();

		}

		public Albums toAlbums() {
			Albums albums = new Albums();

			albums.setAlbumId(albumId);
			albums.setAlbumTitle(albumTitle);
			albums.setYearReleased(yearReleased);

			return albums;
		}
	}

	@Data
	@NoArgsConstructor
	public static class SongsData {

		private Long songId;
		private String songTitle;

		public SongsData(Songs song) {
			this.songId = song.getSongId();
			this.songTitle = song.getSongTitle();

		}

		public Songs toSongs() {
			Songs songs = new Songs();
			songs.setSongId(songId);
			songs.setSongTitle(songTitle);

			return songs;

		}
	}

}
