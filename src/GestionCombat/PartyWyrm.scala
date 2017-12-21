package GestionCombat

import Combattants._
import Graph.GraphCombat

import scala.collection.mutable.ArrayBuffer

class PartyWyrm(nombreDeWyrm: Int, nombreDeBarbareOrc: Int, nombreDeAngelSlayer: Int, nombreDeWorgsRider: Int, nombreDeWarlord: Int, graph : GraphCombat) extends Serializable{
  var greenGreatWyrmDragon = new ArrayBuffer[GreenGreatWyrmDragon];
  var angelSlayer = new ArrayBuffer[AngelSlayer];
  var worgsRider = new ArrayBuffer[WorgsRider];
  var warlord = new ArrayBuffer[Warlord];
  var barbareOrc = new ArrayBuffer[BarbareOrc];

  if (nombreDeWyrm > 0) {
    for (nW <- 0 to nombreDeWyrm-1) {
      var gGWD = new GreenGreatWyrmDragon;
      greenGreatWyrmDragon.append(gGWD);
      graph.addNode(gGWD, 2)
    }
  }
  if (nombreDeBarbareOrc > 0) {
    for (nBO <- 0 to nombreDeBarbareOrc-1) {
      var bO = new BarbareOrc;
      barbareOrc.append(bO);
      graph.addNode(bO, 2)
    }
  }
  if (nombreDeAngelSlayer > 0) {
    for (nP <- 0 to nombreDeAngelSlayer-1) {
      var angS = new AngelSlayer;
      angelSlayer.append(angS);
      graph.addNode(angS, 2)
    }
  }
  if (nombreDeWorgsRider > 0) {
    for (nS <- 0 to nombreDeWorgsRider-1) {
      var worgR = new WorgsRider;
      worgsRider.append(worgR);
      graph.addNode(worgR, 2)

    }
  }
  if (nombreDeWarlord > 0) {
    for (nS <- 0 to nombreDeWarlord-1) {
      var warl = new Warlord;
      warlord.append(warl);
      graph.addNode(warl, 2)
    }
  }



}