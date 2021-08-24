package com.producer.demo.resource;


import com.producer.demo.jms.Producer;
import com.producer.demo.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/demomq")
public class PersonResource {

	private final Producer producer;

	@GetMapping("/teste")
	public String save() {
		try {
			producer.send(Person.builder()
					.age(21)
					.name("Teste")
					.id(1)
					.build());
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		return "vai lá";
	}

}
