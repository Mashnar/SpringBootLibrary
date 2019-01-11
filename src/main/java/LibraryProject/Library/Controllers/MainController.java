package LibraryProject.Library.Controllers;


import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.DB.CRUD.PersonalLibraryRepository;
import LibraryProject.Library.DB.Personal_Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import LibraryProject.Library.DB.User;
import LibraryProject.Library.DB.CRUD.UserRepository;

import java.util.Set;

@Controller    // This means that this class is a Controller //do zmiany pozniej jak ogarne relacje
// This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    //testtestetest
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PersonalLibraryRepository personal_library;

    @RequestMapping(value = "/add_user/{name}/{e_mail}", method = RequestMethod.GET)
    public @ResponseBody
    String addNewUser(@PathVariable("name") String name
            , @PathVariable("e_mail") String email) {


        User n = new User();
        n.setFirst_name(name);
        n.setEmail(email);

        userRepository.save(n);

        return "Saved";
    }


    @RequestMapping(value = "/add_book/{name}/{author}/{description}", method = RequestMethod.GET)
    public String addBooks(@PathVariable("description") String description, @PathVariable("name") String name, @PathVariable("author") String AuthorName, Model model) {//model sluzy do tego zeby do thymeleaf przekazac

        Books book = new Books();


        book.setAuthor(AuthorName);
        book.setDescription(description);
        book.setName(name);

        booksRepository.save(book);



        return "saved";
    }

    @RequestMapping(value = "set_book/{id_user}/{id_book}")
    public User setBooks(@PathVariable("id_user") Integer user_id, @PathVariable("id_book") Integer book_id) {
        Books book = booksRepository.findById(book_id).get();
        User user = userRepository.findById(user_id).get();
        user.getBooks().add(book);
        userRepository.save(user);


        return null;
    }

    @RequestMapping(value = "all/{id_user}", method = RequestMethod.GET)
    public String getAllUsers(@PathVariable("id_user") Integer User_ID,Model model)
    {
        // This returns a JSON or XML with the users
        User user = userRepository.findById(User_ID).get();
        Set<Books> book = booksRepository.getBooksPerUser(user.getId());

        model.addAttribute("books",book);
        model.addAttribute("messeges",user);


        return "test";

    }


    @RequestMapping("/test")
    public String form() {
        return "index";
    }

}