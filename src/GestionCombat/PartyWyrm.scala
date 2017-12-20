package GestionCombat

import Combattants._
import Graph.GraphCombat

import scala.collection.mutable.ArrayBuffer

class PartyWyrm(nombreDeWyrm: Int, nombreDeBarbareOrc: Int, nombreDeAngelSlayer: Int, nombreDeWorgsRider: Int, nombreDeWarlord: Int, graph : GraphCombat) {
  var greenGreatWyrmDragon = new ArrayBuffer[GreenGreatWyrmDragon];
  var angelSlayer = new ArrayBuffer[AngelSlayer];
  var worgsRider = new ArrayBuffer[WorgsRider];
  var warlord = new ArrayBuffer[Warlord];
  var barbareOrc = new ArrayBuffer[BarbareOrc];

  if (nombreDeWyrm > 0) {
    for (nW <- 0 to nombreDeWyrm-1) {
      graph.addNode(new GreenGreatWyrmDragon, 2)
    }
  }
  if (nombreDeBarbareOrc > 0) {
    for (nBO <- 0 to nombreDeBarbareOrc-1) {
      graph.addNode(new BarbareOrc, 2)
    }
  }
  if (nombreDeAngelSlayer > 0) {
    for (nP <- 0 to nombreDeAngelSlayer-1) {
      graph.addNode(new AngelSlayer, 2)
    }
  }
  if (nombreDeWorgsRider > 0) {
    for (nS <- 0 to nombreDeWorgsRider-1) {
      graph.addNode(new WorgsRider, 2)

    }
  }
  if (nombreDeWarlord > 0) {
    for (nS <- 0 to nombreDeWarlord-1) {
      graph.addNode(new Warlord, 2)
    }
  }



}