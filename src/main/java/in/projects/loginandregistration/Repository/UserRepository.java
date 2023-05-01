package in.projects.loginandregistration.Repository;

import in.projects.loginandregistration.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByemail(String email);
}
