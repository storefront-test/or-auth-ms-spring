package application.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Auth API")
@RequestMapping("/")
public class AuthController {
    
    private static Logger logger =  LoggerFactory.getLogger(AuthController.class);

    /**
     * Handle auth header
     * @return HTTP 200 if success
     */
    @ApiOperation(value = "Get authentication api")
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    @ResponseBody ResponseEntity<?> getAuthenticate() {
    	logger.debug("GET /authenticate");
    	
    	return ResponseEntity.ok().build();
    }
    
	/**
     * Handle auth header
     * @return HTTP 200 if success
     */
    @ApiOperation(value = "Post authentication api")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody ResponseEntity<?> postAuthenticate() {
    	logger.debug("POST /authenticate");
    	
    	return ResponseEntity.ok().build();
    }

}