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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private int proId;
	
	@Column(name = "pro_name", length = 255, nullable = false)
	private String proName;
	
	@Column(name = "por_stock")
	private int proStock;
	
	@Column(name = "pro_price", precision = 12, scale = 2)
	private double proPrice;
	
	@Column(name = "pro_deleted", columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean proDeleted;

	@OneToOne
	@JoinColumn
	private BillDetail proBillDetail;
	
	public Product() {
		super();
	}
   
}
