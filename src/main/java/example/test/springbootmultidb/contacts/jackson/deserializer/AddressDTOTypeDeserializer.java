package example.test.springbootmultidb.contacts.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import example.test.springbootmultidb.contacts.model.enums.AddressDTOType;

import java.io.IOException;

public class AddressDTOTypeDeserializer extends StdDeserializer<AddressDTOType> {

    public AddressDTOTypeDeserializer() {
        super(AddressDTOType.class);
    }

    @Override
    public AddressDTOType deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String addressType = node.asText();

        for(AddressDTOType type : AddressDTOType.values()) {
            if(type.getValue().equalsIgnoreCase(addressType)) {
                return type;
            }
        }

        return null;
    }
}
