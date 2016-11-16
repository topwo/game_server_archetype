package com.kidbear.logical.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务管理
 */
@Service
public class TaskService {
    @Autowired
    private TaskExecutor executor;
    /**
     * The Logger.
     */
    public Logger logger = LoggerFactory.getLogger(TaskService.class);

    /**
     * 测试任务
     */
    @Scheduled(cron = "0 0/3 * * * ? ")
    public void testTask() {
        executor.execute(new Runnable() {
            public void run() {
                logger.info("定时任务");
            }
        });
    }
}
