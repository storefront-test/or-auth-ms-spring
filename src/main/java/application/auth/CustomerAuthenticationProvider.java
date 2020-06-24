package application.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import application.customer.Customer;

@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider {

    private static Logger logger =  LoggerFactory.getLogger(CustomerAuthenticationProvider.class);
    
    @Value( "${customerService.url}" )
	private String custResourceUrl;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
		
		if (name.equals("user") && password.equals("password")) {
			// TEST
			return new UsernamePasswordAuthenticationToken(name, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		}
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(custResourceUrl+"?username={name}", Customer[].class, name);

		Customer[] customer_array = responseEntity.getBody();
        
		final List<Customer> custList= new ArrayList<Customer>();
		Collections.addAll(custList, customer_array);
		
		logger.debug("customer service returned:" + custList);
    	
    	if (custList == null || custList.isEmpty()) {
    		throw new AuthenticationException("Invalid username or password") {
				private static final long serialVersionUID = 1L;
			};
    	}
    	
    	final Customer cust = custList.get(0);
  
    	if (!cust.getPassword().equals(password)) {
    		throw new AuthenticationException("Invalid username or password") {
				private static final long serialVersionUID = 1L;
			};
    	}
    	
    	// authentication was valid
		return new UsernamePasswordAuthenticationToken(cust.getCustomerId(), password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
	}

}