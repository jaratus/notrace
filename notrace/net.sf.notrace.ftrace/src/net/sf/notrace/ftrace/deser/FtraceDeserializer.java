package net.sf.notrace.ftrace.deser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.linuxtools.tmf.core.event.ITmfEvent;
import org.eclipse.linuxtools.tmf.core.event.ITmfEventField;
import org.eclipse.linuxtools.tmf.core.event.ITmfEventType;
import org.eclipse.linuxtools.tmf.core.timestamp.ITmfTimestamp;
import org.eclipse.linuxtools.tmf.core.trace.ITmfTrace;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class FtraceDeserializer extends StdDeserializer<ITmfEvent> {

	private static final long serialVersionUID = 1L;

	private Map<String, Class<? extends ITmfEvent>> registry = new HashMap<String, Class<? extends ITmfEvent>>();  
	
	public FtraceDeserializer() {
		super(ITmfEvent.class);
	}

	@Override
	public ITmfEvent deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		//ObjectNode root = (ObjectNode) mapper.readTree(jp);  
		
		return new ITmfEvent(){

			@Override
			public Object getAdapter(Class adapter) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ITmfTrace getTrace() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getRank() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ITmfTimestamp getTimestamp() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getSource() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ITmfEventType getType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ITmfEventField getContent() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getReference() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		
		//return mapper.reader().readValue(jp);
		
	}

}
