import java.util.ArrayList;

public class Post {

    String title;
    String description;

    ArrayList<User> userLike;
    ArrayList<String> comments;



    Post(String title, String description){
        this.title = title;
        this.description = description;
        this.userLike = new ArrayList<>();
        this.comments = new ArrayList<>();
    }







}
