package record.label.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Musicians {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long musicianId;
	
	private String firstName;
	private String lastName;
	private String instrument;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "band_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Bands band;
}