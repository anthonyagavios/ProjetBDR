import org.graphstream.graph.{Edge, Node}
import org.graphstream.graph.implementations.SingleGraph

object testGraph extends App {
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


  node#Solar {
    size:50px;
    fill-color:#fed301;
  }

   node#Pito {
    size: 25px;
    fill-color:#fed301;
  }
   node#Worgs1{
      fill-color:#555;
   shape:diamond;

    }
       node#Worgs2{
          fill-color:#555;
    shape:diamond;

    }
      node#Worgs3{
        fill-color:#555;
    shape:diamond;

    }
       node#Worgs4{
          fill-color:#555;
    shape:diamond;

    }
       node#Worgs5{
          fill-color:#555;
   shape:diamond;

    }
       node#Worgs6{
          fill-color:#555;
   shape:diamond;

   }
node#Worgs7{
         fill-color:#555;
    shape:diamond;

    }
      node#Worgs8{
          fill-color:#555;
    shape:diamond;

    }
       node#Worgs9{
   fill-color:#555;
   shape:diamond;
   }
     node#Barbares1{
         size: 25px,15px;
      fill-color:#F00;
      shape:diamond;

    }
       node#Barbares2{
            size: 25px,15px;
     fill-color:#F00;
      shape:diamond;

   }
      node#Barbares3{
            size: 25px,15px;
     fill-color:#F00;
      shape:diamond;

   }
      node#Barbares4 {
     size: 25px,15px;
     fill-color:#F00;
     shape:diamond;

   }
  node#Warlord {
  size: 25px;
  fill-color: #00F;
  shape:cross;
  }

  edge {
    size: 3px;
    fill-color: black;
    fill-mode: plain;
    arrow-shape: arrow;
    arrow-size: 10px, 3px;
  }


"""


  graph.display
  graph.addAttribute("ui.stylesheet", styleSheet)
  graph.addAttribute("ui.antialias")
  graph.addAttribute("ui.quality")

  val Solar: Node = graph.addNode("Solar")
  val Pito: Node = graph.addNode("Pito")
  val Worgs1: Node = graph.addNode("Worgs1")
  val Worgs2: Node = graph.addNode("Worgs2")
  val Worgs3: Node = graph.addNode("Worgs3")
  val Worgs4: Node = graph.addNode("Worgs4")
  val Worgs5: Node = graph.addNode("Worgs5")
  val Worgs6: Node = graph.addNode("Worgs6")
  val Worgs7: Node = graph.addNode("Worgs7")
  val Worgs8: Node = graph.addNode("Worgs8")
  val Worgs9: Node = graph.addNode("Worgs9")
  val Warlord: Node = graph.addNode("Warlord")
  val Barbares1: Node = graph.addNode("Barbares1")
  val Barbares2: Node = graph.addNode("Barbares2")
  val Barbares3: Node = graph.addNode("Barbares3")
  val Barbares4: Node = graph.addNode("Barbares4")





  Solar.setAttribute("xy", Array[Double](10, 10))
  Pito.setAttribute("xy", Array[Double](10, 8))
  Worgs1.setAttribute("xy", Array[Double](10, 15))
  Worgs2.setAttribute("xy", Array[Double](12, 15))
  Worgs3.setAttribute("xy", Array[Double](8, 15))
  Worgs4.setAttribute("xy", Array[Double](11, 16))
  Worgs5.setAttribute("xy", Array[Double](13, 13))
  Worgs6.setAttribute("xy", Array[Double](7, 16))
  Worgs7.setAttribute("xy", Array[Double](6, 14))
  Worgs8.setAttribute("xy", Array[Double](13, 17))
  Worgs9.setAttribute("xy", Array[Double](8, 17))
  Barbares1.setAttribute("xy", Array[Double](11, 20))
  Barbares2.setAttribute("xy", Array[Double](13, 20))
  Barbares3.setAttribute("xy", Array[Double](9, 20))
  Barbares4.setAttribute("xy", Array[Double](7, 20))
  Warlord.setAttribute("xy", Array[Double](10, 25))


  Solar.setAttribute("ui.label", "Solar")
  Pito.setAttribute("ui.label", "Pito")
  Worgs1.setAttribute("ui.label", "Worgs Rider")
  Worgs2.setAttribute("ui.label", "Worgs Rider")
  Worgs3.setAttribute("ui.label", "Worgs Rider")
  Worgs4.setAttribute("ui.label", "Worgs Rider")
  Worgs5.setAttribute("ui.label", "Worgs Rider")
  Worgs6.setAttribute("ui.label", "Worgs Rider")
  Worgs7.setAttribute("ui.label", "Worgs Rider")
  Worgs8.setAttribute("ui.label", "Worgs Rider")
  Worgs9.setAttribute("ui.label", "Worgs Rider")


  Barbares1.setAttribute("ui.label", "Barbares Orc")
  Barbares2.setAttribute("ui.label", "Barbares Orc")
  Barbares4.setAttribute("ui.label", "Barbares Orc")
  Barbares3.setAttribute("ui.label", "Barbares Orc")
  Warlord.setAttribute("ui.label", "Warlord")



}
