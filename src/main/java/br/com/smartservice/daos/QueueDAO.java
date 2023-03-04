package br.com.smartservice.daos;
import br.com.smartservice.models.Queue;
import br.com.smartservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QueueDAO extends JpaRepository<Queue, UUID>{

    void deleteByUser(User user);
}
