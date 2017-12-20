package GestionCombat

import Graph.GraphCombat
import Combattants.{AstralDeva, MovanicDeva, Planetar, Solar}
import Graph.node
import org.apache.spark.graphx.Graph

import scala.collection.mutable.ArrayBuffer

class PartySolar(nombreDeSolar: Int, nombreDePlanetar: Int, nombreDeMovanicDeva: Int, nombreDeAstralDeva: Int, graph : GraphCombat) {
  var solar = new ArrayBuffer[Solar];
  var planetar = new ArrayBuffer[Planetar];
  var movanicDeva = new ArrayBuffer[MovanicDeva];
  var astralDeva = new ArrayBuffer[AstralDeva];

  if (nombreDeAstralDeva > 0) {
    for (nAD <- 0 to nombreDeAstralDeva-1) {
      graph.addNode(new AstralDeva, 1)
    }
  }
  if (nombreDeMovanicDeva > 0) {
    for (nMD <- 0 to nombreDeMovanicDeva - 1) {
      graph.addNode(new MovanicDeva, 1)
    }
  }
  if (nombreDePlanetar > 0) {
    for (nP <- 0 to nombreDePlanetar - 1) {
      graph.addNode(new Planetar, 1)
    }
  }
  if (nombreDeSolar > 0) {
    for (nS <- 0 to nombreDeSolar - 1) {
      graph.addNode(new Solar, 1)
    }
  }


}