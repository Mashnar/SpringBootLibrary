package LibraryProject.Library.DB.CRUD;




import LibraryProject.Library.DB.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BooksRepository extends CrudRepository<Books, Integer> {

@Query(value = "select * from Books b join user_books on b.Id = user_books.books_id where user_books.user_id = ?1",nativeQuery = true)
public Set<Books> getBooksPerUser(Integer id);
@Query(value ="select * from Books where name=?1",nativeQuery = true)
Books findByName(String name);






}