package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .map(MovieList::getVideos)
                .flatMap(Collection::stream)
                .map( movie->ImmutableMap.of("id",movie.getId(),
                        "title", movie.getTitle(),
                        "time", getTime(movie) ,
                        "url", getMinBox(movie)))
                .collect(Collectors.toList());

    }


    public static Date getTime(Movie movie){
        return  movie.getInterestingMoments()
                .stream()
                .filter(interestingMoments->interestingMoments.getType().equalsIgnoreCase("middle"))
                .findFirst()
                .map(InterestingMoment::getTime)
                .orElseThrow();
    }

    private static BoxArt getMinBox(Movie movie) {
        return movie.getBoxarts()
                .stream()
                .min(Kata9::filterMin)
                .orElseThrow();
    }

    private static int filterMin(BoxArt o1, BoxArt o2) {
        return (o1.getHeight() * o1.getWidth()) - (o2.getHeight() * o2.getWidth());
    }
}
