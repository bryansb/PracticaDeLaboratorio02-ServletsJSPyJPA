package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity
@Table(name="COMPANIES")

public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int comId;
	private String comName;
	private boolean comDeleted;

	public Company() {
		super();
	}
	
}
