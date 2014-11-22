package net.sf.notrace.atrace;

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

import net.sf.notrace.atrace.deser.AtraceParser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;

public class AtraceFactory extends JsonFactory {

	private static final long serialVersionUID = 1L;

	public static final String FORMAT_NAME_ATRACE = "ATRACE";
	
	/*
    /**********************************************************************
    /* Factory construction, configuration
    /**********************************************************************
     */
	public AtraceFactory() {
		super();
	}

	public AtraceFactory(JsonFactory src, ObjectCodec codec) {
		super(src, codec);
	}

	public AtraceFactory(ObjectCodec oc) {
		super(oc);
	}

	@Override
	public JsonFactory copy() {
		_checkInvalidCopy(AtraceFactory.class);
        return new AtraceFactory(this, null);
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
		return new AtraceFactory(this, _objectCodec);
	}
	
	/*
    /******************************************************
    /* Overridden internal factory methods
    /******************************************************
     */
	@Override
	protected AtraceParser _createParser(InputStream in, IOContext ctxt)
			throws IOException {
		
		InputStreamReader inr = new InputStreamReader(in);
		
		return new AtraceParser(inr, ctxt);
	}

	@Override
	protected AtraceParser _createParser(Reader r, IOContext ctxt)
			throws IOException {
		return new AtraceParser(r, ctxt);
	}

	@Override
	protected AtraceParser _createParser(char[] data, int offset, int len,
			IOContext ctxt, boolean recyclable) throws IOException {
		
		CharArrayReader car = new CharArrayReader(data, offset, len);
		
		return new AtraceParser(car, ctxt);
	}

	@Override
	protected AtraceParser _createParser(byte[] data, int offset, int len,
			IOContext ctxt) throws IOException {
		
		ByteArrayInputStream bai = new ByteArrayInputStream(data, offset, (offset + len));
		
		InputStreamReader inr = new InputStreamReader(bai);
		
		return new AtraceParser(inr, ctxt);
	}

	 /*
    /**********************************************************
    /* Overridden parser factory methods, 2.1
    /**********************************************************
     */
	@Override
	public AtraceParser createParser(File f) throws IOException,
			JsonParseException {
		return _createParser(new FileInputStream(f), _createContext(f, true));
	}

	@Override
	public AtraceParser createParser(URL url) throws IOException,
			JsonParseException {
		return _createParser(_optimizedStreamFromURL(url), _createContext(url, true));
	}

	@Override
	public AtraceParser createParser(InputStream in) throws IOException,
			JsonParseException {
		return _createParser(in, _createContext(in, false));
	}

	@Override
	public AtraceParser createParser(Reader r) throws IOException,
			JsonParseException {
		return _createParser(r, _createContext(r, false));
	}

	@Override
	public AtraceParser createParser(byte[] data) throws IOException,
			JsonParseException {
		return _createParser(data, 0, data.length, _createContext(data, true));
	}

	@Override
	public AtraceParser createParser(byte[] data, int offset, int len)
			throws IOException, JsonParseException {
		return _createParser(data, offset, len, _createContext(data, true));
	}

	@Override
	public AtraceParser createParser(String content) throws IOException,
			JsonParseException {
		return _createParser(new StringReader(content), _createContext(content, true));
	}

	@Override
	public AtraceParser createParser(char[] content) throws IOException {
		return _createParser(content, 0, content.length, _createContext(content, true), false);
	}

	@Override
	public AtraceParser createParser(char[] content, int offset, int len)
			throws IOException {
		return _createParser(content, offset, len, _createContext(content, true), false);
	}

}
