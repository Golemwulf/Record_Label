package record.label.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import record.label.controller.model.BandsData;
import record.label.controller.model.BandsData.AlbumsData;
import record.label.controller.model.BandsData.MusiciansData;
import record.label.controller.model.BandsData.SongsData;
import record.label.service.RecordLabelService;

@RestController
@RequestMapping("/record_label")
@Slf4j
public class RecordLabelController {
	@Autowired
	private RecordLabelService recordLabelService;

	/** Focused on the bands table **/

	@PostMapping("/band")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BandsData insertBands(@RequestBody BandsData bandsData) {
		log.info("Creating band ID = {}", bandsData);
		return recordLabelService.saveBands(bandsData);
	}

	@PutMapping("/band/{bandId}")
	public BandsData updateBands(@PathVariable Long bandId, @RequestBody BandsData bandsData) {
		bandsData.setBandId(bandId);
		log.info("Updating band  with ID={}", bandsData);
		return recordLabelService.saveBands(bandsData);
	}

	@GetMapping("/band")
	public List<BandsData> retrieveAllBands() {
		log.info("Retrieveing all bands");
		return recordLabelService.retrieveAllBands();
	}

	@GetMapping("/band/{bandId}")
	public BandsData retrieveBandsById(@PathVariable Long bandId) {
		log.info("Retrieving band  with ID= {}", bandId);
		return recordLabelService.retrieveBandById(bandId);
	}

	@DeleteMapping("/band/{bandId}")
	public Map<String, String> deleteBandsById(@PathVariable Long bandId) {
		log.info("Deleting band  with ID= {}", bandId);

		recordLabelService.deleteBandById(bandId);

		return Map.of("message", "Band  with ID=" + bandId + " deleted.");

	}

	/* Focuses on the musicians table. */

	@PostMapping("/band/{bandId}/musicians")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MusiciansData addmusicianToBands(@PathVariable Long bandId, @RequestBody MusiciansData musiciansData) {
		log.info("Adding musician {) to band  with ID= {}", musiciansData, bandId);

		return recordLabelService.saveMusician(bandId, musiciansData);
	}

	@GetMapping("/musicians/{musicianId}")
	public MusiciansData retrieveMusicianById(@PathVariable Long musicianId) {
		log.info("Retrieving pet store with ID= {}", musicianId);
		return recordLabelService.retrieveMusicianById(musicianId);
	}

	@PutMapping("/band/{bandId}/musicians/{musicianId}")
	public MusiciansData musicians(@PathVariable Long bandId, @PathVariable Long musicianId,
			@RequestBody MusiciansData musiciansData) {
		musiciansData.setMusicianId(musicianId);
		log.info("Updating Musiciain with ID={}", musiciansData);
		return recordLabelService.saveMusician(bandId, musiciansData);
	}

	@DeleteMapping("musicians/{musicianId}")
	public Map<String, String> deleteMusiciansById(@PathVariable Long musicianId) {
		log.info("Deleting musician  with ID= {}", musicianId);

		recordLabelService.deleteMusiciansById(musicianId);

		return Map.of("message", "musician  with ID=" + musicianId + " deleted.");
	}

//

	/* Focuses on the album table. */

	@PostMapping("/albums")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlbumsData insertAlbums(@RequestBody AlbumsData albumsData) {
		log.info("Creating Album  {}", albumsData);
		return recordLabelService.saveAlbums(albumsData);
	}

	@GetMapping("/albums")
	public List<AlbumsData> retrieveAllAlbums() {
		log.info("Retrieveing all albums");
		return recordLabelService.retrieveAllAlbums();
	}

	@GetMapping("/albums/{albumId}")
	public AlbumsData retrieveAlbumById(@PathVariable Long albumId) {
		log.info("Retrieving album with ID= {}", albumId);
		return recordLabelService.retrieveAlbumById(albumId);
	}

	@PutMapping("albums/{albumId}")
	public AlbumsData updateAlbum(@PathVariable Long albumId, @RequestBody AlbumsData albumsData) {
		albumsData.setAlbumId(albumId);
		log.info("Updating band  with ID={}", albumsData);
		return recordLabelService.saveAlbums(albumsData);
	}

	/* Focuses on the songs table. */

	@PostMapping("/band/{bandId}/songs")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SongsData insertSongs(@PathVariable Long bandId, @RequestBody SongsData songsData) {
		log.info("Creating Song with {}", songsData);
		return recordLabelService.saveSong(bandId, songsData);
	}

	@GetMapping("/songs")
	public List<SongsData> retrieveAllSongs() {
		log.info("Retrieveing all Songs");
		return recordLabelService.retrieveAllSongs();
	}

	@GetMapping("/band/{bandId}/songs/{songId}")
	public SongsData retrieveSongById(@PathVariable Long songId) {
		log.info("Retrieving song with ID= {}", songId);
		return recordLabelService.retrieveSongById(songId);
	}

	@PutMapping("/band/{bandId}/songs/{songId}")
	public SongsData updateSongs(@PathVariable Long bandId, @PathVariable Long songId,
			@RequestBody SongsData songsData) {
		songsData.setSongId(songId);
		log.info("Updating Song  with ID={}", songsData);
		return recordLabelService.saveSong(bandId, songsData);
	}

	@PostMapping("/albums/{albumId}/songs/{songId}")
	public Map<String, String> addSongToAlbum( @PathVariable Long albumId, @PathVariable Long songId) {
		log.info("Adding song with ID= {} to album with ID= {}", songId, albumId);

		recordLabelService.addSongToAlbum(albumId, songId);

		return Map.of("message", "Song with ID= " + songId + " to album with ID= " + albumId + ".");
	}
}