package br.com.ifba.infraestructure.mapper;

import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
    }

    public static <Input, Output> Output map(final Input object,
                                             final Class<Output> clazz) {
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        Output c = MODEL_MAPPER.map(object, clazz);
        return c;
    }

    public static <Input, Output> List<Output> mapAll(final List<Input> objects,
                                                     final Class<Output> clazz) {
        return objects.stream()
                .map(object -> map(object, clazz))
                .collect(Collectors.toList());
    }
}


