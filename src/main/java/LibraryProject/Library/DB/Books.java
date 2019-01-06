package LibraryProject.Library.DB;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Books {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private String description;

    @Column(nullable = true)
    private Boolean borrow;


    @Column(nullable = true)
    private Integer count_borrow;
}
