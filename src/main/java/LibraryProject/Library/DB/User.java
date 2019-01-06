package LibraryProject.Library.DB;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Set<Books> books = new HashSet<Books>(0);
    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    //relacje



    public void setBooks(Set<Books> books) {
        this.books = books;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @Access(AccessType.PROPERTY)
    @JoinTable(name="user_books", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="books_id"))
    public Set<Books> getBooks() {
        return this.books;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}