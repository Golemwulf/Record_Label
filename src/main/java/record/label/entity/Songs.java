package record.label.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "band_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Bands bands;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	//@formatter:off
	@JoinTable(name = "album_songs",
	joinColumns = @JoinColumn(name = "song_id"),
	inverseJoinColumns = @JoinColumn(name = "album_id"))
//@formatter:on
	private Set<Albums> albums = new HashSet<>();
	
}