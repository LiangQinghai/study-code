package cn.liangqinghai.study.kotlin.springboot.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class People (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val name: String?,
        val age: Int?,
        val password: String?
){
        override fun toString(): String {
                return "People(id=$id, name=$name, age=$age, password=$password)"
        }
}