package sample;

import sample.model.ModelFactory;
import sample.model.User;

public class Main {

	public static void main(String... args) {
		User user = ModelFactory.eINSTANCE.createUser();
		user.setName("Paul Doe");

		System.out.println(user.getName());
	}

}
