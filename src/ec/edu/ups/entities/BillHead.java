package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillHead
 *
 */
@Entity
@Table(name = "BILL_HEADS")
public class BillHead implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hea_id")
	private int heaId;
	
	@Column(name = "hea_subtotal", precision = 12, scale = 2)
	private double heaSubtotal;
	
	@Column(name = "hea_vat", precision = 12, scale = 2)
	private double heaVat;
	
	@Column(name = "hea_date")
	private Calendar heaDate;
	
	@Column(name = "hea_status", columnDefinition = "VARCHAR(1) DEFAULT 'C'")
	private char heaStatus;
	
	@Column(name = "hea_total", precision = 12, scale = 2)
	private double heaTotal;
	
	@Column(name = "hea_deleted", nullable = false, unique = false, columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean heaDeleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "detBillHead")
	private List<BillDetail> heaBillDeatail;

	public BillHead() {
		super();
		this.heaStatus = 'C';
	}
}
