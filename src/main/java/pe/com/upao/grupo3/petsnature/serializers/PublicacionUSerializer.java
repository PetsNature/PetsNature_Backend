package pe.com.upao.grupo3.petsnature.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PublicacionUSerializer extends JsonSerializer<PublicacionDTO> {
    @Override
    public void serialize(PublicacionDTO publicacionDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("publicacionDto", publicacionDTO.getPublicacion());
            jsonGenerator.writeObjectField("UsuarioProjection", publicacionDTO.getUsuarioProjection());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
