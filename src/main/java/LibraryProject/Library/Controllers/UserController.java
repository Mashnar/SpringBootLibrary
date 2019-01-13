package LibraryProject.Library.Controllers;

import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.BooksUserHistory;
import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.DB.CRUD.BooksUserHistoryRepository;
import LibraryProject.Library.DB.CRUD.PersonalLibraryRepository;
import LibraryProject.Library.DB.CRUD.UserRepository;
import LibraryProject.Library.DB.Personal_Library;
import LibraryProject.Library.DB.User;
import LibraryProject.Library.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    PersonalLibraryRepository personalLibraryRepository;
    @Autowired
    BooksUserHistoryRepository booksUserHistoryRepository;

    @RequestMapping(value="/user/borrow_book" , method = RequestMethod.GET)
    public ModelAndView books()
    {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Books> books = booksRepository.findAll();
        System.out.println(books);
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
        BooksUserHistory booksUserHistory = new BooksUserHistory();

        booksUserHistory.setUser(user);
        booksUserHistory.setBooks(book);
        booksUserHistory.setBorrowDate(new Date());



        user.getBooks().add(book);
        user.getBooksUserHistories().add(booksUserHistory);


        //book.getBooksUserHistories().add(booksUserHistory);
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
    @RequestMapping(value="/user/return_book",method = RequestMethod.GET)
    public String return_book(@RequestParam("id") Integer book_id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());
        Books book = booksRepository.findById(book_id).get();

        book.setBorrow(false);
        user.getBooks().remove(book);
        BooksUserHistory booksUserHistory = booksUserHistoryRepository.findByUserIdAndBookID(user.getId(),book.getId());
        booksUserHistory.setReturnDate(new Date());
        booksUserHistoryRepository.save(booksUserHistory);
        userRepository.save(user);
        booksRepository.save(book);
        return "redirect:/user/list_book";
    }


    @RequestMapping(name = "/user/test",method = RequestMethod.GET)
    public String form(Model model) {

        User user = userRepository.findById(2).get();
        Personal_Library pers_book = new Personal_Library();
        pers_book.setAuthor("test");
        pers_book.setDescription("fkdmifgjsdfsdfsd");
        pers_book.setName("test_nazwa");
        pers_book.setUser(user);
        personalLibraryRepository.save(pers_book);


Set<BooksUserHistory> booksUserHistories =user.getBooksUserHistories();




model.addAttribute("messeges",booksUserHistories);
        return "test";

    }

}
