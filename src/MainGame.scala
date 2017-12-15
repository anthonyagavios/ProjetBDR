import Combattants._
import GestionCombat.{PartySolar, PartyWyrm}
import Graph.{Terrain, Vertice, node}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.Edge
import org.graphstream.graph.{Edge, Node}
import org.graphstream.graph.implementations.SingleGraph

object MainGame extends App {
  System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer")
  val graph = new SingleGraph("Tutorial 1")


  val styleSheet =
    """
  graph {
    canvas-color: white;
    fill-mode: gradient-radial;
    fill-color: white, #EEEEEE;
    padding: 60px;
  }

	node {
  shape: circle;
 			size: 25px;
      fill-mode: plain;
      fill-color: #555;
      stroke-mode: plain;
      stroke-color: black;
 			stroke-width: 2px;
      text-alignment:under;
      text-color:white;
      text-background-color:black;
      text-background-mode:rounded-box;
      text-style:bold;
      text-padding:1px,1px;
      text-offset:0px,5px;
 			}
  edge {
    size: 3px;
    fill-color: black;
    fill-mode: plain;
    arrow-shape: arrow;
    arrow-size: 10px, 3px;
  }


"""
  val gentil : PartySolar = new PartySolar(1,0,0,0)
  val mechant : PartyWyrm = new PartyWyrm(0,4,0,9,1)

  var distanceOrc = 120
  var distanceWorgs = 110
  var distanceWarlord = 130

  var sizeOrc = mechant.barbareOrc.size
  var sizeWorgs = mechant.worgsRider.size
  var sizeWarlord = mechant.warlord.size

  var fin = false
  var tour = 0

  var numeroOrc = 0
  var numeroWorg = 0
  var numeroWarlord = 0

  /*var terrain = new Terrain
  terrain.constructionTerrain(gentil, mechant, distanceWorgs, distanceOrc, distanceWarlord, 0, 0)*/


