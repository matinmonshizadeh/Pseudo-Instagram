import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        ArrayList<User> userArrayList = new ArrayList<User>();
        ArrayList<Following> followingArrayList = new ArrayList<Following>();
        ArrayList<Post> postArrayList = new ArrayList<Post>();


        // Our Pseudo Instagram name is /MM\
        JOptionPane.showMessageDialog(null, "*WELCOME TO /MM\\ PSEUDO INSTAGRAM*", "Welcome", JOptionPane.PLAIN_MESSAGE);


        boolean inTheMainPage = true;

        while(inTheMainPage) {

            // Things that we can do in main page
            int numberChoosed = Integer.parseInt(JOptionPane.showInputDialog("\nWhat do you want to do? (Choose one of the numbers..)\n1. Sign up\n2. Login\n3. Exit\n\nEnter your number: "));


            switch(numberChoosed) {
                // 1. Sign up -----------------------------------------------------------------------------------------------------
                case 1:

                    while(true) {

                        // User sign up
                        String userName = JOptionPane.showInputDialog("UserName: ");
                        userName = userName.toLowerCase();

                        String pass = JOptionPane.showInputDialog("Password: ");

                        User newUser = new User(userName, pass);

                        boolean haveAlreadyUsed = false;

                        for(User checkingUser : userArrayList)
                            if(checkingUser.userName.equals(userName)) {
                                haveAlreadyUsed = true;
                                break;
                            }

                        if(haveAlreadyUsed)
                            JOptionPane.showMessageDialog(null, "Choose another username!", "Error", JOptionPane.ERROR_MESSAGE);
                        else {
                            userArrayList.add(newUser);
                            JOptionPane.showMessageDialog(null, "Congratulation! :D");

                        }

                        break;
                    }
                    break;
                // 2. Login ------------------------------------------------------------------------------------------------------
                case 2:
                    boolean inTheLoginPage = true;
                    while(inTheLoginPage) {
                        if(userArrayList.isEmpty())
                            JOptionPane.showMessageDialog(null, "Please sign up first", "Warning", JOptionPane.WARNING_MESSAGE);
                        else {

                            String userName = JOptionPane.showInputDialog("UserName: ");
                            userName = userName.toLowerCase();

                            String pass = JOptionPane.showInputDialog("Password: ");

                            boolean haveSignUpFirst = false;


                            for(User checkingUser : userArrayList)
                                if(checkingUser.userName.equals(userName)) {
                                    haveSignUpFirst = true;
                                    if(checkingUser.pass.equals(pass)) {


                                        JOptionPane.showMessageDialog(null, "Login was successful :)\n");

                                        User loginedUser = userFinder(userName, userArrayList);


                                        while(inTheLoginPage) {
                                            // User tasks
                                            int numberChoosed1 = Integer.parseInt(JOptionPane.showInputDialog("\n1. Userpage\n2. HomePage\n3. Suggestions to follow\n4. Following\n5. Followers\n6. Create a post\n7. See your posts\n8. message\n9. join or create group chats\n10. Log out\n\nEnter your number: "));



                                            switch(numberChoosed1) {

                                                // 1. UserPage
                                                case 1:

                                                    int countOfFollowers = 0;
                                                    for(User user: userArrayList) {
                                                        if(user.following.contains(userName)) {
                                                            countOfFollowers++;
                                                        }
                                                    }


                                                    JOptionPane.showMessageDialog(null, "Username: " + userName, "UserPage/Username ", JOptionPane.PLAIN_MESSAGE);
                                                    JOptionPane.showMessageDialog(null, "Following: " + userFinder(userName, userArrayList).following.size(), "UserPage/Following", JOptionPane.PLAIN_MESSAGE);
                                                    JOptionPane.showMessageDialog(null, "Followers: " + countOfFollowers, "UserPage/Followers", JOptionPane.PLAIN_MESSAGE);


                                                    int n = 1;
                                                    for(User user : userArrayList) {
                                                        if(user.userName.equals(userName)) {
                                                            for(int i = 0; i < user.userPosts.size(); i++) {
                                                                JOptionPane.showMessageDialog(null,"(" + n + ")\n" + "Title:\n" + user.userPosts.get(i).title +"\nDescription:\n" + user.userPosts.get(i).description, "UserPage", JOptionPane.PLAIN_MESSAGE);

                                                                n++;
                                                            }
                                                        }
                                                        break;
                                                    }

                                                    continue;

                                                    // 2. HomePage
                                                case 2:

                                                    int like = 0;

                                                    Object[] options = {"Like",
                                                            "Comment",
                                                            "Continue"};

                                                    for(User user: userArrayList) {
                                                        int z = 1;
                                                        if(user.userName.equals(userName)) {
                                                            continue;
                                                        }
                                                        else if(userFinder(userName, userArrayList).following.contains(user.userName)) {
                                                            for(int i = 0; i < user.userPosts.size(); i++) {

                                                                Object likeOrComment = JOptionPane.showInputDialog(null,
                                                                        user.userName + "\n" + "(" + z + ")" + "\n" + "Title:\n" + user.userPosts.get(i).title +"\nDescription:\n" + user.userPosts.get(i).description,
                                                                        "UserPage",
                                                                        JOptionPane.YES_NO_CANCEL_OPTION,
                                                                        null,
                                                                        options, options[1]);

                                                                // Like field
                                                                if(likeOrComment.equals("Like")) {

                                                                    loginedUser.userLike(user.userPosts.get(i));
                                                                    JOptionPane.showMessageDialog(null, "Like: " + user.userPosts.get(i).userLike.size());

                                                                    // Comment field
                                                                }else if(likeOrComment.equals("Comment")) {
                                                                    loginedUser.userComments(user.userPosts.get(i));


                                                                    for(Post post : user.userPosts) {
                                                                        for(String comment : post.comments) {
                                                                            JOptionPane.showMessageDialog(null, "Comments:\n" + comment);

                                                                        }


                                                                    }

                                                                }


                                                                z++;
                                                            }
                                                        }


                                                    }


                                                    continue;

                                                    // 3. Suggestions to follow
                                                case 3:
                                                    String temp = "";
                                                    for(User user : userArrayList) {

                                                        if(user.userName.equals(userName)) {

                                                        }else {
                                                            temp += user.userName + "\n";
                                                        }
                                                    }

                                                    String userNameChoosed = JOptionPane.showInputDialog(temp + "\nPlease enter username that you want to follow:");

                                                    if(userFinder(userName, userArrayList).following.contains(userNameChoosed)) {
                                                        JOptionPane.showMessageDialog(null, "You have been followed this user!", "Error", JOptionPane.ERROR_MESSAGE);
                                                        continue;
                                                    }else if(userNameChoosed.equals(userName)) {
                                                        JOptionPane.showMessageDialog(null, "You can't follow yourself!", "Error", JOptionPane.ERROR_MESSAGE);
                                                        continue;
                                                    }

                                                    boolean flag = true;
                                                    for(User user: userArrayList) {
                                                        if(userNameChoosed.equals(user.userName)) {
                                                            flag = true;
                                                            break;

                                                        }else {
                                                            flag = false;
                                                        }
                                                    }

                                                    if(flag == false) {
                                                        JOptionPane.showMessageDialog(null, "Please enter correct username!", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else {

                                                        Following newFollowing = new Following(userNameChoosed);
                                                        followingArrayList.add(newFollowing);


                                                        JOptionPane.showMessageDialog(null, "you are successfully following " + userNameChoosed + " :)");

                                                        userFinder(userName, userArrayList).following.add(newFollowing.userName);
                                                    }
                                                    continue;
                                                    // 4. Following
                                                case 4:
                                                    String temp1 = "";
                                                    for(int i = 0; i < userFinder(userName, userArrayList).following.size(); i++) {
                                                        temp1 += userFinder(userName, userArrayList).following.get(i) + "\n";
                                                    }

                                                    JOptionPane.showMessageDialog(null, "Following:\n" + temp1);

                                                    continue;
                                                    //5. Followers
                                                case 5:
                                                    String temp2 = "";
                                                    for(User user: userArrayList) {
                                                        if(user.following.contains(userName)) {
                                                            temp2 += user.userName + "\n";
                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(null, "Followers:\n" + temp2, "Followers", JOptionPane.PLAIN_MESSAGE);



                                                    continue;
                                                    // 6. Create a post
                                                case 6:

                                                    String title = JOptionPane.showInputDialog("Title: ");

                                                    String description = JOptionPane.showInputDialog("Description: ");

                                                    Post newPost = new Post( title, description);
                                                    postArrayList.add(newPost);

                                                    JOptionPane.showMessageDialog(null, "Your post successfully added :)");

                                                    userFinder(userName, userArrayList).userPosts.add(newPost);


                                                    continue;

                                                    // 7. See Your posts
                                                case 7:
                                                    int m = 1;
                                                    for(User user : userArrayList) {
                                                        if(user.userName.equals(userName)) {
                                                            for(int i = 0; i < user.userPosts.size(); i++) {
                                                                JOptionPane.showMessageDialog(null,"(" + m + ")\n" + "Title:\n" + user.userPosts.get(i).title +"\nDescription:\n" + user.userPosts.get(i).description);
                                                                m++;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    continue;

                                                    // 8. message
                                                case 8:
                                                    String temp3 = "";
                                                    for(User user : userArrayList) {
                                                        if(user.userName.equals(userName)) {
                                                        }else {
                                                            temp3 += user.userName + "\n";
                                                        }
                                                    }


                                                    String userChoosed = JOptionPane.showInputDialog(temp3 + "Enter user that you want message: ");
                                                    if(userChoosed.equals(userName)) {
                                                        JOptionPane.showMessageDialog(null, "You can't message to yourself!", "Error", JOptionPane.ERROR_MESSAGE);

                                                    }else if(userFinder(userChoosed, userArrayList).equals(null)) {
                                                        JOptionPane.showMessageDialog(null, "Wrong Input!", "Error", JOptionPane.ERROR_MESSAGE);

                                                    }else {
                                                        JOptionPane.showMessageDialog(null, "messages:");
                                                        for(DirectMessage directMessage : loginedUser.directMessages) {
                                                            JOptionPane.showMessageDialog(null, directMessage.meassage);
                                                        }

                                                        loginedUser.sendMessage(userFinder(userChoosed, userArrayList));
                                                    }


                                                    continue;

                                                    // 9. join or create group chats
                                                case 9:
                                                    continue;

                                                    // 10. Log out
                                                case 10:
                                                    System.out.println();
                                                    inTheLoginPage = false;
                                                    break;

                                                default:
                                                    JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }

                                    }else {
                                        JOptionPane.showMessageDialog(null, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
                            if(!haveSignUpFirst)
                                JOptionPane.showMessageDialog(null, "Wrong username!", "Error", JOptionPane.ERROR_MESSAGE);

                        }

                        break;
                    }
                    break;
                // 3. Exit -------------------------------------------------------------------------------------------------------
                case 3:
                    JOptionPane.showMessageDialog(null, "Have a great day :)");
                    inTheMainPage = false;
                    break;
                // Wrong input ---------------------------------------------------------------------------------------------------
                default:
                    JOptionPane.showMessageDialog(null, "Wrong input!", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }



    // This method find user
    public static User userFinder(String userName, ArrayList<User> userArrayList) {
        for(User user : userArrayList)
            if(userName.equals(user.userName))
                return user;

        return null;
    }






}





















