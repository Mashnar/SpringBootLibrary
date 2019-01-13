package LibraryProject.Library.Controllers;

import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.DB.CRUD.UserRepository;
import LibraryProject.Library.DB.User;
import LibraryProject.Library.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Set;

@Controller
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/user/borrow_book" , method = RequestMethod.GET)
    public ModelAndView books()
    {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Books> books = booksRepository.findAll();

        modelAndView.addObject("books",books);
        modelAndView.setViewName("/user/borrow_book");
        return modelAndView;

    }
    @RequestMapping(value="/user/borrow_book_execute" , method = RequestMethod.GET)
    public String borrow_book(@RequestParam("id") Integer book_id)
    {
        Books book = booksRepository.findById(book_id).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());


        user.getBooks().add(book);

        Integer counter = book.getCount_borrow();
        if (counter == null)
        {
            counter = 1;
        }
        else
        {
            counter = counter + 1;
        }
        book.setCount_borrow(counter);
        userRepository.save(user);
        book.setBorrow(true);
        booksRepository.save(book);

        return "redirect:/user/borrow_book";

    }

    @RequestMapping(value="/user/list_book",method = RequestMethod.GET)
    public ModelAndView book_list()
        {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();


            User user = userService.findUserByEmail(auth.getName());
            Set<Books> books = user.getBooks();
            ModelAndView modelAndViev= new ModelAndView();
            modelAndViev.setViewName("user/list_book");
            modelAndViev.addObject("books",books);

            return modelAndViev;
        }



}
