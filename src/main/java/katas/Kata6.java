package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .map(Movie::getBoxarts)
                .flatMap(Collection::stream)
                .reduce(((boxArt, boxArt2) -> {
                   return boxArt.getWidth()> boxArt2.getWidth() && boxArt.getHeight()> boxArt2.getHeight()? boxArt : boxArt2;
                }))
                .orElseThrow()
                .getUrl();

    }
}
