package LibraryProject.Library.DB;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private Set<Personal_Library>personal_library;

    public Set<Personal_Library> getPersonal_library() {
        return personal_library;
    }

    public void setPersonal_library(Set<Personal_Library> personal_library) {
        this.personal_library = personal_library;
    }

    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    private String last_name;


    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;




    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Column(name = "active")
    private int active;



    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Column(name = "password")
    @NotEmpty(message = "Podaj haslo")
    @Length(min = 5, message = "*Your password must have at least 5 characters")

    private String password;


    private Set<Books> books = new HashSet<Books>(0);
    @Column
    @CreationTimestamp
    @DateTimeFormat(pattern = "\"dd/MM/yyyy\"")
    private LocalDateTime createDateTime;

    @Column
    @UpdateTimestamp
    @DateTimeFormat(pattern = "\"dd/MM/yyyy\"")
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



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}