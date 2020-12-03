package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "CATEGORIES")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int catId;
	private String catName;
	private boolean catDeleted;

	public Category() {
		super();
	}
   
}
