package co.za.quickbuyticketcomponent.repositories;

import co.za.quickbuyticketcomponent.modals.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    long countUserProfileByEmail(String email);
    UserProfile findByEmailAndPassword(String email,String password);
}
