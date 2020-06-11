package cn.liangqinghai.study.jpa.model;

import cn.liangqinghai.study.jpa.hibernate.PoListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author LiangQinghai
 * @Title TaskPo
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 10:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "TASK")
public class TaskPo extends BasePo{

    private static final long serialVersionUID = 7893171978342756423L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


}
