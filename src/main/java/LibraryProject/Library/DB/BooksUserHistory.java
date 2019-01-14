package LibraryProject.Library.DB;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(BooksUserHistoryId.class)
public class BooksUserHistory{


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "book_id")
    public Books books;
    @Column(name="borrow_date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date borrowDate;
    @Column(name="return_date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date returnDate;

}
@Embeddable
class BooksUserHistoryId implements Serializable
{
private int user;
private int books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksUserHistoryId that = (BooksUserHistoryId) o;
        return user == that.user &&
                books == that.books;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, books);
    }
}


