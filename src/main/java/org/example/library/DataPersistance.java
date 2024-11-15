package org.example.library;


import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DataPersistance {
    private static final String FILE_PATH = "library_data.json";

    public static void saveLibrary(List<Book> books) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(FILE_PATH), books);
    }

    public static List<Book> loadLibrary() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return List.of(mapper.readValue(file, Book[].class));
        }
        return List.of();
    }
}
