import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class User {

    String userName;
    String pass;
    ArrayList<Post> userPosts;
    ArrayList<String> following;
    ArrayList<DirectMessage> directMessages;


    User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
        this.userPosts = new ArrayList<>();
        this.following = new ArrayList<>();
        this.directMessages = new ArrayList<>();
    }



    public String showUserName() {
        return userName;

    }


    public void userLike(Post post) {
        if(!post.userLike.contains(this)) {
            post.userLike.add(this);
        }

    }

    public void userComments(Post post) {
        post.comments.add(JOptionPane.showInputDialog("Enter your comment:"));
    }


    public void sendMessage(User destinationUser) {
        DirectMessage directMessage = new DirectMessage(JOptionPane.showInputDialog("Enter your message: "), this);

        destinationUser.directMessages.add( directMessage);
        directMessages.add(directMessage);

    }


}
