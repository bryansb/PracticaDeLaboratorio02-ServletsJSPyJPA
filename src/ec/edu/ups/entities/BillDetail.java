package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillDetail
 *
 */
@Entity
@Table(name = "BILL_DETAILS")
public class BillDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "det_id")
	private int detId;
	
	@Column(name = "det_amount")
	private int detAmount;
	
	@Column(name = "det_unit_price", precision = 12, scale = 2)
	private double detUnitPrice;
	
	@Column(name = "det_total", precision = 12, scale = 2)
	private double detTotal;
	
	@Column(name = "det_deleted", columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean detDeleted;

	@ManyToOne
	@JoinColumn
	private BillHead detBillHead;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "proBillDetail")
	private Product detProduct;
	
	public BillDetail() {
		super();
	}
}
