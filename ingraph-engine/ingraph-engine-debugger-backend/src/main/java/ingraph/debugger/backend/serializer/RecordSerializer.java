package ingraph.debugger.backend.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.neo4j.driver.v1.Record;

import java.io.IOException;

public class RecordSerializer extends JsonSerializer<Record> {

	@Override
	public void serialize(Record value, JsonGenerator gen, SerializerProvider serializers)
		throws IOException, JsonProcessingException {
		gen.writeStartObject();
		for (String key : value.keys()) {
			gen.writeObjectField(key, value.get(key).asObject());
		}
		gen.writeEndObject();

	}

}
