package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import payroll.models.User;

@Controller
@ComponentScan("payroll.configs.AppConfig")
public class GreetingController {
	

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

		model.addAttribute("name", name);
		return "greeting";
	}


	@GetMapping("/greeting/{name}")
	/**
	 * This mapping demonstrates the @PathVariable annotation which allows us to do things in s restful way
	 * @param name
	 * @param model
	 * @return
	 */
	public String greetingWithName(@PathVariable("name") String name, Model model) {
		
		//check if the path variable is an integer

		try{

		
			int id = Integer.parseInt(name);

			return this.greetingWithId(id , model);

		}catch(NumberFormatException e){

			model.addAttribute("name", name);	
			return "greeting";			
		}
	}

	@Autowired User person;
	public String greetingWithId(int id, Model model) {

		try{

			User user1 = person.getForId(id);		
			model.addAttribute("name", user1.getName());	

		}catch(Exception e){

			model.addAttribute("name", "Unknown user");	
		}
			
		
		return "greeting";
	}


}