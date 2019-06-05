package co.za.quickbuyticketcomponent.modals;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gVadlamuri on 12/28/2017.
 */
@Entity
@Table(name="BulkTickets")
public class BulkTickets implements java.io.Serializable {


    private int id;
    private UserProfile user_profile_id;
    private String reference_number;
    private int amount_paid;
    private String responsible_person;
    private String filename;
    private TicketTypes ticketTypes;



    public BulkTickets() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false)
    public UserProfile getUser_profile_id() {
        return user_profile_id;
    }

    public void setUser_profile_id(UserProfile user_profile_id) {
        this.user_profile_id = user_profile_id;
    }

    @Column(name = "reference_number")
    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    @Column(name = "amount_paid")
    public int getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(int amount_paid) {
        this.amount_paid = amount_paid;
    }

    @Column(name = "responsible_person")
    public String getResponsible_person() {
        return responsible_person;
    }

    public void setResponsible_person(String responsible_person) {
        this.responsible_person = responsible_person;
    }

    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    public TicketTypes getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(TicketTypes ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    @Override
    public String toString() {
        return "BulkTickets{" +
                "id=" + id +
                ", user_profile_id=" + user_profile_id +
                ", reference_number='" + reference_number + '\'' +
                ", amount_paid=" + amount_paid +
                ", responsible_person='" + responsible_person + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}