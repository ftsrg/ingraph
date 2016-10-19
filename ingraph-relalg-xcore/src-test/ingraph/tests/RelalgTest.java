package relalg.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import relalg.RelalgFactory;

public class RelalgTest {

	@Test
	public void testCreateUser() {
		assertNotNull(RelalgFactory.eINSTANCE.createRelationalAlgebraContainer());
	}

}
