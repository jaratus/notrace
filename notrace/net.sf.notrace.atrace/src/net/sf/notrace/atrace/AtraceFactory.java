package net.sf.notrace.atrace;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.ObjectCodec;

public class AtraceFactory extends JsonFactory {

	private static final long serialVersionUID = 1L;

	public AtraceFactory() {
		super();
	}

	public AtraceFactory(JsonFactory src, ObjectCodec codec) {
		super(src, codec);
	}

	public AtraceFactory(ObjectCodec oc) {
		super(oc);
	}

}
