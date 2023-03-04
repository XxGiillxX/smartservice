package br.com.smartservice.daos;

import br.com.smartservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserDAO extends JpaRepository<User, UUID> {

    boolean existsUserByRegistrationNumber(String registrationNumber);

}
