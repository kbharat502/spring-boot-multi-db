package example.test.springbootmultidb.contacts.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import example.test.springbootmultidb.contacts.model.enums.PhoneDTOType;

import java.io.IOException;

public class PhoneDTOTypeDeserializer extends StdDeserializer<PhoneDTOType> {

    public PhoneDTOTypeDeserializer() {
        super(PhoneDTOType.class);
    }
    @Override
    public PhoneDTOType deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String phoneType = node.asText();

        for(PhoneDTOType type : PhoneDTOType.values()) {
            if(type.getType().equalsIgnoreCase(phoneType)) {
                return type;
            }
        }
        return null;
    }
}
