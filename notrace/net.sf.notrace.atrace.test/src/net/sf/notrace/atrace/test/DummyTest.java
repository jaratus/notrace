/**
 * 
 */
package net.sf.notrace.atrace.test;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;
import net.sf.notrace.atrace.AtraceModule;
import net.sf.notrace.atrace.deser.SystraceOutputParser;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author admin
 *
 */
public class DummyTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link net.sf.notrace.atrace.AtraceModule#getModuleName()}.
	 */
	@Test
	public void testGetModuleName() {
		assertThat(new AtraceModule().getModuleName(), CoreMatchers.containsString("Atrace Module"));
	}

	/**
	 * Test method for {@link net.sf.notrace.atrace.AtraceModule#version()}.
	 */
	@Test
	public void testVersion() {
		assertEquals(new AtraceModule().version().getMajorVersion(), 1);
	}
	
	@Test
	public void test_1() {
		
		File systraceAssets = new File("data/falcon_pro_trace.html");
		
		
	}

}
