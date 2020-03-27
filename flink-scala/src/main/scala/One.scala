import java.util.Properties

import cn.hutool.json.{JSONObject, JSONUtil}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.scala.StreamTableEnvironment

object One {

  def main(args: Array[String]): Unit = {

    val env:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val properties = getProperties
    val kafkaConsumer:FlinkKafkaConsumer[String] = new FlinkKafkaConsumer[String](properties.getProperty("topic"), new SimpleStringSchema(), properties)

    val settings: EnvironmentSettings = EnvironmentSettings.newInstance().inStreamingMode().useBlinkPlanner().build()

    val tableEnvironment: StreamTableEnvironment = StreamTableEnvironment.create(env, settings)

  }

  def getProperties: Properties = {
    val inputStream = One.getClass.getClassLoader.getResourceAsStream("kafka.properties")
    val prop = new Properties()
    prop.load(inputStream)
    prop
  }

}