package net.sf.notrace.ftrace.module;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import net.sf.notrace.ftrace.deser.FtraceParser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;

public class FtraceFactory extends JsonFactory {

	private static final long serialVersionUID = 1L;

	public static final String FORMAT_NAME_ATRACE = "ATRACE";
	
	/*
    /**********************************************************************
    /* Factory construction, configuration
    /**********************************************************************
     */
	public FtraceFactory() {
		super();
	}

	public FtraceFactory(JsonFactory src, ObjectCodec codec) {
		super(src, codec);
	}

	public FtraceFactory(ObjectCodec oc) {
		super(oc);
	}

	@Override
	public JsonFactory copy() {
		_checkInvalidCopy(FtraceFactory.class);
        return new FtraceFactory(this, null);
	}
	/*
    /**********************************************************
    /* Format detection functionality
    /**********************************************************
     */
	@Override
    public String getFormatName() {
        return FORMAT_NAME_ATRACE;
    }
	
	/*
    /**********************************************************
    /* Serializable overrides
    /**********************************************************
     */
    /**
     * Method that we need to override to actually make restoration go
     * through constructors etc.
     * Also: must be overridden by sub-classes as well.
     */
	@Override
	protected Object readResolve() {
		return new FtraceFactory(this, _objectCodec);
	}
	
	/*
    /******************************************************
    /* Overridden internal factory methods
    /******************************************************
     */
	@Override
	protected FtraceParser _createParser(InputStream in, IOContext ctxt)
			throws IOException {
		
		InputStreamReader inr = new InputStreamReader(in);
		
		return new FtraceParser(inr, ctxt);
	}

	@Override
	protected FtraceParser _createParser(Reader r, IOContext ctxt)
			throws IOException {
		return new FtraceParser(r, ctxt);
	}

	@Override
	protected FtraceParser _createParser(char[] data, int offset, int len,
			IOContext ctxt, boolean recyclable) throws IOException {
		
		CharArrayReader car = new CharArrayReader(data, offset, len);
		
		return new FtraceParser(car, ctxt);
	}

	@Override
	protected FtraceParser _createParser(byte[] data, int offset, int len,
			IOContext ctxt) throws IOException {
		
		ByteArrayInputStream bai = new ByteArrayInputStream(data, offset, (offset + len));
		
		InputStreamReader inr = new InputStreamReader(bai);
		
		return new FtraceParser(inr, ctxt);
	}

	 /*
    /**********************************************************
    /* Overridden parser factory methods, 2.1
    /**********************************************************
     */
	@Override
	public FtraceParser createParser(File f) throws IOException,
			JsonParseException {
		return _createParser(new FileInputStream(f), _createContext(f, true));
	}

	@Override
	public FtraceParser createParser(URL url) throws IOException,
			JsonParseException {
		return _createParser(_optimizedStreamFromURL(url), _createContext(url, true));
	}

	@Override
	public FtraceParser createParser(InputStream in) throws IOException,
			JsonParseException {
		return _createParser(in, _createContext(in, false));
	}

	@Override
	public FtraceParser createParser(Reader r) throws IOException,
			JsonParseException {
		return _createParser(r, _createContext(r, false));
	}

	@Override
	public FtraceParser createParser(byte[] data) throws IOException,
			JsonParseException {
		return _createParser(data, 0, data.length, _createContext(data, true));
	}

	@Override
	public FtraceParser createParser(byte[] data, int offset, int len)
			throws IOException, JsonParseException {
		return _createParser(data, offset, len, _createContext(data, true));
	}

	@Override
	public FtraceParser createParser(String content) throws IOException,
			JsonParseException {
		return _createParser(new StringReader(content), _createContext(content, true));
	}

	@Override
	public FtraceParser createParser(char[] content) throws IOException {
		return _createParser(content, 0, content.length, _createContext(content, true), false);
	}

	@Override
	public FtraceParser createParser(char[] content, int offset, int len)
			throws IOException {
		return _createParser(content, offset, len, _createContext(content, true), false);
	}

}
