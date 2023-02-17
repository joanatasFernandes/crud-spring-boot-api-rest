package br.com.springboot.curso.springbootRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso.springbootRest.model.Usuario;
import br.com.springboot.curso.springbootRest.repository.UsuarioRepo;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@RequestMapping(value = "/sistema/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Bem vindo ao sistema " + name + "!";
    }
    
    
    @RequestMapping(value = "/codeTech/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String helloSistema(@PathVariable String name) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setName(name);    	
    	
    	usuarioRepo.save(usuario);    	
    	return "Bem vindo a CodeTech " + name + "!";    	
    }
    @GetMapping("listAll")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listUsers() {
		 List<Usuario> userList= usuarioRepo.findAll();
		 
		 return  new ResponseEntity<>(userList, HttpStatus.OK);
	}
    
    @PostMapping(value = "saveUser")
    @ResponseBody    
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
    	
    	Usuario userAccount = usuarioRepo.save(user);
    	
    	return new ResponseEntity<Usuario>(userAccount, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "deleteUser")
    @ResponseBody    
    public ResponseEntity<String> deleteUser(@RequestParam Long userId){
    	
    	 usuarioRepo.deleteById(userId);
    	
    	return new ResponseEntity<String>("User delete success", HttpStatus.OK);
    }
    
    @GetMapping(value = "listUserId")
    @ResponseBody    
    public ResponseEntity<Usuario> listUserId(@RequestParam (name =  "userId") Long userId){
    	
    	Usuario userAccount = usuarioRepo.findById(userId).get();
    	
    	return new ResponseEntity<Usuario>(userAccount, HttpStatus.OK);
    }
    
    @PutMapping(value = "updateUser")
    @ResponseBody    
    public ResponseEntity<?> upDateUser(@RequestBody Usuario user){ 	

   
    	Usuario userAccount = usuarioRepo.saveAndFlush(user);
    	
    	return new ResponseEntity<Usuario>(userAccount, HttpStatus.OK);    
    }
    
    @GetMapping(value = "findByName")
    @ResponseBody    
    public ResponseEntity<List<Usuario>> listUserId(@RequestParam (name =  "userName") String userName){
    	
    	List<Usuario> userAccounts = usuarioRepo.findByName(userName);
    	
    	return new ResponseEntity<List<Usuario>>(userAccounts, HttpStatus.OK);
    }
}
