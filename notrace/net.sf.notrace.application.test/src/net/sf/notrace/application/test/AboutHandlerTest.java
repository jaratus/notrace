package net.sf.notrace.application.test;

import static org.junit.Assert.*;
import net.sf.notrace.application.handlers.AboutHandler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.CoreMatchers;

public class AboutHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGreeting() {
		assertThat(new AboutHandler().getGreeting(), CoreMatchers.containsString("NoTrace E4 Application"));
	}

}
