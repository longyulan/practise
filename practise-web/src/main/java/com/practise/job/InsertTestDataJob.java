package com.practise.job;

import com.practise.model.task.InsertTestDataModel;
import com.practise.service.task.InsertTestDataServiceImpl;
import com.practise.util.ThreadPoolUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池测试job
 * @author longyulan
 * @date 2023/6/19
 */
@Slf4j
@Component
public class InsertTestDataJob {

    @Resource
    private InsertTestDataServiceImpl insertTestDataService;

    @XxlJob(value = "insertTestDataWithThreadPoolJob")
    public ReturnT<String> insertTestDataWithThreadPool() throws NoSuchAlgorithmException {
        ThreadPoolExecutor threadPool = ThreadPoolUtils.getThreadPool();
        int count = 200000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            InsertTestDataModel data = new InsertTestDataModel();
            data.setName("姓名" + i);
            data.setAge(i);
            data.setCore(SecureRandom.getInstanceStrong().nextFloat());
            insertTestDataService.setData(data);
            threadPool.execute(insertTestDataService);
        }
        log.info("任务insertTestDataWithThreadPoolJob执行结束，耗时:{}", System.currentTimeMillis() - start);
        return ReturnT.SUCCESS;
    }

    @XxlJob(value = "insertTestDataJob")
    public ReturnT<String> insertTestData() throws NoSuchAlgorithmException {
        ThreadPoolExecutor threadPool = ThreadPoolUtils.getThreadPool();
        int count = 200000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            InsertTestDataModel data = new InsertTestDataModel();
            data.setName("姓名" + i);
            data.setAge(i);
            data.setCore(SecureRandom.getInstanceStrong().nextFloat());
            insertTestDataService.insert(data);
        }
        log.info("任务InsertTestDataJob执行结束，耗时:{}", System.currentTimeMillis() - start);
        return ReturnT.SUCCESS;
    }
}
