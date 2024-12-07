package klu.service;

import klu.service.Comment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    private final RestTemplate restTemplate;

    private static final String HIVE_URL = "jdbc:hive2://<HIVE_SERVER>:10000/default";
    private static final String HIVE_USER = "your_user";
    private static final String HIVE_PASSWORD = "your_password";

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch comments from API
    public List<Comment> getAllComments() {
        String apiUrl = "https://jsonplaceholder.typicode.com/comments";
        Comment[] comments = restTemplate.getForObject(apiUrl, Comment[].class);
        return Arrays.asList(comments);
    }

    // Save comments to Hive
    public void saveCommentsToHive(List<Comment> comments) {
        try (Connection connection = DriverManager.getConnection(HIVE_URL, HIVE_USER, HIVE_PASSWORD)) {
            String insertQuery = "INSERT INTO comments (id, name, email, body) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                for (Comment comment : comments) {
                    preparedStatement.setInt(1, comment.getId());
                    preparedStatement.setString(2, comment.getName());
                    preparedStatement.setString(3, comment.getEmail());
                    preparedStatement.setString(4, comment.getBody());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while saving comments to Hive", e);
        }
    }

    // Fetch comments from Hive
    public List<Comment> getCommentsFromHive() {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(HIVE_URL, HIVE_USER, HIVE_PASSWORD)) {
            String query = "SELECT id, name, email, body FROM comments";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setName(resultSet.getString("name"));
                    comment.setEmail(resultSet.getString("email"));
                    comment.setBody(resultSet.getString("body"));
                    comments.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching comments from Hive", e);
        }
        return comments;
    }
}
