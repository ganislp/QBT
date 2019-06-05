package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.BulkTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkTicketsRepository extends JpaRepository<BulkTickets, Long> {


}
