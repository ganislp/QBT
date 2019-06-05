package co.za.quickbuyticketcomponent.modals;

import javax.persistence.*;

/**
 * Created by gVadlamuri on 12/28/2017.
 */
@Entity
@Table(name="TicketType")
public class TicketTypes implements java.io.Serializable {


    private Integer id;
    private String name;



    public TicketTypes() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_type_id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TicketTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}