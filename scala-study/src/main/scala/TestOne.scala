import java.io.FileNotFoundException
import scala.io.Source


object TestOne {

  def main(args: Array[String]) {
    var a: Int = 22
    var b: String = "Hello"
    var (c, d) = (1, "Hello")

    println(a, b, c, d)

    val person = new Person(b, a)

    person.introduce()

    val student = new Student("1999", b, a)

    student.introduce()

    var i: Int = 0
    for (i <- 1 to 10) {
      println(i)
    }

    // 不包含10
    for (i <- 1 until 10) {
      println(i)
    }

    var j: Int = 0

    for (i <- 0 to 10; j <- 0 to 10) {
      println(i, j)
    }

    var str = ""
    var strList = List("One", "Two", "Three", "Four", "Five")

    for (str <- strList) {
      println(str)
    }

    println("--------------------------")
    for (str <- strList; if str.contains("o")) {
      println(str)
    }

    println("------------------")
    var tmp = for (str <- strList; if str.contains("i")) yield str
    println(tmp)

    println("---------")
    println(getList(strList))

    println("---------")
    delayFun(time())

    println("---------")
    printStr("Hello", "World")

    println("---------------")
    println(outside(wapper, 100))

    println("---------")
    var fun = (x: Int, y: Int) => {
      x * y
    }

    println(fun(10, 10))

    println("---------")
    println(foo("One")("Two"))

    println("---------")
    var f = 1.75d
    println(f"Hello, Name: $b%s, age: $a, height: $f%2.2f")

    println("---------")
    var arr = new Array[String](10)
    arr(0) = "Hello"
    arr(9) = "World"

    for (elem <- arr) {
      print(elem)
      print("\t")
    }
    println()
    println("---------")

    var nums = 1::(2::(3::Nil))
    println(nums)

    var mun2 = List(4, 5, 6, 7, 8)

    println(nums ::: mun2)

    println(nums.reverse)

    val  p = Set(new Person("One", 23), new Student(stdNoA = "199", nameA = "Two", ageA = 23), new Person("Three", 25))
    println(p)
    println(p.head)
    println(p.tail)
    println(p.isEmpty)

    val numsOne = Set(1, 2, 4, 5, 6)
    val numsTwo = Set(5, 6, 7, 8)
    println(numsOne.intersect(numsTwo))
    println(numsOne.&(numsTwo))


    val myMap = Map("one" -> "Hello", "two" -> "world")
    println(myMap)

    try {
      Source.fromFile("one.properties").foreach {
        print
      }
    } catch {
      case ex: FileNotFoundException => {
        println(ex)
      }
    }

    val myMap2 = scala.collection.mutable.Map()
    myMap2("Hello") = "Hello"

  }

  def getList(strList: List[String]): List[String] = {
    var str = ""
    for (str <- strList; if str.contains("o")) yield str
  }

  def time() = {
    println("Get time")
    System.nanoTime()
  }

  def delayFun(t: => Long): Unit = {
    println("Call")
    println(t)
  }

  def printStr(strList: String*): Unit = {
    for (str <- strList) {
      println(str)
    }
  }

  def outside(fun: Int => String, x: Int): String = {
    fun(x)
  }

  def wapper(x: Int): String = "x : " + x + " ."

  def foo(a : String)(b : String): String = {
    a + b
  }

}

class Person(name: String, age: Int) {

  var nameX: String = name
  var ageX: Int = age

  def introduce(): Unit = {
    println(nameX, ageX)
  }

}

class Student(stdNoA: String, nameA: String, ageA: Int) extends Person(nameA, ageA) {

  var stdNo: String = stdNoA

  override def introduce(): Unit = {
    println(stdNo, nameA, ageA)
  }

}
