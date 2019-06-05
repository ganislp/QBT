package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.TicketTypes;
import co.za.quickbuyticketcomponent.modals.VerifiedTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedTicketsRepository extends JpaRepository<VerifiedTickets, Long> {

    VerifiedTickets findByReferenceNumber(String referenceNumber);

}
