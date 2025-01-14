package record.label.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Songs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;
	
	private String songTitle;
}
