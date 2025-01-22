package record.label.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Songs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;
	
	private String songTitle;
	
	
	@OneToMany(mappedBy = "songs", cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Bands> bands = new HashSet<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "songs", cascade = CascadeType.PERSIST)
	private Set<Albums> albums = new HashSet<>();
	
}
