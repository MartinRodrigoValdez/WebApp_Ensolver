package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;


import model.User;

import service.UserService;




@Controller
public class SharedController {

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		int type=(Integer)session.getAttribute("typeUserLoged");
		if( type==1){
			ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
			return view;
		}else{
			ModelAndView view=new ModelAndView("Index");
			view.addObject("UserToLog",new User());
			return view;
		}

	}
	

	
}