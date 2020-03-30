package com.producer.demo.resource;


import com.producer.demo.jms.Producer;
import com.producer.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import javax.naming.NamingException;

@Controller
public class PersonResource {

	@GetMapping("/")
	public String index() {
		return "index.html";
	}

	@GetMapping("/cadastra-pessoas")
    private String cadastraPessoas(Model model){
		return "cadastra-pessoas.html";
    }
	
	@PostMapping(value="salvar")
	public String save(@RequestParam("name") String name, @RequestParam("age") int age, Model model) throws JMSException, NamingException {
		Producer producer = new Producer();
		Person person = new Person(name, age);
		producer.connectToRow(person);
		return "/cadastra-pessoas";
	}

}
