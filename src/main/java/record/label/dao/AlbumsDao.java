package record.label.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.label.entity.Albums;

public interface AlbumsDao extends JpaRepository<Albums, Long>{

}
