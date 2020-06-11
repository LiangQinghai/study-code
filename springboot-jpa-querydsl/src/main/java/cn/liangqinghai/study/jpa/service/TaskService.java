package cn.liangqinghai.study.jpa.service;

import cn.liangqinghai.study.jpa.model.TaskPo;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title TaskService
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 11:45
 */
public interface TaskService {

    List<TaskPo> list();

}
