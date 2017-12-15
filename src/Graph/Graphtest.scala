package Graph


import Combattants._
import breeze.numerics._
import io.netty.buffer.ByteBuf
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext
import org.apache.spark.graphx.{Edge, EdgeContext, EdgeTriplet, Graph, _}
import org.apache.spark.network.buffer.ManagedBuffer
import org.apache.spark.network.protocol.Message

import scala.reflect.ClassTag



object MainGraph {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("Petersen Graph (10 nodes) test")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

   var myVertices = sc.makeRDD(Array(
      (1L, new node(id = 1L, new Solar())), //A
      (2L, new node(id = 2L, new WorgsRider())), //B
      (3L, new node(id = 3L, new WorgsRider())), //C
      (4L, new node(id = 4L, new WorgsRider())), //D
      (5L, new node(id = 5L, new WorgsRider())), //E
      (6L, new node(id = 6L, new WorgsRider())), //F
      (7L, new node(id = 7L, new WorgsRider())), //G
      (8L, new node(id = 8L, new WorgsRider())), //H
      (9L, new node(id = 9L, new WorgsRider())), //I
      (10L, new node(id = 10L, new Warlord())),
      (11L, new node(id = 11L, new BarbareOrc())),
      (12L, new node(id = 12L, new BarbareOrc())),
      (13L, new node(id = 13L, new BarbareOrc())),
      (14L, new node(id = 14L, new BarbareOrc())))) //J
/*
    var myVertices = sc.makeRDD(Array(
      (1L, new node(id = 1L)), //A
      (2L, new node(id = 2)), //B
      (3L, new node(id = 3)), //C
      (4L, new node(id = 4)), //D
      (5L, new node(id = 5)), //E
      (6L, new node(id = 6)), //F
      (7L, new node(id = 7)), //G
      (8L, new node(id = 8)), //H
      (9L, new node(id = 9)), //I
      (10L, new node(id = 10)))) //J
*/
    //15 EDGES
    //Petersen graph
    //Voir image de référence
    var myEdges = sc.makeRDD(Array(
      Edge(1L, 2L, dist(new Solar, new BarbareOrc)), Edge(1L, 3L, dist(new Solar, new BarbareOrc)), Edge(1L, 4L, dist(new Solar, new BarbareOrc)),
      Edge(1L, 5L, dist(new Solar, new BarbareOrc)), Edge(1L, 6L, dist(new Solar, new BarbareOrc)),
      Edge(1L, 7L, 6), Edge(1L, 8L, 7),
      Edge(1L, 9L, 8), Edge(1L, 10L, 9),
      Edge(1L, 11L, 10), Edge(1L, 12L, 11),
      Edge(1L, 13L, 12),
      Edge(1L, 14L, 13),
      Edge(1L, 15L, 14)
    ))

    var myGraph = Graph(myVertices, myEdges)

    println("HEY ON EST LA" + myEdges.count())
    for (edge <- myEdges) println(edge.attr)

    println(myVertices.collect().length)
    for ((a,b) <- myVertices) println(a+" "+b.combatant.name)

    def sendAttCreature(ctx: EdgeContext[node, Int, String]) : Unit = {

      //Do we send to a given vertex. SRC or DST.
      ctx.sendToDst( ctx.srcAttr.combatant.name)
      ctx.sendToSrc( ctx.dstAttr.combatant.name)
    }

    def changeNode(vid : VertexId, sommet : node, bestId : String) : node = {

      return new node(sommet.id, sommet.combatant)
    }

      val vertice_and_messages = myGraph.aggregateMessages[String](
       sendAttCreature,
        (a,ba)=>a+ba //use an optimized join strategy (we don't need the edge attribute)
      )

      //Join les resultats des messages avec choisirCouleur
      myGraph = myGraph.joinVertices(vertice_and_messages)((vid, sommet, bestId) => changeNode(vid, sommet, bestId))



    println(vertice_and_messages)
    println(myGraph.vertices.id+ " azertyuiuytrds")
    //for ((a,b) <- myVertices) println(a+" "+b.combatant.name)
   println("          "+myVertices.collect().apply(2)._2)

    for (edge <- myEdges) {
      var src = edge.srcId.intValue()
      var dst = edge.dstId.intValue()
      var v = myVertices.collect()
      var d = dist(v.apply(src)._2.combatant, v.apply(3)._2.combatant)
    }

  }



  def updateEdge(g :Graph[node, String]): Unit ={
    var myEdges = g.edges
    var myVertices = g.vertices
    for (edge <- myEdges) {
      var src = edge.srcId.intValue()
      var dst = edge.dstId.intValue()
      var v = myVertices.collect()
      var d = dist(v.apply(src)._2.combatant, v.apply(3)._2.combatant)
    }
  }


  def dist(c1 : Combattant, c2 : Combattant): Int ={
    return sqrt((c1.posX-c2.posX)*(c1.posX-c2.posX)+(c1.posY-c2.posY)*(c1.posY-c2.posY)).toInt
  }

  def changePos(c1 : Combattant, c2 : Combattant, dist : Int): Combattant ={
    var angle = tan((c2.posX-c1.posX)/(c2.posY-c1.posY))
    c1.posX = c1.posX - (asin(angle)*dist).toInt
    c1.posY = c1.posY - (acos(angle)*dist).toInt
    return c1;}



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







