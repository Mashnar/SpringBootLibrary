package LibraryProject.Library.Controllers;

import LibraryProject.Library.DB.Books;
import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.DB.CRUD.PersonalLibraryRepository;
import LibraryProject.Library.DB.CRUD.UserRepository;
import LibraryProject.Library.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import LibraryProject.Library.DB.User;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data

    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    UserService userService;
    @RequestMapping(value="/admin/user", method = RequestMethod.GET)
    public ModelAndView getAllUser()
    {
        Set<User> users = userRepository.getAllUserWithoutAdmins();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users_var",users);
       modelAndView.setViewName("admin/user");
       return modelAndView;

    }

    @RequestMapping(value="/admin/change_stat", method = RequestMethod.GET)
    public String change_stat(@RequestParam("id") Integer user_id)
    {
        User user = userRepository.findById(user_id).get();
    if (user.getActive()==1)
    {
        user.setActive(0);
    }
    else
    {
        user.setActive(1);
    }

        userRepository.save(user);
        return "redirect:/admin/user";
    }



    @RequestMapping(value="/admin/book", method = RequestMethod.GET)
    public ModelAndView add_book(){
        ModelAndView modelAndView = new ModelAndView();
       Books book = new Books();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("/admin/book");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/book", method = RequestMethod.POST)
    public ModelAndView createNewBook(@Valid @ModelAttribute("book") Books book,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Books book_exist = booksRepository.findByName(book.getName());
        if (bindingResult.hasErrors())
        {
            modelAndView.setViewName("/admin/book");


            return  modelAndView;
        }
        if(book_exist != null)
        {

            modelAndView.addObject("messege", 0);

            modelAndView.setViewName("/admin/book");

            return  modelAndView;

        }


        book.setBorrow(false);
    book.setCount_borrow(0);
        booksRepository.save(book);
        modelAndView.setViewName("/admin/book");
        Books book_new = new Books();
        modelAndView.addObject("book", book_new);
        modelAndView.addObject("messege", 1);
        return  modelAndView;
    }

    @RequestMapping(value = "/admin/book_list", method = RequestMethod.GET)
    public ModelAndView createNewBook()
    {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Books> books = booksRepository.findAll();

        modelAndView.addObject("books",books);
        modelAndView.setViewName("/admin/book_list");
        return modelAndView;




    }

    }


