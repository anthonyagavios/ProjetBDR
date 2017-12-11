package GestionCombat

import Combattants.{AstralDeva, MovanicDeva, Planetar, Solar}

import scala.collection.mutable.ArrayBuffer

class PartySolar(nombreDeSolar: Int, nombreDePlanetar: Int, nombreDeMovanicDeva: Int, nombreDeAstralDeva: Int) {
  var solar = new ArrayBuffer[Solar];
  var planetar = new ArrayBuffer[Planetar];
  var movanicDeva = new ArrayBuffer[MovanicDeva];
  var astralDeva = new ArrayBuffer[AstralDeva];

  if (nombreDeAstralDeva > 0) {
    for (nAD <- 0 to nombreDeAstralDeva-1) {
      var asD = new AstralDeva;
      astralDeva.append(asD);
    }
  }
  if (nombreDeMovanicDeva > 0) {
    for (nMD <- 0 to nombreDeMovanicDeva - 1) {
      var movDe = new MovanicDeva;
      movanicDeva.append(movDe);
    }
  }
  if (nombreDePlanetar > 0) {
    for (nP <- 0 to nombreDePlanetar - 1) {
      var pla = new Planetar;
      planetar.append(pla);
    }
  }
  if (nombreDeSolar > 0) {
    for (nS <- 0 to nombreDeSolar - 1) {
      var sol = new Solar;
      solar.append(sol);
    }
  }


}