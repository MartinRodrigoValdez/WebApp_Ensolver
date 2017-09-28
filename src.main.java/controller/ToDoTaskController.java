package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import model.ToDoTask;
import model.User;
import service.ToDoTaskService;
import service.UserService;

@Controller
@RequestMapping("ToDoTask")
public class ToDoTaskController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	ToDoTaskService toDoTaskService;
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public ModelAndView HomeUser(HttpServletRequest request,RedirectAttributes atributos){
		HttpSession session = request.getSession(false);
		int type=(Integer)session.getAttribute("typeUserLoged");
		
		if (type==1){
			ModelAndView view = new ModelAndView("ToDoTask/UserHome");
			 /*Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
			 if (inputFlashMap != null) {
				  view.addObject("error",inputFlashMap.get("error"));
			  }
			view.addObject("newUser", new User());*/
			
			//hay que traer la lista de odos
			
			List<ToDoTask> toDoList=userService.getListTaskOfUsername((String) request.getSession().getAttribute("userLoged"));
			
			
		/*	for (ToDoTask task: toDoList){
				System.out.println(task.getDueDate());
				task.setDueDate(task.getDueDate().plusDays(1));
				System.out.println(task.getTitle());
				System.out.println(task.getDueDate());
			}
			*/
			

			
			
			view.addObject("toDoList",toDoList);

			
			view.addObject("newToDoTask", new ToDoTask());
			view.addObject("toDoTask", new ToDoTask());
			return view;
			
		}else{
			ModelAndView view = new ModelAndView("redirect:/");
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("ERROR IN CONTENT", "Don't have acces pleasse Log In.");
			atributos.addFlashAttribute("error", errors);
			

			return view;
			
		}
	}
		
	@RequestMapping(path="/SaveNewToDoTask",method=RequestMethod.POST)
	public ModelAndView SaveNewToDoTask(@ModelAttribute("newToDoTask")ToDoTask newToDoTask,RedirectAttributes atributos,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		int type=(Integer)session.getAttribute("typeUserLoged");
			
		
		
		if (type==1){
			ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
			//userService.findUserByName();
			if (toDoTaskService.newToDoTask(newToDoTask,(String) request.getSession().getAttribute("userLoged")) ){
				atributos.addFlashAttribute("message", "New to-do task created.");
			}else{
				
			}
				 /*Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
				 if (inputFlashMap != null) {
					  view.addObject("error",inputFlashMap.get("error"));
				  }
				view.addObject("newUser", new User());*/
			return view;
		}else{
			ModelAndView view = new ModelAndView("redirect:/");
			view.addObject("error", "Don't have acces pleasse Log In.");
			return view;
		}
	}

	@RequestMapping(path="/DeleteToDoTask",method=RequestMethod.POST)
	public ModelAndView DeleteToDoTask(@ModelAttribute("toDoTask")ToDoTask deleteToDoTask,RedirectAttributes atributos,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		int type=(Integer)session.getAttribute("typeUserLoged");
			
		if (type==1){
			ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
			//userService.findUserByName();
			if (toDoTaskService.deleteToDoTask(deleteToDoTask,(String) request.getSession().getAttribute("userLoged")) ){
				atributos.addFlashAttribute("message", "One To-do task deleted.");
			}else{
				
			}
				 /*Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
				 if (inputFlashMap != null) {
					  view.addObject("error",inputFlashMap.get("error"));
				  }
				view.addObject("newUser", new User());*/
			return view;
		}else{
			ModelAndView view = new ModelAndView("redirect:/");
			view.addObject("error", "Don't have acces pleasse Log In.");
			return view;
		}
	}

	
	
	
	@RequestMapping(value = "FindToDoTaskDetails/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ToDoTask> FindToDoTaskDetails(@PathVariable("id") Long idOfToDoTask,HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		int type=(Integer)session.getAttribute("typeUserLoged");
		ToDoTask toDoTaskDetails=null;
		if(type==1){
			toDoTaskDetails=toDoTaskService.findToDoTaskWithIdOfThisUser(idOfToDoTask,(String) request.getSession().getAttribute("userLoged"));
			//toDoTaskDetails=toDoTaskService.findToDoTaskWithIdOfThisUser(idOfToDoTask,"martin");
			
			if(toDoTaskDetails!=null){
				toDoTaskDetails.setDueDate(toDoTaskDetails.getDueDate().plusDays(1));
				System.out.print(toDoTaskDetails.getDueDate());
				
				
				return new ResponseEntity<ToDoTask>(toDoTaskDetails, HttpStatus.OK);
			}else{
				
				return new ResponseEntity<ToDoTask>(toDoTaskDetails, HttpStatus.LOCKED);
			}
		}else{
			
			return new ResponseEntity<ToDoTask>(toDoTaskDetails, HttpStatus.UPGRADE_REQUIRED);
		}
		
		
	}
		
		
		@RequestMapping(path="/EditToDoTask",method=RequestMethod.POST)
		public ModelAndView EditToDoTask(@ModelAttribute("toDoTask")ToDoTask toDoTask,RedirectAttributes atributos,HttpServletRequest request){
			HttpSession session = request.getSession(false);
			int type=(Integer)session.getAttribute("typeUserLoged");
				
			if (type==1){
				ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
				//userService.findUserByName();
				if (toDoTaskService.editToDoTask(toDoTask,(String) request.getSession().getAttribute("userLoged")) ){
					atributos.addFlashAttribute("message", "One to-do task edited.");
				}else{
					
				}
					 /*Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
					 if (inputFlashMap != null) {
						  view.addObject("error",inputFlashMap.get("error"));
					  }
					view.addObject("newUser", new User());*/
				return view;
			}else{
				ModelAndView view = new ModelAndView("redirect:/");
				view.addObject("error", "Don't have acces pleasse Log In.");
				return view;
			}
		}
		
		
		
		
		@RequestMapping(path="/SwitchToDoTask",method=RequestMethod.POST)
		public ModelAndView SwitchToDoTask(@ModelAttribute("toDoTask")ToDoTask toDoTask,RedirectAttributes atributos,HttpServletRequest request){
			HttpSession session = request.getSession(false);
			int type=(Integer)session.getAttribute("typeUserLoged");
				
			if (type==1){
				ModelAndView view = new ModelAndView("redirect:/ToDoTask/");
				//userService.findUserByName();
				if (toDoTaskService.switchToDoTask(toDoTask,(String) request.getSession().getAttribute("userLoged")) ){
					atributos.addFlashAttribute("message", "One to-do task switched status.");
				}else{
					
				}
					 /*Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
					 if (inputFlashMap != null) {
						  view.addObject("error",inputFlashMap.get("error"));
					  }
					view.addObject("newUser", new User());*/
				return view;
			}else{
				ModelAndView view = new ModelAndView("redirect:/");
				view.addObject("error", "Don't have acces pleasse Log In.");
				return view;
			}
		}
	

	
	
	
	
	
	
	
	
	
	
	
}
