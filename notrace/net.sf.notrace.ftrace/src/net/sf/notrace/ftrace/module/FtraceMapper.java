package net.sf.notrace.ftrace.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

public class FtraceMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public FtraceMapper() {
		this(new FtraceFactory());
	}

	public FtraceMapper(FtraceFactory jf) {
		super(jf);
	}

	public FtraceMapper(FtraceFactory jf, DefaultSerializerProvider sp,
			DefaultDeserializationContext dc) {
		super(jf, sp, dc);
	}

	public FtraceMapper(ObjectMapper src) {
		super(src);
	}

}
