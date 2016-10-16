package sample;

import org.junit.Test;
import sample.model.ModelFactory;

import static org.junit.Assert.assertNotNull;

public class SampleModelTest {

	@Test
	public void testCreateUser() {
		assertNotNull(ModelFactory.eINSTANCE.createUser());
	}

}
