package LibraryProject.Library.DB.CRUD;

import LibraryProject.Library.DB.Personal_Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonalLibraryRepository extends JpaRepository<Personal_Library,Integer> {
    @Query(value = "select * from personal_library pl where pl.user_id=?1 and pl.name=?2",nativeQuery = true)
    Personal_Library getBooksWithNameAndUserID(Integer id_user,String book_name);

}
