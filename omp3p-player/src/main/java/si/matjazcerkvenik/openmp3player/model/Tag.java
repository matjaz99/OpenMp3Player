package si.matjazcerkvenik.openmp3player.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name = null;
	
	@Column(name = "color")
	private String color = null;
	
	public Tag() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
