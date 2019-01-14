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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
public class UserController
{


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

    @RequestMapping(value = "/user/borrow_book", method = RequestMethod.GET)
    public ModelAndView books() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Books> books = booksRepository.findAll();

        modelAndView.addObject("books", books);
        modelAndView.setViewName("/user/borrow_book");
        return modelAndView;

    }

    @RequestMapping(value = "/user/borrow_book_execute", method = RequestMethod.GET)
    public String borrow_book(@RequestParam("id") Integer book_id) {
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
        } else
        {
            counter = counter + 1;
        }
        book.setCount_borrow(counter);
        userRepository.save(user);
        book.setBorrow(true);
        booksRepository.save(book);

        return "redirect:/user/borrow_book";

    }

    @RequestMapping(value = "/user/list_book", method = RequestMethod.GET)
    public ModelAndView book_list()
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());
        Set<Books> books = user.getBooks();
        ModelAndView modelAndViev = new ModelAndView();
        modelAndViev.setViewName("user/list_book");
        modelAndViev.addObject("books", books);

        return modelAndViev;
    }

    @RequestMapping(value = "/user/return_book", method = RequestMethod.GET)
    public String return_book(@RequestParam("id") Integer book_id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());
        Books book = booksRepository.findById(book_id).get();

        book.setBorrow(false);
        user.getBooks().remove(book);
        BooksUserHistory booksUserHistory = booksUserHistoryRepository.findByUserIdAndBookID(user.getId(), book.getId());
        booksUserHistory.setReturnDate(new Date());
        booksUserHistoryRepository.save(booksUserHistory);
        userRepository.save(user);
        booksRepository.save(book);
        return "redirect:/user/list_book";
    }


    @RequestMapping(value = "/user/history", method = RequestMethod.GET)
    public ModelAndView get_personal()
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        Set<BooksUserHistory> booksUserHistory = booksUserHistoryRepository.getHistoryPerUser(user.getId());
        modelAndView.addObject("pers",booksUserHistory);
        modelAndView.setViewName("/user/history");


        return modelAndView;

    }

    @RequestMapping(value = "/user/personal",method = RequestMethod.GET)
    public ModelAndView personal_library()
    {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());

        Set<Personal_Library> personal_libraries = user.getPersonal_library();

        modelAndView.addObject("personal_library", personal_libraries);
        modelAndView.setViewName("/user/personal");
        return modelAndView;

    }
    @RequestMapping(value = "/user/add_personal_book",method = RequestMethod.GET)
    public ModelAndView add_personal_book()
    {
        ModelAndView modelAndView = new ModelAndView();
        Personal_Library book = new Personal_Library();
        modelAndView.addObject("book",book);
        modelAndView.setViewName("/user/add_personal_book");
        return modelAndView;
    }
    @RequestMapping(value = "/user/add_personal_book",method = RequestMethod.POST)
    public ModelAndView send_personal_book(@Valid @ModelAttribute("book") Personal_Library personal_library, BindingResult bindingResult)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        User user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        Personal_Library personal_library_exist = personalLibraryRepository.getBooksWithNameAndUserID(user.getId(),personal_library.getName());
        if(bindingResult.hasErrors())
        {
            modelAndView.setViewName("/user/add_personal_book");
            return modelAndView;
        }
        if(personal_library_exist!=null)
        {
            modelAndView.addObject("messege", 0);
            modelAndView.setViewName("/user/add_personal_book");
            return modelAndView;
        }

        personal_library.setUser(user);
        personalLibraryRepository.save(personal_library);
Personal_Library new_personal=new Personal_Library();
modelAndView.addObject("book",new_personal);
modelAndView.setViewName("redirect:/user/personal");
        return modelAndView;

    }






}
