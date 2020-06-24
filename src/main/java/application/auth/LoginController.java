package application.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Login API")
@RequestMapping("/")
public class LoginController {
    
    private static Logger logger =  LoggerFactory.getLogger(LoginController.class);
    
    @ApiOperation(value = "login api")
    @GetMapping("/login")
    public String login() {
    	logger.debug("/login");
    	return "login";
    }
    
}
