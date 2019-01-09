package LibraryProject.Library.DB;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import  java.sql.Timestamp;
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "personal_library")
public class Personal_Library {



        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
private User user;
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







        private String name;

        private String author;

        private String description;
        @Column
        @CreationTimestamp
        private LocalDateTime createDateTime;

        @Column
        @UpdateTimestamp
        private LocalDateTime updateDateTime;



}
