package com.example.demo;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.User;
@RestController
@RequestMapping(path="/demo") 
public class UserController {
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
	 , @RequestParam String email) {
	// @ResponseBody means the returned String is the response, not a view name
	// @RequestParam means it is a parameter from the GET or POST request
	
	User n = new User();
	n.setName(name);
	n.setEmail(email);
	userRepository.save(n);
	return "Saved";
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id,@RequestParam String name
			 , @RequestParam String email) {
	    try {
	    	User existUser = userRepository.findById(id).get();
	    	existUser.setName(name);
	    	existUser.setEmail(email);
	        userRepository.save(existUser);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	// This returns a JSON or XML with the users
	return userRepository.findAll();
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
}
