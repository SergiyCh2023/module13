import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Main {

    private static final String CREATE_USER_URI = "https://jsonplaceholder.typicode.com/users/";
    private static final String GET_USER_BY_ID = "https://jsonplaceholder.typicode.com/users/";
    private static final String GET_USER_COMMENTS = "https://jsonplaceholder.typicode.com/users/1/posts/";

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        User user = createDefaultUser();

        final User createdUser = HttpUtil.userCreate(URI.create(CREATE_USER_URI), user);
        System.out.println("createdUser: " + createdUser);


        final User updateUser= HttpUtil.userUpdate(user);
        System.out.println("updateUser: " + updateUser);


        HttpUtil.delete(createdUser.getId());


        final List<User> users = HttpUtil.getListUsers(URI.create(GET_USER_BY_ID));
        for (User u : users) {
            System.out.println("info about all users: " + u);
        }


        final User someUser = HttpUtil.sendGetUserById(3);
        System.out.println("get information by Id: " + someUser);


        HttpUtil.sendGetUserByName("Bret");


        HttpUtil.createFileAndPrint();


        HttpUtil.getListOpenTasks();


    }

    private static User createDefaultUser() {
        User user = new User();
        user.setId(1);
        user.setName("Papa");
        user.setUsername("Carlo");
        user.setEmail("P.Carlo@gmail.com");
        return user;
    }

}
