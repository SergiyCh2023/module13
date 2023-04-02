import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class HttpUtil {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static final String URI_USERS = "https://jsonplaceholder.typicode.com/users/";
    private static final String URI_USERS_FOR_GET_USERS_BY_NAME = "https://jsonplaceholder.typicode.com/users?username=";
    private static final Gson GSON = new Gson();


    public static User sendGetUserById (int UserId) throws IOException, InterruptedException, URISyntaxException {  //*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_USERS + UserId))
                .GET()
                .build();
        HttpResponse<String> response;
        response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("sendGet statusCode: " + response.statusCode());
        final User user = GSON.fromJson(response.body(), User.class);
        return user;

    }



    public static void  sendGetUserByName (String name) throws IOException, InterruptedException, URISyntaxException { //*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_USERS_FOR_GET_USERS_BY_NAME + name))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>(){}.getType());

        for (User u: users) {
            System.out.println("get information by names" + u);
        }
    }


    public static User userCreate (URI uri, User user) throws IOException, InterruptedException {  //*
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("content-type", "application/json")
                    .build();

        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }


    public static User userUpdate (User user) throws IOException, InterruptedException, URISyntaxException { //*
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_USERS + user.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("content-type", "application/json")
                .build();

        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("updating statusCode: " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }


    public static void delete (int UserId) throws IOException, URISyntaxException, InterruptedException {  //*
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI_USERS + UserId))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("user delete response.statusCode(): " + response.statusCode());
    }

    public static List<User> getListUsers (URI uri) throws IOException, InterruptedException {  //*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response;
        response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("sendGet statusCode: " + response.statusCode());
        final List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>(){}.getType());
        return users;

    }


    public static void getListOpenTasks () throws IOException, InterruptedException, URISyntaxException {  //*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users/1/todos"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<Tasks> tasks = GSON.fromJson(response.body(), new TypeToken<List<Tasks>>(){}.getType());
         for (Tasks t : tasks) {
         if (t.completed==false) System.out.println(t);
        }
   }



    public static List<Comment> getComments () throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/posts/10/comments"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<Comment> comments = GSON.fromJson(response.body(), new TypeToken<List<Comment>>(){}.getType());
        return comments;
    }




    public static Post getInfoLastPost () throws IOException, InterruptedException, URISyntaxException {  //*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users/1/posts"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<Post> posts = GSON.fromJson(response.body(), new TypeToken<List<Post>>(){}.getType());
        Post max = new Post();
        max.setId(0);
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() > max.getId())  max=posts.get(i);
        }
        return max;
    }



    public static void createFileAndPrint() throws IOException, URISyntaxException, InterruptedException {  //*
        Post currentPost = HttpUtil.getInfoLastPost();
        String filename = String.format("user-%d-post-%d", currentPost.getUserId(), currentPost.getId());
        String x = filename + ".json";
        File file = new File("src\\"+x);

        try (FileWriter writer = new FileWriter(file)){
             List<Comment> commentList = HttpUtil.getComments();
             Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = "";
                for (int i = 0; i < commentList.size(); i++) {
                    if (i==0)   json +="[ " + gson.toJson(commentList.get(i).getName()) + " ," + "\n";
                    else if (i==commentList.size()-1) json += gson.toJson(commentList.get(i).getName()) + " ]";
                    else  json += gson.toJson(commentList.get(i).getName()) + " ," + "\n";

                }
                writer.write(json);
                writer.flush();
                for (Comment c : commentList ) {
                    System.out.println(c.getName());
                }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
