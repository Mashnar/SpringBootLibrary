package LibraryProject.Library.DB;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class Books {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    public Set<LibraryProject.Library.DB.User> getUser() {
        return User;
    }

    public void setUser(Set<LibraryProject.Library.DB.User> user) {
        User = user;
    }

    @ManyToMany(mappedBy = "books")
    private Set<User> User;

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


    //relacje
    @Column
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private String name;

    private String author;

    private String description;

    @Column(nullable = true)
    private Boolean borrow;


    @Column(nullable = true)
    private Integer count_borrow;
}
