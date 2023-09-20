package com.example.SocialNetwork.service;

import com.example.SocialNetwork.dto.ReplyDto;
import com.example.SocialNetwork.entity.CommentReplay;
import com.example.SocialNetwork.exception.CommentNotFoundException;
import com.example.SocialNetwork.exception.UserNotFoundException;
import com.example.SocialNetwork.repository.CommentReplayRepository;
import com.example.SocialNetwork.repository.CommentRepository;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentReplayService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

@Autowired
private CommentReplayRepository commentReplayRepository;

    public CommentReplay getReplyById(Long id) {
        return commentReplayRepository .findById(id).orElse(null);
    }

    public CommentReplay saveReply(CommentReplay commentReplay) {
        return commentReplayRepository.save(commentReplay);
    }


        public CommentReplay replyonComment(ReplyDto reply)  throws CommentNotFoundException, UserNotFoundException {
            if(userRepository.findById(reply.getUser_id()).isPresent())
            {
                if (commentRepository.findById(reply.getComment_id()).isPresent())
                {
                    CommentReplay replyoncomment=new CommentReplay();
                    replyoncomment.setReply(reply.getReply());
                    replyoncomment.setComment_id(reply.getComment_id());
                    replyoncomment.setUser_id(reply.getUser_id());
                   replyoncomment .save(replyoncomment);
                    return replyoncomment;

                }
                throw new CommentNotFoundException("Invalid comment id");
            }
            throw new UserNotFoundException ("Invalid user id" );

        }


}
