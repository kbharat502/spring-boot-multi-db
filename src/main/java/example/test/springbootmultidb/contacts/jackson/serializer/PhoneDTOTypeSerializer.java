package example.test.springbootmultidb.contacts.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import example.test.springbootmultidb.contacts.model.enums.PhoneDTOType;

import java.io.IOException;

public class PhoneDTOTypeSerializer extends StdSerializer<PhoneDTOType> {

    public PhoneDTOTypeSerializer() {
        super(PhoneDTOType.class);
    }
    @Override
    public void serialize(PhoneDTOType phoneDTOType, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(phoneDTOType.getType());
    }
}
