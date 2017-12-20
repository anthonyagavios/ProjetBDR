package Graph

import java.util

import GestionCombat.{PartySolar, PartyWyrm}
import org.apache.spark.graphx.{Edge, EdgeContext, EdgeTriplet, Graph, _}
import Combattants._
import scala.math._
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GraphCombat extends  Serializable {

  //var listCreat : List[(Long, node)] = Nil
  //var listEdge : List[Edge[(Long, Long, Int)]] = Nil
  var listCreat = new ArrayBuffer[(Long, node)]
  var listEdge = new ArrayBuffer[Edge[Int]]


  var id : Long = 0

  def agrrMessage(graph : Graph[node, String]): Unit ={
    return graph.aggregateMessages[String](sendAttCreature,  (a,ba)=>a+" "+ba )
  }

  def sendAttCreature(ctx: EdgeContext[node, String, String]) : Unit = {

    //Do we send to a given vertex. SRC or DST.
    ctx.sendToDst( ctx.srcAttr.combatant.name)
    ctx.sendToSrc( ctx.dstAttr.combatant.name)
  }

  def changeNode(vid : VertexId, sommet : node, listCreatAtt : String, ennemiS: PartySolar, ennemi: PartyWyrm) : node = {
    val values = listCreatAtt.split(" ")
    for (v <- values) {
      sommet.combatant.HP = sommet.combatant.attaqueDistance(ennemi, v, 0)
      sommet.combatant.HP = sommet.combatant.attaqueMelee(ennemi, v, 0)
    }
    return new node(sommet.id, sommet.combatant)
  }

  def updateEdge(g :Graph[node, Int]): Unit ={
    var myEdges = g.edges.collect()
    var myVertices = g.vertices.collect()
    for (edge <- myEdges) {
      var src = edge.srcId.intValue()
      var dst = edge.dstId.intValue()
      var d = dist(myVertices.apply(src)._2.combatant, myVertices.apply(dst)._2.combatant)
      edge.attr = d
    }
  }


  def dist(c1 : Combattant, c2 : Combattant): Int ={
    return sqrt((c1.posX-c2.posX)*(c1.posX-c2.posX)+(c1.posY-c2.posY)*(c1.posY-c2.posY)).toInt
  }

  def changePos(c1 : Combattant, c2 : Combattant, dist : Int): Combattant ={
    println("start")
    println(c1.posX)
    println(c2.posY)
    var angle = tan((-c2.posX.toDouble+c1.posX.toDouble)/(-c2.posY.toDouble+c1.posY.toDouble))
    println("angle : "+angle)
    c1.posX = (c1.posX - (asin(angle)*dist)).toInt
    c1.posY = (c1.posY - (acos(angle)*dist)).toInt
    println("end")
    println("why "+ asin(angle))
    println((acos(angle)*dist).toInt)
    return c1;
  }


//add combattan to the graph
  def addNode(c : Combattant, team : Int): Unit ={
    //(id, new node(id, c, team))::listCreat
    listCreat.append((id, new node(id, c, team)))
    id = id+1
  }


  //creat adge for X teams
  def creatEdge(): Unit ={
    var l = listCreat
    for((a1 : Long ,b1) <- listCreat){
      for((a2 : Long,b2)<-l){
        if(b1.team < b2.team){
          listEdge.append(new Edge(a1,a2,dist(b1.combatant, b2.combatant)))
        }
      }
    }
  }

  def getGraph() : Graph[node, Int]={
    val conf = new SparkConf()
      .setAppName("Petersen Graph (10 nodes) test")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    var vertices  = sc.makeRDD(listCreat)
    var edges = sc.makeRDD(listEdge)
    return Graph(vertices, edges)
  }
}


//test class graph
object testGraph extends App{
  var c  = new GraphCombat
  c.addNode(new Solar, 1)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 2)
  c.addNode(new Planetar, 3)


  println("node")
  var r = new Random()
  for((a,b)<-c.listCreat){
    b.combatant.posX = r.nextInt()
    b.combatant.posY = r.nextInt()
    println(a+" "+b.combatant.posX+" "+b.combatant.posY)
  }
  c.creatEdge()
  println("edge")
  for(e<-c.listEdge){
    println(e.srcId+" "+e.dstId+" "+e.attr)
  }

  var a  = c.getGraph()
  var azert = a.edges.collect().apply(a.vertices.collect().apply(2)._1.toInt).dstId
  a.vertices.collect().apply(2)._2.combatant = c.changePos(a.vertices.collect().apply(2)._2.combatant, a.vertices.collect().apply(2)._2.combatant, 50000000)
  c.updateEdge(a)

  println("edge")
  for(e<-a.edges.collect()){
    println(e.srcId+" "+e.dstId+" "+e.attr)
  }

}