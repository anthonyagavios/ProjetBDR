package Graph


import Combattants.{BarbareOrc, Solar, Warlord, WorgsRider}
import io.netty.buffer.ByteBuf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext
import org.apache.spark.graphx.{Edge, EdgeContext, EdgeTriplet, Graph, _}
import org.apache.spark.network.buffer.ManagedBuffer
import org.apache.spark.network.protocol.Message



object Graphtest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Petersen Graph (10 nodes) test")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    var myVertices = sc.makeRDD(Array(
      Vertice(1L, new node(id = 1L, new Solar())), //A
      Vertice(2L, new node(id = 2L, new WorgsRider())), //B
      Vertice(3L, new node(id = 3L, new WorgsRider())), //C
      Vertice(4L, new node(id = 4L, new WorgsRider())), //D
      Vertice(5L, new node(id = 5L, new WorgsRider())), //E
      Vertice(6L, new node(id = 6L, new WorgsRider())), //F
      Vertice(7L, new node(id = 7L, new WorgsRider())), //G
      Vertice(8L, new node(id = 8L, new WorgsRider())), //H
      Vertice(9L, new node(id = 9L, new WorgsRider())), //I
      Vertice(10L, new node(id = 10L, new Warlord())),
      Vertice(11L, new node(id = 11L, new BarbareOrc())),
      Vertice(12L, new node(id = 12L, new BarbareOrc())),
      Vertice(13L, new node(id = 13L, new BarbareOrc())),
      Vertice(14L, new node(id = 14L, new BarbareOrc())))) //J

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
      Edge(1L, 15L, 14)
    ))

    println("HEY ON EST LA" + myEdges.count())
    for (edge <- myEdges) println(edge.attr)

    //println(myVertices.collect().length)
    for (vertice <- myVertices) println(vertice.node.combatant.name)

    /*var myGraph = Graph(myVertices, myEdges)

    val vertice_and_messages = myGraph.aggregateMessages[VertexId](
      sendAttCreature() //use an optimized join strategy (we don't need the edge attribute)
    )*/
  }
/*
  def sendAttCreature(ctx: EdgeContext[node, String, String]) : Unit = {

    //Do we send to a given vertex. SRC or DST.
      ctx.sendToDst( ctx.srcAttr.combatant.name)
      ctx.sendToSrc( ctx.dstAttr.combatant.name)
  }
*/
}
/*
class Graph[RDD[Vertice], ED] {
  def aggregateMessages[Msg: ClassTag](
                                        sendMsg: EdgeContext[VD, ED, Msg] => Unit,
                                        mergeMsg: (Msg, Msg) => Msg,
                                        tripletFields: TripletFields = TripletFields.All): VertexRDD[Msg]
}

object Graphs extends Serializable{


}
*/





