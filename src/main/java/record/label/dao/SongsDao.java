package record.label.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.label.entity.Songs;

public interface SongsDao extends JpaRepository<Songs, Long>{

}
