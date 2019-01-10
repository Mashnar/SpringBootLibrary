package LibraryProject.Library.Controllers;

import LibraryProject.Library.DB.CRUD.BooksRepository;
import LibraryProject.Library.DB.CRUD.PersonalLibraryRepository;
import LibraryProject.Library.DB.CRUD.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import LibraryProject.Library.DB.User;

import java.util.Set;

@Controller
public class AdminController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    //testtestetest
    private UserRepository userRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PersonalLibraryRepository personal_library;
    @RequestMapping(value="/admin/user", method = RequestMethod.GET)
    public ModelAndView getAllUser()
    {
        Set<User> users = userRepository.getAllUserWithoutAdmins();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users_var",users);
       modelAndView.setViewName("admin/user");
       return modelAndView;

    }
}