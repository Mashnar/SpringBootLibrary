package LibraryProject.Library.DB;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;

import  java.sql.Timestamp;
public class Personal_Library {

    @Entity // This tells Hibernate to make a table out of this class
    public class Books {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public LibraryProject.Library.DB.User getUser() {
            return User;
        }

        public void setUser(LibraryProject.Library.DB.User user) {
            User = user;
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

        @OneToOne
        @JoinColumn(name = "user_id")
        private User User;

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

}
