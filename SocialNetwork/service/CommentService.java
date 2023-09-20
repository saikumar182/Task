package com.example.SocialNetwork.service;
import com.example.SocialNetwork.dto.CommentDto;
import com.example.SocialNetwork.entity.*;
import com.example.SocialNetwork.exception.CommentNotFoundException;
import com.example.SocialNetwork.exception.UserNotFoundException;
import com.example.SocialNetwork.repository.CommentReplayRepository;
import com.example.SocialNetwork.repository.CommentRepository;
import com.example.SocialNetwork.repository.PostRepository;
import com.example.SocialNetwork.repository.UserRepository;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository) {
    }


   @Autowired
   private CommentService commentService;

    @Autowired
    private CommentReplayRepository commentReplayRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

  private  static Logger log = Logger.getLogger(CommentService.class);

 public  static  void main(String[] args) throws Exception {


//     Layout layout = new SimpleLayout();

     Layout layout1 = new PatternLayout("%d %c %M %m %n");


//     Appender app = new ConsoleAppender(layout1);

      Appender app = new FileAppender(layout1,"/home/developer/Documents/SocialNetwork//logging.log");
     log.addAppender(app);


     log.info("This is info");
     log.debug("this  one is debug");
     log.warn("this is warning");
     log.fatal("this is fatal");
     log.error("this is error");





 }




    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public CommentReplay saveReply(Long commentId, CommentDto commentDto) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            throw new CommentNotFoundException("Comment with ID " + commentId + " not found.");
        }

        CommentReplay commentReplay = new CommentReplay();
        commentReplay.setContent(commentDto.getContent());
        commentReplay.setComment(comment);

        return commentReplayRepository.save(commentReplay);
    }


    public void replyToComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        CommentReplay commentReplay = new CommentReplay();
        commentReplay.setContent(commentDto.getContent());
        commentReplay.setComment(comment);
        commentReplay.setUser(comment.getUser());

        comment.getContent().equals(commentReplay);

        commentRepository.save(comment);
    }

}



