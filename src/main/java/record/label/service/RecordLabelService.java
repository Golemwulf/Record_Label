package record.label.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import record.label.controller.model.BandsData;
import record.label.controller.model.BandsData.MusiciansData;
import record.label.controller.model.BandsData.MusiciansData.AlbumsData;
import record.label.controller.model.BandsData.MusiciansData.SongsData;
import record.label.dao.AlbumsDao;
import record.label.dao.BandsDao;
import record.label.dao.MusiciansDao;
import record.label.dao.SongsDao;
import record.label.entity.Albums;
import record.label.entity.Bands;
import record.label.entity.Musicians;
import record.label.entity.Songs;

@Service
public class RecordLabelService {

	@Autowired
	private BandsDao bandDao;
	@Autowired
	private MusiciansDao musiciansDao;
	@Autowired
	private AlbumsDao albumsDao;
	@Autowired
	private SongsDao songsDao;

	/* bands Table */

	@Transactional(readOnly = false)
	public BandsData saveBands(BandsData bandData) {
		Long bandId = bandData.getBandId();
		Bands band = findOrCreateBands(bandId);

		copyBandsFields(band, bandData);
		return new BandsData(bandDao.save(band));

	}

	private Bands findOrCreateBands(Long bandId) {
		if (Objects.isNull(bandId)) {
			return new Bands();
		} else {
			return findBandById(bandId);
		}
	}

	private void copyBandsFields(Bands band, BandsData bandData) {
		band.setBandId(bandData.getBandId());
		band.setBandName(bandData.getBandName());
		band.setHomeTown(bandData.getHomeTown());
		band.setGenre(bandData.getGenre());
		band.setYearsActive(bandData.getYearsActive());
	}

	private Bands findBandById(Long bandId) {
		return bandDao.findById(bandId)
				.orElseThrow(() -> new NoSuchElementException("Band  with ID= " + bandId + " was not found"));
	}

	@Transactional(readOnly = false)
	public void deleteBandById(Long bandId) {
		Bands band = findBandById(bandId);
		bandDao.delete(band);
	}

	@Transactional(readOnly = true)
	public List<BandsData> retrieveAllBands() {
		List<Bands> bands = bandDao.findAll();

		List<BandsData> result = new LinkedList<>();

		for (Bands band : bands) {
			BandsData bd = new BandsData(band);

			bd.getSongs().clear();
			bd.getMusicians().clear();

			result.add(bd);
		}

		return result;
	}

	public BandsData retrieveBandById(Long bandId) {
		return new BandsData(findBandById(bandId));
	}

	/* musicians Table */

	private Musicians findMusiciansById(Long bandId, Long musiciansId) {
		Musicians musicians = musiciansDao.findById(musiciansId)
				.orElseThrow(() -> new NoSuchElementException("Musicians with ID=" + musiciansId + " Was not found."));

		if (musicians.getBand().getBandId() != bandId) {
			throw new IllegalArgumentException(
					"The musicians with ID=" + musiciansId + " is not a member of the band with ID=" + bandId);
		}

		return musicians;
	}

	@Transactional(readOnly = false)
	public MusiciansData saveMusician(Long bandId, MusiciansData musiciansData) {
		Bands band = findOrCreateBands(bandId);
		Musicians musician = findOrCreateMusician(bandId, musiciansData.getMusicianId());
		copyMusiciansFields(musician, musiciansData);

		musician.setBand(band);
		band.getMusicians().add(musician);

		Musicians dbMusician = musiciansDao.save(musician);
		return new MusiciansData(dbMusician);
	}

	private void copyMusiciansFields(Musicians musicians, MusiciansData musiciansData) {
		musicians.setFirstName(musiciansData.getFirstName());
		musicians.setLastName(musiciansData.getLastName());
		musicians.setInstrument(musiciansData.getInstrument());
	}

	private Musicians findOrCreateMusician(Long musiciansId, Long bandId) {
		if (Objects.isNull(musiciansId)) {
			return new Musicians();
		}

		return findMusiciansById(bandId, musiciansId);

	}

	@Transactional(readOnly = true)
	public List<MusiciansData> retrieveAllMusicians() {
		List<Musicians> musicians = musiciansDao.findAll();
		List<MusiciansData> result = new LinkedList<>();

		for (Musicians musician : musicians) {
			MusiciansData md = new MusiciansData(musician);

			result.add(md);
		}

		return result;
	}

	@Transactional(readOnly = true)
	public MusiciansData retrieveMusicianById(Long musicianId) {
		return new MusiciansData(findMusiciansById(musicianId));

	}

	private Musicians findMusiciansById(Long musicianId) {
		return musiciansDao.findById(musicianId)
				.orElseThrow(() -> new NoSuchElementException("Musicians with ID=" + musicianId + " Was not found."));
	}

	@Transactional(readOnly = false)
	public void deleteMusiciansById(Long musicianId) {
		Musicians musician = findMusiciansById(musicianId);
		musiciansDao.delete(musician);
	}

	/* album Table */

	private Albums findAlbumById(Long albumId) {
		return albumsDao.findById(albumId)
				.orElseThrow(() -> new NoSuchElementException("Band  with ID= " + albumId + " was not found"));
	}

	public List<AlbumsData> retrieveAllAlbums() {

		List<Albums> albums = albumsDao.findAll();
		List<AlbumsData> result = new LinkedList<>();

		for (Albums album : albums) {
			AlbumsData ad = new AlbumsData(album);

			result.add(ad);
		}

		return result;
	}

	public AlbumsData retrieveAlbumById(Long albumId) {
		return new AlbumsData(findAlbumById(albumId));
	}

	public AlbumsData saveAlbums(AlbumsData albumsData) {
		Long albumId = albumsData.getAlbumId();
		Albums album = findOrCreateAlbum(albumId);

		copyAlbumsFields(album, albumsData);
		return new AlbumsData(albumsDao.save(album));
	}

	private Albums findOrCreateAlbum(Long albumId) {
		if (Objects.isNull(albumId)) {
			return new Albums();
		} else {
			return findAlbumById(albumId);
		}
	}

	private void copyAlbumsFields(Albums album, AlbumsData albumsData) {
		album.setAlbumId(album.getAlbumId());
		album.setAlbumTitle(album.getAlbumTitle());
		album.setYearReleased(album.getYearReleased());
	}

	/* song Table */

	private Songs findSongById(Long songId) {
		return songsDao.findById(songId)
				.orElseThrow(() -> new NoSuchElementException("Song  with ID= " + songId + " was not found"));
	}

	public List<SongsData> retrieveAllSongs() {
		List<Songs> songs = songsDao.findAll();
		List<SongsData> result = new LinkedList<>();

		for (Songs song : songs) {
			SongsData sd = new SongsData(song);

			result.add(sd);
		}

		return result;
	}

	public SongsData retrieveSongById(Long songId) {
		return new SongsData(findSongById(songId));
	}

	public SongsData saveSong(SongsData songsData) {
		Long songId = songsData.getSongId();
		Songs song = findOrCreateSong(songId);

		copySongsFields(song, songsData);
		return new SongsData(songsDao.save(song));

	}

	private Songs findOrCreateSong(Long songId) {
		if (Objects.isNull(songId)) {
			return new Songs();
		} else {
			return findSongById(songId);
		}
	}

	private void copySongsFields(Songs song, SongsData songsData) {
		song.setSongId(song.getSongId());
		song.setSongTitle(song.getSongTitle());
	}
}
