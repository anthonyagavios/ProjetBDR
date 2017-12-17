package Graph

import GestionCombat.{PartySolar, PartyWyrm}
import org.graphstream.graph.Node
import org.graphstream.graph.implementations.SingleGraph

import scala.collection.mutable.ArrayBuffer

class Terrain {

  // Construction du graphe
  System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer")
  val graph = new SingleGraph("Premier combat")
  var styleSheet =
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

   node#Pito {
    size: 25px;
    fill-color:#fed301;
  }


  edge {
    size: 3px;
    fill-color: black;
    fill-mode: plain;
    arrow-shape: arrow;
    arrow-size: 10px, 3px;
  }


"""

  // ArrayBuffer contenant les noeuds
  var solars = new ArrayBuffer[Node]
  var planetars = new ArrayBuffer[Node]
  var astralDevas = new ArrayBuffer[Node]
  var movanicDevas = new ArrayBuffer[Node]

  var worgs = new ArrayBuffer[Node]
  var orcs = new ArrayBuffer[Node]
  var angelSlayers = new ArrayBuffer[Node]
  var warlords = new ArrayBuffer[Node]
  var wyrms = new ArrayBuffer[Node]

  val rand = scala.util.Random

  def constructionTerrain(gentil: PartySolar, mechant: PartyWyrm, distanceWorgs: Int, distanceOrc: Int, distanceWarlord: Int, distanceWyrm: Int, distanceAngelSlayer: Int, numeroCombat: Int) {

    // Creation des noeuds contenant les solars
    if (!gentil.solar.isEmpty) {
      for (numero <- 0 to gentil.solar.size - 1) {
        val sol: Node = graph.addNode("Solar" + numero)
        styleSheet +=
          """
          node#Solar""" + numero +
          """{
          size:50px;
          fill-color:#fed301;
          }
        """
        solars.append(sol)
      }
    }

    // Creation des noeuds contenant les planetars
    if (!gentil.planetar.isEmpty) {
      for (numero <- 0 to gentil.planetar.size - 1) {
        val plan: Node = graph.addNode("Planetar" + numero)
        styleSheet +=
          """
          node#Planetar""" + numero +
          """{
          size:20px;
          fill-color:#fed301;
          }
        """
        planetars.append(plan)
      }
    }

    // Creation des noeuds contenant les movanicDevas
    if (!gentil.movanicDeva.isEmpty) {
      for (numero <- 0 to gentil.movanicDeva.size - 1) {
        val mov: Node = graph.addNode("MovanicDeva" + numero)
        styleSheet +=
          """
          node#MovanicDeva""" + numero +
          """{
          size:15px;
          fill-color:#fed301;
          }
        """
        movanicDevas.append(mov)
      }
    }

    // Creation des noeuds contenant les astralDevas
    if (!gentil.astralDeva.isEmpty) {
      for (numero <- 0 to gentil.astralDeva.size - 1) {
        val ast: Node = graph.addNode("AstralDeva" + numero)
        styleSheet +=
          """
          node#AstralDeva""" + numero +
          """{
          size:15px;
          fill-color:#fed301;
          }
        """
        astralDevas.append(ast)
      }
    }



    // Creation des noeuds contenant les WorgsRider
    if (!mechant.worgsRider.isEmpty) {
      for (numero <- 0 to mechant.worgsRider.size - 1) {
        val worg: Node = graph.addNode("Worgs" + numero)
        styleSheet +=
          """
          node#Worgs""" + numero +
          """{
          fill-color:#555;
             shape:diamond;
          }
        """
        worgs.append(worg)
      }
    }

    // Creation du/des noeuds contenant les Warlord
    if (!mechant.warlord.isEmpty) {
      for (numero <- 0 to mechant.warlord.size - 1) {
        val warlord: Node = graph.addNode("Warlord" + numero)
        styleSheet +=
          """
          node#Warlord""" + numero +
          """{
             size: 25px;
             fill-color: #00F;
             shape:cross;
          }
        """
        warlords.append(warlord)
      }
    }


    // Creation des noeuds contenant les BarbaresOrcs
    if (!mechant.barbareOrc.isEmpty) {
      for (numero <- 0 to mechant.barbareOrc.size - 1) {
        val orc: Node = graph.addNode("BarbaresOrc" + numero)
        styleSheet +=
          """
          node#BarbaresOrc""" + numero +
          """{
            size: 25px,15px;
              fill-color:#F00;
              shape:diamond;
          }
        """
        orcs.append(orc)
      }
    }

    // Creation des noeuds contenant les angelSlayers
    if (!mechant.angelSlayer.isEmpty) {
      for (numero <- 0 to mechant.angelSlayer.size - 1) {
        val angel: Node = graph.addNode("AngelSlayer" + numero)
        styleSheet +=
          """
          node#AngelSlayer""" + numero +
          """{
            size: 25px;
              fill-color:#0000FF;
              shape:cross;
          }
        """
        angelSlayers.append(angel)
      }
    }

    // Creation des noeuds contenant les wyrms
    if (!mechant.greenGreatWyrmDragon.isEmpty) {
      for (numero <- 0 to mechant.greenGreatWyrmDragon.size - 1) {
        val wyrm: Node = graph.addNode("GreatGreenWyrmDragon" + numero)
        styleSheet +=
          """
          node#GreatGreenWyrmDragon""" + numero +
          """{
            size: 50px,50px;
              fill-color:#00FF00;
              shape:box;
          }
        """
        wyrms.append(wyrm)
      }
    }


    // Ajout des coordonnées pour les solars
    var x = 75
    var y = 0
    for (mons <- solars) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 10
    // Ajout des coordonnées pour les planetars
    for (mons <- planetars) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 15
    // Ajout des coordonnées pour les movanicDeva
    for (mons <- movanicDevas) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 20
    // Ajout des coordonnées pour les astralDeva
    for (mons <- astralDevas) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    // Ajout des coordonnées pour les worgs
    x = 70
    for (mons <- worgs) {
      mons.setAttribute("xy", Array[Double](x, distanceWorgs))
      x += 1
    }

    var rndx=0
    var rndy=0
    // Ajout des coordonnées pour les barbares


    for (mons <- orcs) {

      if (numeroCombat == 1) {
        x = 75
          rndx = rand.nextInt(2)
          rndy = rand.nextInt(2)

      }
      else
      {
        x = 75
         rndx = rand.nextInt(10) - 5
         rndy = rand.nextInt(10) - 5

        while ((rndx * rndx + rndy * rndy) > 25) {
          rndx = rand.nextInt(10) - 5
          rndy = rand.nextInt(10) - 5
        }
      }


      mons.setAttribute("xy", Array[Double](x+rndx, distanceOrc + rndy-2))



    }

    // Ajout des coordonnées pour les warlords
    x = 75
    for (mons <- warlords) {
      mons.setAttribute("xy", Array[Double](x, distanceWarlord))
      x += 1
    }

    // Ajout des coordonnées pour les angelslayers
    x = 70
    for (mons <- angelSlayers) {
      mons.setAttribute("xy", Array[Double](x, distanceAngelSlayer))
      x += 1
    }

    // Ajout des coordonnées pour les wyrms
    x = 75
    for (mons <- wyrms) {
      mons.setAttribute("xy", Array[Double](x, distanceWyrm))
      x += 1
    }



    // Ajout du label (nom) pour les solars
    for (mons <- solars) {
      mons.setAttribute("ui.label", "Solar")
    }

    // Ajout du label (nom) pour les planetar
    for (mons <- planetars) {
      mons.setAttribute("ui.label", "Planetar")
    }

    // Ajout du label (nom) pour les movanicDeva
    for (mons <- movanicDevas) {
      mons.setAttribute("ui.label", "MovanicDeva")
    }

    // Ajout du label (nom) pour les astralDeva
    for (mons <- astralDevas) {
      mons.setAttribute("ui.label", "AstralDeva")
    }

    // Ajout du label (nom) pour les worgs
    for (mons <- worgs) {
      mons.setAttribute("ui.label", "Worgs Rider")
    }

    // Ajout du label (nom) pour les barbares
    for (mons <- orcs) {
      //mons.setAttribute("ui.label", "Barbare Orc")
    }

    // Ajout du label (nom) pour les warlords
    for (mons <- warlords) {
      mons.setAttribute("ui.label", "Warlord")
    }

    // Ajout du label (nom) pour les angelSlayers
    for (mons <- angelSlayers) {
      mons.setAttribute("ui.label", "Angel Slayer")
    }

    // Ajout du label (nom) pour les wyrms
    for (mons <- wyrms) {
      mons.setAttribute("ui.label", "Great Green Wyrm Dragon")
    }


    // Applique la mise en forme et permet l'affichage
    graph.display(false)
    graph.addAttribute("ui.stylesheet", styleSheet)
    graph.addAttribute("ui.antialias")
    graph.addAttribute("ui.quality")
  }

  def updateDistance(distanceWorgs: Int, distanceOrc: Int, distanceWarlord: Int, distanceWyrm: Int, distanceAngelSlayer: Int, numeroCombat: Int): Unit = {

    // Ajout des coordonnées pour les solars
    var x = 75
    var y = 0
    for (mons <- solars) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 10
    // Ajout des coordonnées pour les planetars
    for (mons <- planetars) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 15
    // Ajout des coordonnées pour les movanicDeva
    for (mons <- movanicDevas) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    x = 70
    y = 20
    // Ajout des coordonnées pour les astralDeva
    for (mons <- astralDevas) {
      mons.setAttribute("xy", Array[Double](x, y))
      x += 1
    }

    // Ajout des coordonnées pour les worgs
    x = 70
    for (mons <- worgs) {
      mons.setAttribute("xy", Array[Double](x, distanceWorgs))
      x += 1
    }

    // Ajout des coordonnées pour les barbares

      for (mons <- orcs) {

        var coord =mons.getAttribute[Array[Double]]("xy")
        mons.setAttribute("xy", Array[Double](coord(0) , coord(1)-distanceOrc ))

      }





    // Ajout des coordonnées pour les warlords
    x = 75
    for (mons <- warlords) {
      mons.setAttribute("xy", Array[Double](x, distanceWarlord))
      x += 1
    }

    // Ajout des coordonnées pour les angelslayers
    x = 70
    for (mons <- angelSlayers) {
      mons.setAttribute("xy", Array[Double](x, distanceAngelSlayer))
      x += 1
    }

    // Ajout des coordonnées pour les wyrms
    x = 75
    for (mons <- wyrms) {
      mons.setAttribute("xy", Array[Double](x, distanceWyrm))
      x += 1
    }

    graph.addAttribute("ui.stylesheet", styleSheet)
    graph.addAttribute("ui.antialias")
    graph.addAttribute("ui.quality")
  }

}
