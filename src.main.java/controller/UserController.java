package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import model.User;
import service.UserService;

@Controller
@RequestMapping("User")
public class UserController {

	@Autowired
	UserService userService;
	
/*	@Autowired
    SecurityService securityService;*/
	
	@RequestMapping(path="/SignUp",method=RequestMethod.GET)
	public ModelAndView SignUpser(HttpServletRequest request){
		ModelAndView view = new ModelAndView("User/SignUp");
		 Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		 if (inputFlashMap != null) {
			  view.addObject("error",inputFlashMap.get("error"));
		  }
		view.addObject("newUser", new User());
		return view;
	}
	
	
	@RequestMapping(path="SignUpSucces", method=RequestMethod.POST)
	public ModelAndView newUser(@ModelAttribute("newUser")User user, HttpServletRequest request, RedirectAttributes atributos){
		HttpSession session = request.getSession(false);
		if(!userService.createNewUser(user)){
			ModelAndView view = new ModelAndView("redirect:/User/SignUp");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ERROR IN CONTENT", "This user already exist");
			atributos.addFlashAttribute("error", errors);
			//securityService.autologin(user.getName(), user.getPassword());
			return view;
		}else{
			ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
			session.setAttribute("typeUserLoged", 1);
			session.setAttribute("userLoged", user.getName());
			//session.setAttribute("idUser", ((User)result.getContent()).getId());
			atributos.addFlashAttribute("message", "Welcome");
				
			
			
			view.addObject("message", "Se ha registrado correctamente");
			return view;
		}
	}
	
	@RequestMapping(path="LogIn", method=RequestMethod.POST)
	public ModelAndView logIn(@ModelAttribute("UserToLog")User user , HttpServletRequest request,RedirectAttributes atributos){

		ModelAndView view=new ModelAndView();
		HttpSession session = request.getSession(false);
		
		if(userService.logInUser(user)){
			session.setAttribute("typeUserLoged", 1);
			session.setAttribute("userLoged", user.getName());
			//session.setAttribute("idUser", ((User)result.getContent()).getId());
			atributos.addFlashAttribute("message", "Welcome");
				view.setViewName("redirect:/ToDoTask/");
				//view.addObject("user", ((User)result.getContent()));
				return view;
		}else{
			
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ERROR IN CONTENT", "Invalid credencials");
			atributos.addFlashAttribute("error", errors);
			view.setViewName("redirect:/");
			return view;
		}
	}
	
	@RequestMapping(path="LogOut", method=RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest request,RedirectAttributes atributos){
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/");
		session.removeAttribute("userLoged");
		session.removeAttribute("idUser");
		session.setAttribute("typeUserLoged", 0);
		atributos.addFlashAttribute("message", "Goodbye");
		return view;
	}
	
	
	//Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			
			
			
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	
}
