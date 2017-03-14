package ingraph.debugger.backend.serializer;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Provider
public class JerseyMapperProvider implements ContextResolver<ObjectMapper> {
	private static ObjectMapper objectMapper;

	public JerseyMapperProvider() {
		objectMapper = new ObjectMapper();
		
		SimpleModule recordSerializerModule = new SimpleModule();
		recordSerializerModule.addSerializer(Record.class, new RecordSerializer());
		objectMapper.registerModule(recordSerializerModule);
		
		SimpleModule valueSerializerModule = new SimpleModule();
		valueSerializerModule.addSerializer(Value.class, new ValueSerializer());
		objectMapper.registerModule(valueSerializerModule);
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return objectMapper;
	}
}