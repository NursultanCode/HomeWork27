import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JsonReader {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Movie> readMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        String fromFile = "";
        try (
                FileReader fr = new FileReader("src/movies.json");
                Scanner scan = new Scanner(fr)
        ) {
            while (scan.hasNextLine()) {
                fromFile += scan.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(gson.fromJson(fromFile, Movie[].class));
    }
}