  // TODO Manque une verification, isEmpty, sur les Arraybuffer
  while (!fin) {

    println("tour :" + tour)

    // Attaque a distance tant que les orc ne sont pas au corp a corp pour le solar
    // Les orc ne font que des attaques corp a corp donc il avance tant qu'ils ne peuvent pas attaquer
    // Attaque toujours la meme cible tant que celle ci est vivante
    // Attaque l'ennemie le plus proche

    var cibleSolar = ""
    if (distanceOrc < distanceWarlord && distanceOrc < distanceWorgs && !mechant.barbareOrc.isEmpty || (mechant.warlord.isEmpty && mechant.worgsRider.isEmpty)) {
      cibleSolar = mechant.barbareOrc(0).name
    }
    else if (distanceWarlord < distanceWorgs && !mechant.warlord.isEmpty || (mechant.barbareOrc.isEmpty && mechant.worgsRider.isEmpty)) {
      cibleSolar = mechant.warlord(0).name
    }
    else if (!mechant.worgsRider.isEmpty || (mechant.barbareOrc.isEmpty && mechant.warlord.isEmpty)) {
      cibleSolar = mechant.worgsRider(0).name
    }

    // Attaque a distance
    if ((distanceOrc > 7 && distanceOrc < 110) || (distanceWorgs > 7 && distanceWorgs < 110) || (distanceWarlord > 7 && distanceWarlord < 110)) {
      if (cibleSolar == mechant.barbareOrc(0).name) {
        cibleSolar = mechant.barbareOrc(0).name
        gentil.solar(0).attaqueDistance(mechant, mechant.barbareOrc(0).name, 0)

      }

      else if (cibleSolar == mechant.worgsRider(0).name) {
        cibleSolar = mechant.worgsRider(0).name
        gentil.solar(0).attaqueDistance(mechant, mechant.worgsRider(0).name, 0)

      }

      else if (cibleSolar == mechant.warlord(0).name) {
        cibleSolar = mechant.warlord(0).name
        gentil.solar(0).attaqueDistance(mechant, mechant.warlord(0).name, 0)


      }
    }
    // Attaque au corp a corp
    else if (distanceOrc < 7 || distanceWarlord < 7 || distanceWorgs < 7) {
      if (!mechant.barbareOrc.isEmpty && cibleSolar == mechant.barbareOrc(0).name) {

        cibleSolar = mechant.barbareOrc(0).name
        gentil.solar(0).attaqueMelee(mechant, mechant.barbareOrc(0).name, 0)


      } else if (!mechant.warlord.isEmpty && cibleSolar == mechant.warlord(0).name) {

        cibleSolar = mechant.warlord(0).name
        gentil.solar(0).attaqueMelee(mechant, mechant.warlord(0).name, 0)


      } else if (!mechant.worgsRider.isEmpty && cibleSolar == mechant.worgsRider(0).name) {

        cibleSolar = mechant.worgsRider(0).name
        gentil.solar(0).attaqueMelee(mechant, mechant.worgsRider(0).name, 0)


      }
      for (warlord <- mechant.warlord) {
        warlord.attaqueMelee(gentil, gentil.solar(0).name, 0)
      }
      for (worgs <- mechant.worgsRider) {
        worgs.attaqueMelee(gentil, gentil.solar(0).name, 0)
      }
      for (orc <- mechant.barbareOrc) {
        orc.attaqueMelee(gentil, gentil.solar(0).name, 0)
      }

    }

    // Fuite
    if (mechant.warlord.isEmpty && mechant.worgsRider.isEmpty && mechant.barbareOrc.size == 1) {
      println("Le dernier Orc a fui la bataille")
      println("Victoire pour le Solar")
      fin = true
    }

    // Deplacement
    if (!mechant.barbareOrc.isEmpty) {
      if (distanceOrc - mechant.barbareOrc(0).vitesse > 7 && distanceOrc > 0) {
        distanceOrc = distanceOrc - mechant.barbareOrc(0).vitesse
      } else if (distanceOrc - mechant.barbareOrc(0).vitesse < 0 || distanceOrc - mechant.barbareOrc(0).vitesse < 7) {
        distanceOrc = 2
      }
    }
    if (!mechant.worgsRider.isEmpty) {
      if (distanceWorgs - mechant.worgsRider(0).vitesse > 7 && distanceWorgs > 0) {
        distanceWorgs = distanceWorgs - mechant.worgsRider(0).vitesse
      } else if (distanceWorgs - mechant.worgsRider(0).vitesse < 0 || distanceWorgs - mechant.worgsRider(0).vitesse < 7) {
        distanceWorgs = 3
      }
    }
    if (!mechant.warlord.isEmpty) {
      if (distanceWarlord - mechant.warlord(0).vitesse > 7 && distanceWarlord > 0) {
        distanceWarlord = distanceWarlord - mechant.warlord(0).vitesse
      } else if (distanceWarlord - mechant.warlord(0).vitesse < 0 || distanceWarlord - mechant.warlord(0).vitesse < 7) {
        distanceWarlord = 4
      }
    }


    // Fin de tour

    // Test de victoire
    if (mechant.warlord.isEmpty && mechant.worgsRider.isEmpty && mechant.barbareOrc.isEmpty) {
      println("Victoire du Solar")
      fin = true
    } else if (gentil.solar.isEmpty) {
      println("Defaite du Solar")
      fin = true
    }

    // Mise a jour du nombre de noeuds
    if (sizeOrc - mechant.barbareOrc.size != 0) {
      for (i <- 0 to (sizeOrc - mechant.barbareOrc.size - 1)) {
        //terrain.graph.removeNode("BarbaresOrc" + numeroOrc)
        sizeOrc = mechant.barbareOrc.size
        numeroOrc += 1
      }
    }

    if (sizeWarlord - mechant.warlord.size != 0) {
      for (i <- 0 to (sizeWarlord - mechant.warlord.size - 1)) {
       // terrain.graph.removeNode("Warlord" + numeroWarlord)
        sizeWarlord = mechant.warlord.size
        numeroWarlord += 1
      }
    }

    if (sizeWorgs - mechant.worgsRider.size != 0) {
      for (i <- 0 to (sizeWorgs - mechant.worgsRider.size - 1)) {
      //  terrain.graph.removeNode("Worgs" + numeroWorg)
        sizeWorgs = mechant.worgsRider.size
        numeroWorg += 1
      }
    }

    gentil.solar(0).regeneration()


    //terrain.updateDistance(distanceWorgs, distanceOrc, distanceWarlord, 0, 0)
    Thread.sleep(1000)
    println("Il reste " + mechant.barbareOrc.size + " barbareOrc")
    println("Il reste " + mechant.worgsRider.size + " worgsRider")
    println("Il reste " + mechant.warlord.size + " warlord")
    tour += 1

    graph.display
    graph.addAttribute("ui.stylesheet", styleSheet)
    graph.addAttribute("ui.antialias")
    graph.addAttribute("ui.quality")
    val conf = new SparkConf()
      .setAppName("Petersen Graph (10 nodes) test")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")



    var numbercombattants : Int = gentil.solar.length+gentil.astralDeva.length+gentil.movanicDeva.length + gentil.planetar.length
    numbercombattants+= mechant.angelSlayer.length+mechant.barbareOrc.length+mechant.greenGreatWyrmDragon.length+mechant.warlord.length+mechant.worgsRider.length
    var vertices = new Array[Vertice](numbercombattants)

    var i : Int = 0
    for(creature<-gentil.solar){
      vertices(i)= Vertice(i, new node(id = i, new Solar()))
      i+=1
    }
    for(creature<-gentil.planetar){
      vertices(i)= Vertice(i, new node(id = i, new Planetar()))
      i+=1
    }
    for(creature<-gentil.movanicDeva){
      vertices(i)= Vertice(i, new node(id = i, new MovanicDeva()))
      i+=1
    }
    for(creature<-gentil.astralDeva){
      vertices(i)= Vertice(i, new node(id = i, new AstralDeva()))
      i+=1
    }
    for(creature<-mechant.greenGreatWyrmDragon){
      vertices(i)= Vertice(i, new node(id = i, new GreenGreatWyrmDragon()))
      i+=1
    }
    for(creature<-mechant.worgsRider){
      vertices(i)= Vertice(i, new node(id = i, new WorgsRider()))
      i+=1
    }
    for(creature<-mechant.barbareOrc){
      vertices(i)= Vertice(i, new node(id = i, new BarbareOrc()))
      i+=1
    }
    for(creature<-mechant.warlord){
      vertices(i)= Vertice(i, new node(id = i, new Warlord()))
      i+=1
    }
    for(creature<-mechant.angelSlayer){
      vertices(i)= Vertice(i, new node(id = i, new AngelSlayer()))
      i+=1
    }

    var myVertices = sc.makeRDD(vertices)

    var nodes = new Array[Node](myVertices.collect().length)
    var n : Int = 0
    for(vertice<-myVertices){

      val x = scala.util.Random
      val y = scala.util.Random
      val nodex : Node = graph.addNode(vertice.node.combatant.name + n.toString)
      nodex.setAttribute("xy", Array[Double](x.nextInt(20), y.nextInt(20)))
      nodex.setAttribute("ui.label", vertice.node.combatant.name)

      nodes(n) = nodex
      n+=1
    }

  }



}
