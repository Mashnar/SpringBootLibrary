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
    public ModelAndView createNewBook(@Valid Books book, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        book.setBorrow(false);
booksRepository.save(book);
modelAndView.setViewName("/admin/index");
        return  modelAndView;
    }

}
