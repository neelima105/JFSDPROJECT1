package klu.controller;

import klu.controller.model.Comment;
import klu.controller.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class FacultyController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> fetchAllComments() {
        return commentService.getAllComments();
    }
}