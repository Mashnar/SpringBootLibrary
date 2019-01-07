package LibraryProject.Library.DB.CRUD;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import LibraryProject.Library.DB.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BooksRepository extends CrudRepository<Books, Integer> {
@Query("SELECT * FROM USER");
Set<Books>temp getId();

}