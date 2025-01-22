package record.label.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
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
	
	
	@OneToMany(mappedBy = "musicians", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Bands> bands= new HashSet<>();
}
