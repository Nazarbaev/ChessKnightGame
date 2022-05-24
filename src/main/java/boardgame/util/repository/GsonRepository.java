package boardgame.util.repository;

import boardgame.util.gson.LocalDateAdapter;
import boardgame.util.gson.LocalDateTimeAdapter;
import boardgame.util.gson.ZonedDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class GsonRepository<T> extends Repository<T> {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .registerTypeAdapter(LocalDateTime.class,new LocalDateTimeAdapter())
            .create();

    public GsonRepository(Class<T> elementType) {
        super(elementType);
    }

    public void loadFromFile(File file) throws IOException {
        try (var reader = new FileReader(file)) {
            var listType = TypeToken.getParameterized(List.class, elementType).getType();
            elements = GSON.fromJson(reader, listType);
        }
    }

    public void saveToFile(File file) throws IOException {
        try (var writer = new FileWriter(file)) {
            GSON.toJson(elements, writer);
        }
    }

}
