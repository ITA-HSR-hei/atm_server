package ch.cnlab.atm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.cnlab.atm.domain.User;
import ch.cnlab.atm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		// Load some data
		User user = userService.loadUserByEmail("rstaehli@hsr.ch");

		// Get user who is logged in
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		// Add attribute to jsp page pages/hello.jsp
		model.addAttribute("loggedInUser", auth.getName());
		if (user != null) {
			model.addAttribute("loadedUser", user.getFirstName());
		}
		return "admin";

	}

}