package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.CustomerTickets;
import co.za.quickbuyticketcomponent.modals.CustomerTransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<CustomerTransactionDetails, Long> {

    CustomerTransactionDetails findByCustomStr1(String referenceNumber);
}
