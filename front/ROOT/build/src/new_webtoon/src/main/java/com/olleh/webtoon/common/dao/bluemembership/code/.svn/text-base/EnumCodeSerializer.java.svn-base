package com.olleh.webtoon.common.dao.bluemembership.code;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class EnumCodeSerializer extends JsonSerializer<CodeBase>
{

	@Override
	public void serialize( CodeBase code, JsonGenerator generator, SerializerProvider provider ) throws IOException, JsonProcessingException
	{
		generator.writeStartObject();
		generator.writeFieldName( "code" );
		generator.writeString( code.getCode() );
		generator.writeFieldName( "text" );
		generator.writeString( code.getText() );
		generator.writeEndObject();
	}

}
