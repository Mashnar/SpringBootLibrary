package LibraryProject.Library.DB;

import javax.persistence.*;

public class Personal_Library {

    @Entity // This tells Hibernate to make a table out of this class
    public class Books {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;

        @OneToOne
        @JoinColumn(name = "user_id")
        private User User;

        private String name;

        private String author;

        private String description;


    }

}
