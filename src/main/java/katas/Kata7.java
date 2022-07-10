package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
       return movieLists.stream()
                .map(MovieList ::getVideos)
                .flatMap(Collection::stream)
                .map( movie -> {
                    return ImmutableMap.of("id", movie.getId(),
                            "title", movie.getTitle(),
                            "boxart", getMinBox(movie)
                    );
                }).collect(Collectors.toList());


    }

    private static BoxArt getMinBox(Movie movie) {
        return movie.getBoxarts().stream()
                .min(Kata7::filterMin)
                .get();
    }

    private static int filterMin(BoxArt o1, BoxArt o2) {
        return (o1.getHeight() * o1.getWidth()) - (o2.getHeight() * o2.getWidth());
    }
}
