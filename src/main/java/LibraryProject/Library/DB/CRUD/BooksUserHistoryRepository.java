package LibraryProject.Library.DB.CRUD;

import LibraryProject.Library.DB.BooksUserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksUserHistoryRepository extends JpaRepository<BooksUserHistory,Integer> {
    @Query(value = "select * from books_user_history b where b.user_id =?1 and b.book_id=?2",nativeQuery=true)
    BooksUserHistory findByUserIdAndBookID(Integer user_id, Integer book_id );

}
