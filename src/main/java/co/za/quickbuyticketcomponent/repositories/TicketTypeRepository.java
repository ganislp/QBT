package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.AccountType;
import co.za.quickbuyticketcomponent.modals.TicketTypes;
import co.za.quickbuyticketcomponent.modals.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketTypes, Integer> {

}
