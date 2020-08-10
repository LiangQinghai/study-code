package cn.liangqinghai.study.kotlin.springboot.service

import cn.liangqinghai.study.kotlin.springboot.model.People
import cn.liangqinghai.study.kotlin.springboot.repos.PeopleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PeopleService {

    @Autowired
    val peopleRepository :PeopleRepository? = null

    fun getAllPeople(): MutableIterable<People>? {

        return peopleRepository?.findAll();

    }

}