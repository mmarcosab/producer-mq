package com.producer.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

	private int id;
	private String name;
	private int age;

}
