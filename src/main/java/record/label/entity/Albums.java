
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
	@ManyToMany(cascade = CascadeType.PERSIST)
	//@formatter:off
	@JoinTable(name = "album_songs",
			joinColumns = @JoinColumn(name = "album_id"),
			inverseJoinColumns = @JoinColumn(name = "song_id"))
	//@formatter:on
	private Set<Songs> songs = new HashSet<>();
}
