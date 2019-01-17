package LibraryProject.Library.DB;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class Books {
    public Books(@NotEmpty(message = "*Podaj Nazwe książki") String name, @NotEmpty(message = "*Podaj autora") String author, @NotEmpty(message = "*Podaj opis") String description, Boolean borrow, Integer count_borrow) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.borrow = borrow;
        this.count_borrow = count_borrow;
    }
    public Books()
    {

    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    public Set<BooksUserHistory> getBooksUserHistories() {
        return booksUserHistories;
    }

    public void setBooksUserHistories(Set<BooksUserHistory> booksUserHistories) {
        this.booksUserHistories = booksUserHistories;
    }

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BooksUserHistory> booksUserHistories;
    //relacje
    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    @Column(name = "name")
    @NotEmpty(message = "*Podaj Nazwe książki")
    private String name;
    @Column(name = "author")
    @NotEmpty(message = "*Podaj autora")
    private String author;
    @Column(name = "description",columnDefinition="TEXT")
    @NotEmpty(message = "*Podaj opis")

    private String description;

    @Column(nullable = false)
    private Boolean borrow;


    @Column(nullable = false)
    private Integer count_borrow;


    @ManyToMany(mappedBy = "books")
    private Set<User> User;



    public Set<LibraryProject.Library.DB.User> getUser() {
        return User;
    }

    public void setUser(Set<LibraryProject.Library.DB.User> user) {
        User = user;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getBorrow() {
        return borrow;
    }

    public void setBorrow(Boolean borrow) {
        this.borrow = borrow;
    }

    public Integer getCount_borrow() {
        return count_borrow;
    }

    public void setCount_borrow(Integer count_borrow) {
        this.count_borrow = count_borrow;
    }



}
