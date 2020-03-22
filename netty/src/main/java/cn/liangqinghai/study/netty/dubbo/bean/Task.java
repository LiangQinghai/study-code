package cn.liangqinghai.study.netty.dubbo.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Mr.Liang
 * @date 2020/3/22
 */
@Data
@Accessors(chain = true)
@Builder
public class Task {

    private String id;

    private String name;

    private String cron;

    private Status status;

    private Date createDate;

    private Date updateDate;


    public enum Status {

        /**
         *
         */
        ENABLE,

        /**
         *
         */
        DISABLE,

        /**
         *
         */
        DELETE

    }

}
