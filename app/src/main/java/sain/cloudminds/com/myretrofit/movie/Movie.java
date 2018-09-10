package sain.cloudminds.com.myretrofit.movie;

/**
 * created by sain on 9/10/18
 */
public class Movie {
    public String title;
    public String original_title;
    public String year;
    public MovieImage images;

    public static class MovieImage{
        public String small;
        public String large;
        public String medium;
    }
}
