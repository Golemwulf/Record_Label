package record.label.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Albums {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;

	private String albumTitle;
	private String yearReleased;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "albums", cascade = CascadeType.PERSIST)
	//@formatter:off
	@JoinTable(name = "album_songs",
			joinColumns = @JoinColumn(name = "album_id"),
			inverseJoinColumns = @JoinColumn(name = "song_id"))
	//@formatter:on
	private Set<Songs> song = new HashSet<>();
}
