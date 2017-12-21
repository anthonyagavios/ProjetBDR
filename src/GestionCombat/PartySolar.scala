package GestionCombat

import Graph.GraphCombat
import Combattants.{AstralDeva, MovanicDeva, Planetar, Solar}
import Graph.node
import org.apache.spark.graphx.Graph

import scala.collection.mutable.ArrayBuffer

class PartySolar(nombreDeSolar: Int, nombreDePlanetar: Int, nombreDeMovanicDeva: Int, nombreDeAstralDeva: Int, graph : GraphCombat) extends Serializable {
  var solar = new ArrayBuffer[Solar];
  var planetar = new ArrayBuffer[Planetar];
  var movanicDeva = new ArrayBuffer[MovanicDeva];
  var astralDeva = new ArrayBuffer[AstralDeva];

  if (nombreDeAstralDeva > 0) {
    for (nAD <- 0 to nombreDeAstralDeva-1) {
      var asD = new AstralDeva;
      astralDeva.append(asD);
      graph.addNode(asD, 1)

    }
  }
  if (nombreDeMovanicDeva > 0) {
    for (nMD <- 0 to nombreDeMovanicDeva - 1) {
      var movDe = new MovanicDeva;
      movanicDeva.append(movDe);
      graph.addNode(movDe, 1)
    }
  }
  if (nombreDePlanetar > 0) {
    for (nP <- 0 to nombreDePlanetar - 1) {
      var pla = new Planetar;
      planetar.append(pla)
      graph.addNode(pla, 1)
    }
  }
  if (nombreDeSolar > 0) {
    for (nS <- 0 to nombreDeSolar - 1) {
      var sol = new Solar;
      solar.append(sol);
      graph.addNode(sol, 1)
    }
  }


}