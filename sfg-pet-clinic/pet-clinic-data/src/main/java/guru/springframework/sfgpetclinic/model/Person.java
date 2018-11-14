package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
// @NoArgsConstructor
// @AllArgsConstructor
@Setter
@Getter
public class Person extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	public Person(Long id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person() {
		super();
	}

}
