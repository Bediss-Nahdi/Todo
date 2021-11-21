package fr.bediss.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.bediss.entities.Todo;
import fr.bediss.repository.TodoRepository;

@RestController
@RequestMapping("/todo") 
public class TodoController {

	
	
	@Autowired
	private TodoRepository tr;
	
	
	@GetMapping("/findall")
	public List<Todo> fetchAll(){
		return this.tr.findAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addTodo(@RequestBody Todo todo){
		ResponseEntity<?> response = new ResponseEntity(tr.save(todo), HttpStatus.CREATED); 
		return response;
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") Integer id ){
		
		if (this.tr.findById(id).isPresent()) {
			this.tr.deleteById(id);
			ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
			
			return response;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable ("id") Integer id, @RequestBody Todo todo){
		if (this.tr.findById(id).isPresent()) {
			todo.setId(id);
			ResponseEntity<?> response =  new ResponseEntity<>(this.tr.save(todo), HttpStatus.OK);
			return response;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
