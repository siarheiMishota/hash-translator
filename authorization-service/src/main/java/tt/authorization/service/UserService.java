package tt.authorization.service;

import tt.authorization.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAll();

    boolean checkSignIn(String email, String password);

    User save(User user);

    void delete(long id);
}
