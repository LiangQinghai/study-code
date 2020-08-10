package cn.liangqinghai.study.kotlin.springboot.controller

import cn.liangqinghai.study.kotlin.springboot.model.People
import cn.liangqinghai.study.kotlin.springboot.service.PeopleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {

    @Autowired
    val peopleService : PeopleService? = null

    @GetMapping(value = ["/", "index"])
    fun index(): Any {
        return "Hello kotlin"
    }

    @GetMapping("/people")
    fun people(): MutableIterable<People>? {

        return peopleService?.getAllPeople()

    }

}