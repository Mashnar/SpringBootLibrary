package LibraryProject.Library.Services;

import LibraryProject.Library.DB.CRUD.UserRepository;
import LibraryProject.Library.DB.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }
}