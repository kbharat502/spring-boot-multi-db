package example.test.springbootmultidb.contacts.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.springbootmultidb.contacts.model.enums.AddressDTOType;

import java.io.IOException;

public class AddressDTOTypeSerializer extends StdSerializer<AddressDTOType> {

    public AddressDTOTypeSerializer() {
        super(AddressDTOType.class);
    }

    @Override
    public void serialize(AddressDTOType addressDTOType, JsonGenerator generator,
                          SerializerProvider provider) throws IOException {
        //generator.writeStartObject();
        //generator.writeFieldName("type");
        generator.writeString(addressDTOType.getValue());
        //generator.writeEndObject();
    }
}
