package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    AccountType findByAccountTypeId(int accountTypeId);
}
