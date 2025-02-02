package record.label.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Bands {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bandId;
	
	private String bandName;
	private String genre;
	private String homeTown;
	private String yearsActive;
	
	@OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Musicians> musicians = new HashSet<>();
	

	@OneToMany(mappedBy = "bands", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Songs> songs = new HashSet<>();
	

}
