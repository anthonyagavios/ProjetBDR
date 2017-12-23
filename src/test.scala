import scala.collection.mutable.ArrayBuffer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.{ PairRDDFunctions, RDD }


object test extends App{
  // Programme sur lequel on a tenter de mettre les clusters
  val conf = new SparkConf().setAppName("blbl").setMaster("local[*]")
  val sc = new SparkContext(conf)
  class creature(val name : String) extends Serializable {
    var spells =  ArrayBuffer[String]()
    def addspell(spell : String) : Unit = {
      spells += spell
    }
  }

  var solar = new creature("Combattants.Solar")
  solar.addspell("cure light wounds")
  solar.addspell("heal")
  var test = new creature("test")
  test.addspell("healing touch")
  var test2 = new creature("test2")
  test2.addspell("heal")
  val rdd = sc.parallelize(Seq(solar,test,test2))
  val blbl = rdd.map(creature =>  creature.spells.toList -> creature.name)
  val y = blbl.flatMap{ case (a,b) => a.map(_ -> b)}
  val z = y.reduceByKey((x, y) => x + "; " + y)
    .foreach(println)
}
