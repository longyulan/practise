package com.practise.service.task;

import com.practise.InsertTestDataMapper;
import com.practise.model.task.InsertTestDataModel;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 写入测试数据
 *
 * @author longyulan
 * @date 2023/6/19
 */
@Service
public class InsertTestDataServiceImpl implements Runnable {

    @Resource
    private InsertTestDataMapper insertTestDataMapper;

    private InsertTestDataModel data;

    @SneakyThrows
    public void insert(InsertTestDataModel data) {
        Thread.sleep(1000L);
        insertTestDataMapper.insert(data);
    }

    @SneakyThrows
    @Override
    public void run() {
        insert(data);
    }

    public InsertTestDataModel getData() {
        return data;
    }

    public void setData(InsertTestDataModel data) {
        this.data = data;
    }
}
