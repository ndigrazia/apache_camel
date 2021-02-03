package org.mycompany.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spi.AsEndpointUri;
import org.springframework.stereotype.Component;

import org.mycompany.bean.CreateRequestProccessBean;
import org.mycompany.bean.FindAllCharactersBean;
import org.mycompany.bean.FindAllStarShipsBean;
import org.mycompany.bean.GetDarthVaderResponseBean;
import org.mycompany.bean.GetObiWanKenobiResponseBean;
import org.mycompany.exception.ResourceNotFoundException;
import org.mycompany.str.CharactersShipsAggregationStrategy;
import org.mycompany.util.route.HandlingErrorRoute;

@Component
public class ApplicationRoute extends HandlingErrorRoute {
	
	@Override
	public void configure() throws Exception {
		//ErrorHandling
		super.configure();
		
		//Get an existing character by id with Choice EIP
		//https://camel.apache.org/components/latest/eips/choice-eip.html
		from(Uri.GET.value())
		.routeId(Uri.GET.value())
			.log(LoggingLevel.DEBUG, "calling bean method...")
			.choice()
				.id("Character?")
				.when(header("id").isEqualTo("0teUVBC5WU")).id("Darth Vader")
					.bean(GetDarthVaderResponseBean.class, "process")
					.id("GetRequestAProccessBean")
                .when(header("id").isEqualTo("nxpAPnATEb")).id("Obi-Wan Kenobi")
					.bean(GetObiWanKenobiResponseBean.class, "process")
					.id("GetRequestBProccessBean")
                .otherwise()
                	.throwException(new ResourceNotFoundException("Resource not found"))
            .end()
			.log(LoggingLevel.DEBUG, "leaving bean method...");
		
		//Find all characters with Enrich EIP
		//https://camel.apache.org/components/latest/eips/enrich-eip.html
		from(Uri.FIND_ALL.value())
		.routeId(Uri.FIND_ALL.value())
			.log(LoggingLevel.DEBUG, "calling bean method...")
			.to("direct:characters")
			.enrich("direct:characters-starship",
					new CharactersShipsAggregationStrategy())
			.log(LoggingLevel.DEBUG, "leaving bean method...");
		
		//Create a character with Bean EIP
		//https://camel.apache.org/components/latest/eips/bean-eip.html
		from(Uri.ADD.value())
		.routeId(Uri.ADD.value())
			.log(LoggingLevel.DEBUG, "calling bean method...")
			.bean(CreateRequestProccessBean.class, "process")
				.id("CreateRequestProccessBean")
			.log(LoggingLevel.DEBUG, "leaving bean method...");
		
		from("direct:characters")
			.bean(FindAllCharactersBean.class, "process");
		
		from("direct:characters-starship")
			.bean(FindAllStarShipsBean.class, "process");
	}
	
	public enum Uri {

		GET("direct:get-endpoint"),

		FIND_ALL("direct:find-all-endpoint"),
		
		ADD("direct:add-endpoint"),
		
		REMOVE("direct:remove-endpoint"),
		
		UPDATE("direct:update-endpoint");
		
		private final String value;

		Uri(@AsEndpointUri String  value) {
			this. value =  value;
		}
		
		public String value() {
			return this.value;
		}
		
	}
	
}
