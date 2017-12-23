package Graph

import java.util

import GestionCombat.{PartySolar, PartyWyrm}
import org.apache.spark.graphx.{Edge, EdgeContext, EdgeTriplet, Graph, _}
import Combattants.{Solar, Warlord, _}
import org.apache.spark.rdd.RDD

import scala.math._
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GraphCombat extends  Serializable {

  //var listCreat : List[(Long, node)] = Nil
  //var listEdge : List[Edge[(Long, Long, Int)]] = Nil
  var listCreat = new ArrayBuffer[(Long, node)]
  var listEdge = new ArrayBuffer[Edge[(Int, node, node)]]

  var distanceOrc = 120
  var lastDistanceOrc = 120
  var distanceWorgs = 110
  var distanceWarlord = 130

  var id : Long = 0



  def updateEdgeDist(g :Graph[node, (Int,node,node)]): Unit ={
    var myEdges = g.edges.collect()
    var myVertices = g.vertices.collect()
    var src,dst,d : Int = 0
    for (edge <- myEdges) {
      src = edge.srcId.intValue()
      dst = edge.dstId.intValue()
      d = dist(myVertices.apply(src)._2.combatant, myVertices.apply(dst)._2.combatant)
      //edge.attr._1 = d
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


  //creat edge for X teams
  def creatEdge(): Unit ={
    var l = listCreat
    for((a1, b1) <- listCreat){
      for((a2, b2)<-l){
        if(b1.team < b2.team){
          listEdge.append(new Edge(a1,a2,(dist(b1.combatant, b2.combatant),b1, b2)))
        }
      }
    }
  }



  def getGraph(sc : SparkContext) : Graph[node, (Int,node,node)]={
    creatEdge()
    var vertices  = sc.makeRDD(listCreat)
    var edges = sc.makeRDD(listEdge)
    var graphCreature = Graph(vertices, edges)
    return graphCreature
  }

  def joinV(graphCreat : Graph[node, (Int,node,node)], vertice_and_messages : RDD[(VertexId, String)], gentil : PartySolar, mechant : PartyWyrm): Graph[node, (Int,node,node)] ={
    return graphCreat.joinVertices(vertice_and_messages)((vid, sommet, listCreatAtt) => changeNode(vid, sommet, listCreatAtt,  gentil, mechant))
  }

  def agrrMessage(graph : Graph[node, (Int,node,node)]): RDD[(VertexId, String)] ={
    var v = graph.aggregateMessages[String](sendAttCreature,  ((s1,s2)=>s1+" "+s2) )
    v.collect()
    return v
  }

  def sendAttCreature(ctx: EdgeContext[node, (Int,node,node), String]) : Unit = {
    //Do we send to a given vertex. SRC or DST.

    if(ctx.dstAttr.live == true && ctx.srcAttr.live == true) {

      if(ctx.srcAttr.target.id == ctx.dstAttr.id){
        ctx.sendToDst(ctx.srcAttr.combatant.name+"-"+ctx.attr._1)
      }
      if(ctx.dstAttr.target.id == ctx.srcAttr.id){
        ctx.sendToSrc( ctx.dstAttr.combatant.name+"-"+ctx.attr._1)
      }
    }
  }

  def changeNode(vid : VertexId, sommet : node, listCreatAtt : String, ennemiS: PartySolar, ennemi: PartyWyrm) : node = {
    val values = listCreatAtt.split(" ")
    var nsommet = sommet

    for (v <- values) {
      val carac = v.split("-")

      if((carac(0)=="worgsRider" || carac(0)=="barbareOrc" || carac(0)=="warlord" )&& carac(1).toInt<=7){
          //nsommet = sommet.combatant.attaqueMelee(ennemi, carac(0), 0, sommet)

      }

      if(carac(0)=="solar"&& carac(1).toInt<=7){
        println("name attaquant : "+carac(0)+" ---- name attaqué "+nsommet.combatant.name)
        nsommet = nsommet.combatant.attaqueMelee(ennemiS, carac(0), 0, nsommet)
        println(nsommet.combatant.name+" "+nsommet.combatant.HP)

      }
      if((carac(0)=="worgsRider" || carac(0)=="barbareOrc" || carac(0)=="warlord" )&& carac(1).toInt>7){
        //nsommet = sommet.combatant.attaqueDistance(ennemi, carac(0), 0, sommet)

      }
      if(carac(0)=="solar"&& carac(1).toInt>7){
        nsommet = nsommet.combatant.attaqueDistance(ennemiS, carac(0), 0, nsommet)
      }

    }
    return nsommet
  }

  def teamWin(g : Graph[node, (Int,node,node)]): Int ={
    var vertice = g.vertices.collect()
    var team1 = false
    var team2 = false
    for(v<-vertice){
      if(v._2.team == 1 && v._2.live){
        team1 = true
      }
      if(v._2.team == 2 && v._2.live){
        team2 = true
      }
    }

    if(team1 && team2) return -1
    else if (!team1 && !team2) return  3
    else if(team1) return 1
    return 2
  }

  def near(g : Graph[node, (Int,node,node)]): Graph[node, (Int,node,node)] ={
    var edge = g.edges.collect()
    var vertice = g.vertices
    //edge.collect().apply(0).attr._1
    for(n<-vertice){
      var dist = 999999999
      for(e<-edge){
        //println(e.attr._3.live+"  "+e.attr._2.live)
        if((n._2.id == e.dstId || n._2.id == e.srcId)&& e.attr._3.live && e.attr._2.live){
          if(e.attr._1 <= dist){
            dist = e.attr._1
            if(n._2.id == e.srcId){
              println(n._2.combatant.name)
              println("-----------------------------")
              n._2.target = e.attr._3
              n._2.target.combatant = e.attr._3.combatant
              n._2.target.live = e.attr._3.live
              n._2.target.id = e.attr._3.id

            }
            else {
              n._2.target = e.attr._2
              n._2.target.combatant = e.attr._2.combatant
              n._2.target.live = e.attr._2.live
              n._2.target.id = e.attr._2.id
            }
          }
        }
      }

    }
    for(vertice<-g.vertices.collect()){
      //println(vertice._2.combatant.name+" "+vertice._2.target.combatant.name+" "+vertice._2.target.live)
    }
    g.vertices.collect()
    return g
  }

  def updateEdgeNode(g : Graph[node, (Int,node,node)]): Graph[node, (Int,node,node)] ={
    var edge = g.edges
    var vertice = g.vertices.collect()

    for(v<-vertice){
      for(e<-edge){
        if(e.srcId == v._1 ){
          e.attr._2.combatant = v._2.combatant
          e.attr._2.live = v._2.live
          e.srcId = v._1
        }
        if(e.dstId == v._1){
          e.attr._3.combatant = v._2.combatant
          e.attr._3.live = v._2.live
          e.dstId = v._1
        }
      }
    }
    /*for(e<-g.edges.collect()){
      println(e.attr._2.combatant.name+" : "+ e.attr._2.live+" vie target : "+e.attr._2.target.live+" ---- "+e.attr._3.combatant.name+" :"+e.attr._3.live)

      //println(e.attr._2.combatant.name+" : "+ e.attr._2.live+" ---- "+e.attr._3.combatant.name+" :"+e.attr._3.live)
    }*/

    return g
  }

  def newTarget(g : Graph[node, (Int,node,node)]): Graph[node, (Int,node,node)] ={
    var edge = g.edges.collect()
    var vertice = g.vertices
    var dist = 99999999//edge.apply(0).attr._1
    for(n<-vertice){
      println("nam "+ n._2.target.combatant.name+" "+n._2.target.live)
      if(!n._2.target.live){
        for(e<-edge){
          if((n._2.id == e.dstId || n._2.id == e.srcId)&& e.attr._3.live && e.attr._2.live){
            if(e.attr._1 <= dist){
              dist = e.attr._1
              if(n._2.id == e.attr._2.id){

                n._2.target = e.attr._3
              }
              else { n._2.target = e.attr._2}
            }
          }
        }
        println("one dead")
      }
    }

    return g
  }

}

/*
//test class graph
object testGraph extends App{
  var c  = new GraphCombat

  var gentil = new PartySolar(1, 0, 0, 0, c)
  var mechant = new PartyWyrm(0, 4, 0, 9, 1, c)

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

  println(" \n\n\n\n\n\n")
  var counter = 0
  while (true && counter < 2) {
    println("ITERATION NUMERO : " + (counter + 1))
    counter += 1

  }

}*/