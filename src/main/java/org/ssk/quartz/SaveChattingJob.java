package org.ssk.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.ssk.dto.ChatDto;
import org.ssk.repository.ChattingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-24
 * description  :
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveChattingJob extends QuartzJobBean {

    private final ChattingRepository chattingRepository;

    private final RedisTemplate<String , ChatDto> redisTemplate;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ListOperations<String, ChatDto> operations = redisTemplate.opsForList();

        Set<String> keys = redisTemplate.keys("*");
        List<ChatDto> list = new ArrayList<>();
        for(String key : keys){
            log.info("key : "+key);
            List<ChatDto> chattingList = operations.range(key, 0, -1);
            list.addAll(chattingList);
        }

        log.info(list.toString());
        chattingRepository.saveAllChatting(list);
        redisTemplate.delete(keys);
    }

}
