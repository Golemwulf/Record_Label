package record.label.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import record.label.dao.AlbumsDao;
import record.label.dao.BandsDao;
import record.label.dao.MusiciansDao;
import record.label.dao.SongsDao;

@Service
public class RecordLabelService {
	
	@Autowired
	private BandsDao bandDao;
	@Autowired
	private MusiciansDao musiciansDao;
	@Autowired
	private AlbumsDao albumsDao;
	private SongsDao songsDao;
}
