package co.za.quickbuyticketcomponent.modals;
// Generated Dec 28, 2017 1:58:40 PM by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.Date;

/**
 * AccountType generated by hbm2java
 */
@Entity
@Table(name="verified_tickets"
)
public class VerifiedTickets implements java.io.Serializable {

    private Integer id;
    private String referenceNumber;
    private Date verifiedTime;

    public VerifiedTickets() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verified_tickets_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "reference_number", unique = true, nullable = false)
    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Column(name = "verified_time", nullable = false)
    public Date getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(Date verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    public VerifiedTickets(String referenceNumber, Date verifiedTime) {
        this.referenceNumber = referenceNumber;
        this.verifiedTime = verifiedTime;
    }
}

