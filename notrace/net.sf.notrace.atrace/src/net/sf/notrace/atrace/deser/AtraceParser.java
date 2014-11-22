package net.sf.notrace.atrace.deser;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;

public class AtraceParser extends ParserMinimalBase {

	private Reader _reader;
	private IOContext _ioContext;
	
	public AtraceParser(Reader r, IOContext ctxt) {
		
		this._reader = r;
		this._ioContext = ctxt;
	}
	
	@Override
	public JsonToken nextToken() throws IOException, JsonParseException {
		return null;
	}

	@Override
	protected void _handleEOF() throws JsonParseException {
		
	}

	@Override
	public String getCurrentName() throws IOException {
		return null;
	}

	@Override
	public void close() throws IOException {
		
	}

	@Override
	public boolean isClosed() {
		return false;
	}

	@Override
	public JsonStreamContext getParsingContext() {
		return null;
	}

	@Override
	public void overrideCurrentName(String name) {
		
	}

	@Override
	public String getText() throws IOException {
		return null;
	}

	@Override
	public char[] getTextCharacters() throws IOException {
		return null;
	}

	@Override
	public boolean hasTextCharacters() {
		return false;
	}

	@Override
	public int getTextLength() throws IOException {
		return 0;
	}

	@Override
	public int getTextOffset() throws IOException {
		return 0;
	}

	@Override
	public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
		return null;
	}

	@Override
	public ObjectCodec getCodec() {
		return null;
	}

	@Override
	public void setCodec(ObjectCodec c) {
		
	}

	@Override
	public Version version() {
		return null;
	}

	@Override
	public JsonLocation getTokenLocation() {
		return null;
	}

	@Override
	public JsonLocation getCurrentLocation() {
		return null;
	}

	@Override
	public Number getNumberValue() throws IOException {
		return null;
	}

	@Override
	public NumberType getNumberType() throws IOException {
		return null;
	}

	@Override
	public int getIntValue() throws IOException {
		return 0;
	}

	@Override
	public long getLongValue() throws IOException {
		return 0;
	}

	@Override
	public BigInteger getBigIntegerValue() throws IOException {
		return null;
	}

	@Override
	public float getFloatValue() throws IOException {
		return 0;
	}

	@Override
	public double getDoubleValue() throws IOException {
		return 0;
	}

	@Override
	public BigDecimal getDecimalValue() throws IOException {
		return null;
	}

	@Override
	public Object getEmbeddedObject() throws IOException {
		return null;
	}

}
