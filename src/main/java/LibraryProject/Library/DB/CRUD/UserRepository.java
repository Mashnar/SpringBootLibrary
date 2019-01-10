package LibraryProject.Library.DB.CRUD;
import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query(value = "select * from User u join user_role b on u.user_id = b.user_id where b.role_id = 2",nativeQuery = true)
    public Set<User> getAllUserWithoutAdmins();
}