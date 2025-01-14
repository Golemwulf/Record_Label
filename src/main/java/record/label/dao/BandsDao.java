package record.label.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.label.entity.Bands;

public interface BandsDao extends JpaRepository<Bands, Long>{

}
