package record.label.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import record.label.entity.Musicians;

public interface MusiciansDao extends JpaRepository<Musicians, Long>{

}
