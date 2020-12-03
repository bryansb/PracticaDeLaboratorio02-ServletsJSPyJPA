package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillDetail
 *
 */
@Entity
@Table(name="BILL_DETAILS")

public class BillDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int detId;
	private int detAmount;
	private double detUnitPrice;
	private double detTotal;
	private boolean detDeleted;

	public BillDetail() {
		super();
	}
   
}
