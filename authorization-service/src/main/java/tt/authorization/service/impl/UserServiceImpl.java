package tt.authorization.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tt.authorization.entity.User;
import tt.authorization.repository.UserRepository;
import tt.authorization.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public boolean checkSignIn(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return false;
        }
        Optional<User> userOptional = findByEmail(email);

        if (!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        return user.getPassword().equals(password);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
