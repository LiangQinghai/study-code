package cn.liangqinghai.study.kotlin.springboot.repos

import cn.liangqinghai.study.kotlin.springboot.model.People
import org.springframework.data.repository.CrudRepository

interface PeopleRepository : CrudRepository<People, Long> {

}