package LibraryProject.Library.DB.CRUD;
import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}