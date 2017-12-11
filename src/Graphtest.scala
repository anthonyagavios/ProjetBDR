import org.apache.spark.graphx.Edge
import org.apache.spark.{SparkConf, SparkContext}
import org.jsoup.Jsoup

import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Petersen Graph (10 nodes) test")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    var myVertices = sc.makeRDD(Array(
      (1L, new node(id = 1L), new Solar()), //A
      (2L, new node(id = 2L), new WorgsRider()), //B
      (3L, new node(id = 3L), new WorgsRider()), //C
      (4L, new node(id = 4L)), new WorgsRider(), //D
      (5L, new node(id = 5L)), new WorgsRider(), //E
      (6L, new node(id = 6L)), new WorgsRider(), //F
      (7L, new node(id = 7L), new WorgsRider()), //G
      (8L, new node(id = 8L), new WorgsRider()), //H
      (9L, new node(id = 9L), new WorgsRider()), //I
      (10L, new node(id = 10L), new Warlord()),
      (11L, new node(id = 11L), new BarbareOrc()),
      (12L, new node(id = 12L), new BarbareOrc()),
      (13L, new node(id = 13L), new BarbareOrc()),
      (14L, new node(id = 14L), new BarbareOrc()))) //J

    //15 EDGES
    //Petersen graph
    //Voir image de référence
    var myEdges = sc.makeRDD(Array(
      Edge(1L, 2L, 1), Edge(1L, 3L, 2), Edge(1L, 4L, 3),
      Edge(1L, 5L, 4), Edge(1L, 6L, 5),
      Edge(1L, 7L, 6), Edge(1L, 8L, 7),
      Edge(1L, 9L, 8), Edge(1L, 10L, 9),
      Edge(1L, 11L, 10), Edge(1L, 12L, 11),
      Edge(1L, 13L, 12),
      Edge(1L, 14L, 13),
      Edge(1L, 10L, 14),
      Edge(1L, 10L, 15)
    ))

    println("HEY ON EST LA" + myEdges.count())
    myVertices.collect().foreach(println)
    //for (vertice <- myVertices.collect()) println(vertice)


  }
}


