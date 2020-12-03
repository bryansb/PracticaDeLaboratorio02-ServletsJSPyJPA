package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
@Table(name="PRODUCTS")

public class Product implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int proId;
	private String proName;
	private int proStock;
	private double proPrice;
	private boolean proDeleted;

	public Product() {
		super();
	}
   
}
