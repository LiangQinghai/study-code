package cn.liangqinghai.study.mybatis.service.impl;

import cn.liangqinghai.study.mybatis.entity.Project;
import cn.liangqinghai.study.mybatis.mapper.ProjectMapper;
import cn.liangqinghai.study.mybatis.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
