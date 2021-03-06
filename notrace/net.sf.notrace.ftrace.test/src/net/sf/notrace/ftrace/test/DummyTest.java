package net.sf.notrace.ftrace.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.notrace.ftrace.deser.FtraceDeserializer;
import net.sf.notrace.ftrace.event.impl.FtraceEvent;
import net.sf.notrace.ftrace.module.FtraceMapper;
import net.sf.notrace.ftrace.module.FtraceModule;
import net.sf.notrace.ftrace.service.IFtraceService;
import net.sf.notrace.ftrace.service.impl.FtraceService;

import org.eclipse.linuxtools.tmf.core.event.ITmfEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.google.common.collect.BiMap;
import com.google.common.collect.TreeBasedTable;

public class DummyTest {

	private static final File _file = new File("data/android_systrace.txt");
	
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
	public void test() throws IOException {
		
		IFtraceService service = new FtraceService();
		
		Assert.assertNotNull(service);
		
		service.addTrace(_file.toURI());
		
		TreeBasedTable<Integer, Long, Long> pageTable = service.getPageTable(_file.toURI());
		
		int pages = pageTable.size();
		
		BiMap<Long, Integer> rankTable = service.getRankTable(_file.toURI());
		
		Iterator<Entry<Long, Integer>> it = rankTable.entrySet().iterator();
		
		while(it.hasNext()){
			
			Entry<Long, Integer> entry = it.next();
			
			String entryData = entry.getKey() +" "+ entry.getValue();
			
			System.out.println("IFtraceService entryData = "+ entryData);
		}
	}
	
	@Test
	public <T> void test_1() throws JsonProcessingException, IOException {
		
		if(!_file.exists()) throw new IOException("File not exist");
		
		FtraceDeserializer deserializer = new FtraceDeserializer();
		
		FtraceModule module = new FtraceModule();
		
		module.addDeserializer(ITmfEvent.class, deserializer);
		
		FtraceMapper mapper = new FtraceMapper();
		
		mapper.registerModule(module);
		
		MappingIterator<T> it = mapper.reader(ITmfEvent.class).readValues(_file);
		
		while (it.hasNext()) {
			
			@SuppressWarnings("unused")
			T row = it.nextValue();

			if(row instanceof ITmfEvent){
				
				ITmfEvent ev = (ITmfEvent) row;
				
				String source = ev.getSource();
				String ref = ev.getReference();
				long rank = ev.getRank();
				
				String event = rank +" : "+ source  +" : "+ ref;
				
				//System.out.println("event : "+ event);
			}
		}
	}

	@Test
	public <T> void test_2() throws JsonProcessingException, IOException {
		
		for(int i=0;i<10;i++){
			
			test_1();
			
			System.out.println("test run : "+ i);
		
		}
	}
}
