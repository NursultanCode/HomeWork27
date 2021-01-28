import java.util.Arrays;

public class Movie {
    private String name;
    private int year;
    private String description;
    private Director director;
    private Actor [] cast;

    public Actor[] getCast() {
        return cast;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String castToString(){
        StringBuilder r= new StringBuilder();
        for (int i = 0; i <cast.length ; i++) {
            r.append(cast[i].toString());
            if (i == cast.length-1) {
                r.append("\n").append("-".repeat(150));
            }else {
                r.append(String.format("\n %-45s | %-5s | %-15s | %-20s | ", "", "", "", ""));
            }
        }
        return r.toString();
    }

    @Override
    public String toString() {
        return  String.format(" %-45s | %-5d | %-15s | %-20s | %s ", name, year, description, director, castToString());
    }
}
