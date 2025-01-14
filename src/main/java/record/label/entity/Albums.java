package record.label.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Albums {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	
	private String albumTitle;
	private String yearReleased;
	
	
}
