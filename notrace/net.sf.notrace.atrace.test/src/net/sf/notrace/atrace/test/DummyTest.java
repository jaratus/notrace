/**
 * 
 */
package net.sf.notrace.atrace.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;
import net.sf.notrace.atrace.AtraceMapper;
import net.sf.notrace.atrace.AtraceModule;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;

/**
 * @author admin
 * @param <T>
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
	
	@Ignore
	public <T> void test_1() throws JsonProcessingException, IOException {
		
		File inputFile = new File("data/android_systrace.txt");
		
		AtraceMapper mapper = new AtraceMapper();
		
		MappingIterator<T> it = mapper.reader(Map.class).readValue(inputFile);
		
		while (it.hasNext()) {
			@SuppressWarnings("unused")
			T row = it.nextValue();

		}
	}

}
