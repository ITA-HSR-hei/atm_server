package ch.cnlab.atm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/webpage")
public class WebPageController {
	
	@RequestMapping(value = "{webpage}", method = RequestMethod.GET)
	public String getWebPage(@PathVariable String webpage){
		return webpage;
	}

}
