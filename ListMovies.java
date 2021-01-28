import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListMovies {
    private static List<Movie> movies;
    private static List<Movie> sortedMovies;
    public static void main(String[] args) {
        movies = JsonReader.readMovies();
        sortedMovies = movies;
        printMovies(movies);
        askOperation();
    }

    private static void askOperation() {
        try {
            System.out.println("Which operation you want? f-find or s-sort: ");
            Scanner scanner = new Scanner(System.in);
            String sortOrFind = scanner.next();
            switch (sortOrFind){
                case "f":
                    System.out.println("You have selected finding");
                    findOperations();
                    break;
                case "s":
                    System.out.println("You have selected sorting");
                    sortOperation();
                    break;
                default:
                    throw new Exception("You have incorrect input!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            askOperation();
        }

    }

    private static void sortOperation() {
        try {
            System.out.println("Which operation you want?");
            System.out.println("1 - sort by year from New films,2 - sort by year from Old films, 3 - sort by name A-Z, 4 - sort by name Z-A, 5 - sort by director A-Z, 6 - sort by director Z-A\"");
            Scanner scanner = new Scanner(System.in);
            int sortOrFind = scanner.nextInt();
            switch (sortOrFind){
                case 1:
                    sortByYearNew();
                    break;
                case 2:
                    sortByYearOld();
                    break;
                case 3:
                    sortByNameAZ();
                    break;
                case 4:
                    sortByNameZA();
                    break;
                case 5:
                    sortByDirectorAZ();
                    break;
                case 6:
                    sortByDirectorZA();
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception | ExceptionInInitializerError e) {
            System.out.println("Wrong input--");
            sortOperation();
        }
    }

    private static void sortByYearNew() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        printMovies(sortedMovies);
    }

    private static void sortByYearOld() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getYear() - o2.getYear();
            }
        });
        printMovies(sortedMovies);
    }

    private static void sortByNameAZ() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        printMovies(sortedMovies);
    }

    private static void sortByNameZA() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });
        printMovies(sortedMovies);
    }

    private static void sortByDirectorAZ() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getDirector().getFullName().compareTo(o2.getDirector().getFullName());
            }
        });
        printMovies(sortedMovies);
    }

    private static void sortByDirectorZA() {
        Collections.sort(sortedMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getDirector().getFullName().compareTo(o1.getDirector().getFullName());
            }
        });
        printMovies(sortedMovies);
    }

    private static void findOperations() {
        try {
            System.out.println("Which operation you want?");
            System.out.println("1 - find by actor, 2 - find by director, 3 - find by year, 4 - list of all actors with film name and role");
            Scanner scanner = new Scanner(System.in);
            int sortOrFind = scanner.nextInt();
            switch (sortOrFind){
                case 1:
                    System.out.println("Successfully chose find by actor!");
                    findByActors();
                    break;
                case 2:
                    System.out.println("Successfully chose find by Director!");
                    findByDirector();
                    break;
                case 3:
                    System.out.println("Successfully chose find by Year!");
                    findByYear();
                    break;
                case 4:
                    System.out.println("Successfully chose All actors!");
                    listOfActors();
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Wrong input2");
            sortOperation();
        }

    }

    private static void findByDirector() {
        System.out.println("Enter the name of director: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        sortedMovies = new ArrayList<>();
        for (Movie movie:movies
             ) {
            Matcher matcher = pattern.matcher(movie.getDirector().getFullName());
            if (matcher.find()){
                sortedMovies.add(movie);
            }
        }
        if (sortedMovies.size()>0) printMovies(sortedMovies);
        else {
            System.out.println("We can not find "+name);
            findByDirector();
        }
    }

    private static void findByYear() {
        System.out.println("Enter the name of actor: ");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        sortedMovies = new ArrayList<>();
        for (Movie movie:movies
        ) {
            if (movie.getYear() == year){
                sortedMovies.add(movie);
            }
        }
        if (sortedMovies.size()>0) printMovies(sortedMovies);
        else{
            System.out.println("We can not find "+year);
            findByYear();
        }
    }

    private static void findByActors(){
        System.out.println("Enter the name of director: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        sortedMovies = new ArrayList<>();
        for (Movie movie:movies
        ) {
            Actor [] actors = movie.getCast();
            for (Actor a:actors
                 ) {
                Matcher matcher = pattern.matcher(a.getFullName());
                if (matcher.find()){
                    sortedMovies.add(movie);
                    break;
                }
            }
        }
        if (sortedMovies.size()>0) printMovies(sortedMovies);
        else {
            System.out.println("We can not find "+name);
            findByDirector();
        }
    }


    private static void listOfActors() {
        HashSet<Actor> actors = new HashSet<>();
        for (Movie m:movies
             ) {
            Actor[]actors1 = m.getCast();
            for (Actor a:actors1
                 ) {
                actors.add(a);
            }
        }
        TreeSet<Actor> treeSet = new TreeSet<Actor>(actors);
        for (Actor actor:treeSet
             ) {
            sortedMovies = new ArrayList<>();
            System.out.println(actor.getFullName());
            for (Movie m:movies
                 ) {
                Actor[]actors2 = m.getCast();
                for (Actor a:actors2
                     ) {
                    if (a.equals(actor)){
                        System.out.println("-".repeat(10)+m.getName()+" | "+a.getRole());
                    }
                }
            }
        }

    }


    private static void printMovies(List<Movie> movies) {
        String title = String.format(" %-45s | %-5s | %-15s | %-20s | %-20s | %s ", "Name", "Year", "Description", "Director", "Actor", "Role");
        System.out.println(title);
        System.out.println("-".repeat(150));
        for (Movie a:movies
        ) {
            System.out.println(a.toString());
        };
    }

}
