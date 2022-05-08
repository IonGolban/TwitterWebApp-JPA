package Project.TwitterWebApp.service;

import Project.TwitterWebApp.model.dto.ReplyCreateDto;


public interface ReplyService {


    void replyPost(ReplyCreateDto replyCreateDto, Integer postId);

}
