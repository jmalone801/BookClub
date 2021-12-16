package com.jm.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jm.bookclub.models.Book;
import com.jm.bookclub.models.LoginUser;
import com.jm.bookclub.models.User;
import com.jm.bookclub.services.BookService;
import com.jm.bookclub.services.UserService;


@Controller
public class HomeController {
	

    @Autowired
    private BookService bookServ;
    @Autowired
    private UserService userServ;
    
    
    //===========================================================
    // Render Login/Register
    //===========================================================
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
  
    //===========================================================
    // Process Register Route
    //===========================================================
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, 
    		Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("userId", newUser.getId());
        return "redirect:/dashboard";
    }
    
    
    //===========================================================
    // Process Login Route
    //===========================================================
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, 
    		Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
        return "redirect:/dashboard";
    }
    
    
    //===========================================================
    // Render Dashboard Route
    //===========================================================
    
    @GetMapping("/dashboard")
    public String home(HttpSession session, Model model, RedirectAttributes flash) {
    	//Confirms if user is in session
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("notLoggedIn", "Please register or login before entering site!");
    		return "redirect:/";
    	}
    	//Renders user information
    	User user = userServ.getUserInfo(userId);
    	model.addAttribute("loggedUser", user);
    	
    	//Renders all books
    	List<Book> books = bookServ.getAllBooks();
    	model.addAttribute("books", books);
        
        return "dashboard.jsp";
    }
    
    
    //===========================================================
    // Logout Route
    //===========================================================
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
    //===========================================================
    // New Book Route
    //===========================================================
    
    @GetMapping("/books/new") 
    public String newBook(HttpSession session, RedirectAttributes flash, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("notLoggedIn", "Please register or login before entering site!");
    		return "redirect:/";
    	}
    	model.addAttribute("userId", userId);
    	model.addAttribute("newBook", new Book());
        return "newbook.jsp";
    }
    
    @PostMapping("/createbook")
    public String createBook(@Valid @ModelAttribute("newBook") Book book, BindingResult result, HttpSession session, Model model) {
    	if(result.hasErrors()) {
    		Long userId = (Long) session.getAttribute("userId");
    		model.addAttribute("userId", userId);
    		return "newbook.jsp";
    	} 
    	bookServ.saveBook(book);
    	return "redirect:/dashboard";
    }
    
    
    //===========================================================
    // Show One Book Route
    //===========================================================
    
    @GetMapping("/book/{id}")
    public String showOneBook(@PathVariable("id") Long bookId, HttpSession session, Model model, RedirectAttributes flash) {
    	//Confirms if user is in session
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("notLoggedIn", "Please register or login before entering site!");
    		return "redirect:/";
    	}
    	//Renders one book
    	Book book = bookServ.findOneBook(bookId);
    	model.addAttribute("book", book);
    	
    	User user = userServ.getUserInfo(userId);
    	model.addAttribute("loggedUser", user);
    	
    return "showone.jsp";
    }
    
    
    //===========================================================
    // Edit Book Route
    //===========================================================
    
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, HttpSession session, Model model, RedirectAttributes flash) {
    	//Confirms if user is in session
    	Long userId = (Long) session.getAttribute("userId");
    	if(userId == null) {
    		flash.addFlashAttribute("notLoggedIn", "Please register or login before entering site!");
    		return "redirect:/";
    	}
    	//Renders one book
    	Book book = bookServ.findOneBook(bookId);
    	model.addAttribute("book", book);
    	
    return "edit.jsp";
    }
    
    @PutMapping("/update/{id}")
    public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	if(result.hasErrors()) {
    		return "edit.jsp";
    	}
    	bookServ.saveBook(book);
    	return "redirect:/dashboard";
    }
    
    
    //===========================================================
    // Delete Book
    //===========================================================
    
    @RequestMapping(value="/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
    	bookServ.deleteBook(bookId);
    	return "redirect:/dashboard";
    }
    
    
    
}

