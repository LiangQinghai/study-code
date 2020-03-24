import org.apache.flink.types.Row;

object one {
  def one(): Unit = {

    val row = Row.of("1")

    println(row._2)

  }
}
