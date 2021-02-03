package org.mycompany.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationSwaggerWelcomeController {

	private static final String SWAGGER_UI_URI = 
			"redirect:/webjars/swagger-ui/index.html?url=/api/swagger&validatorUrl=";
	
	/**
	 * Forward to Swagger UI uri
	 * 
	 * http://localhost:port/swagger-ui
	 * 
	 * @return URI
	 */
	@RequestMapping("/swagger-ui")
	public String redirectToUi() {
		return SWAGGER_UI_URI;
	}
	
}
