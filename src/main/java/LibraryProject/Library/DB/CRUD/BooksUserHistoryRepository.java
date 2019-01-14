package LibraryProject.Library.DB.CRUD;

import LibraryProject.Library.DB.BooksUserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface BooksUserHistoryRepository extends JpaRepository<BooksUserHistory,Integer> {
    @Query(value = "select * from books_user_history b where b.user_id =?1 and b.book_id=?2",nativeQuery=true)
    BooksUserHistory findByUserIdAndBookID(Integer user_id, Integer book_id );
    @Query(value = "select * from books_user_history join user on books_user_history.user_id=user.user_id where books_user_history.book_id=?1",nativeQuery = true)
    public Set<BooksUserHistory> getHistoryPerBook(Integer id);
    @Query(value = "select * from books_user_history join user on books_user_history.user_id=user.user_id where books_user_history.user_id=?1",nativeQuery = true)
    public Set<BooksUserHistory> getHistoryPerUser(Integer id);

}
