package apap.tutorial.cineplux.repository;

import apap.tutorial.cineplux.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDB extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
