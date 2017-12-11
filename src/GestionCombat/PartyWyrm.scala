package GestionCombat

import Combattants._

import scala.collection.mutable.ArrayBuffer

class PartyWyrm(nombreDeWyrm: Int, nombreDeBarbareOrc: Int, nombreDeAngelSlayer: Int, nombreDeWorgsRider: Int, nombreDeWarlord: Int) {
  var greenGreatWyrmDragon = new ArrayBuffer[GreenGreatWyrmDragon];
  var angelSlayer = new ArrayBuffer[AngelSlayer];
  var worgsRider = new ArrayBuffer[WorgsRider];
  var warlord = new ArrayBuffer[Warlord];
  var barbareOrc = new ArrayBuffer[BarbareOrc];

  if (nombreDeWyrm > 0) {
    for (nW <- 0 to nombreDeWyrm-1) {
      var gGWD = new GreenGreatWyrmDragon;
      greenGreatWyrmDragon.append(gGWD);
    }
  }
  if (nombreDeBarbareOrc > 0) {
    for (nBO <- 0 to nombreDeBarbareOrc-1) {
      var bO = new BarbareOrc;
      barbareOrc.append(bO);
    }
  }
  if (nombreDeAngelSlayer > 0) {
    for (nP <- 0 to nombreDeAngelSlayer-1) {
      var angS = new AngelSlayer;
      angelSlayer.append(angS);
    }
  }
  if (nombreDeWorgsRider > 0) {
    for (nS <- 0 to nombreDeWorgsRider-1) {
      var worgR = new WorgsRider;
      worgsRider.append(worgR);
    }
  }
  if (nombreDeWarlord > 0) {
    for (nS <- 0 to nombreDeWarlord-1) {
      var warl = new Warlord;
      warlord.append(warl);
    }
  }

  def miseAjour(): Unit = {
    if (!barbareOrc.isEmpty) {
      for (numero <- 0 to barbareOrc.size) {
        if (barbareOrc(numero).HP <= 0) {
          barbareOrc.remove(numero)
        }
      }
    } else if (!angelSlayer.isEmpty) {
      for (numero <- 0 to angelSlayer.size) {
        if (angelSlayer(numero).HP <= 0) {
          angelSlayer.remove(numero)
        }
      }
    }
    else if (!worgsRider.isEmpty) {
      for (numero <- 0 to worgsRider.size) {
        if (worgsRider(numero).HP <= 0) {
          worgsRider.remove(numero)
        }
      }
    }
    else if (!warlord.isEmpty) {
      for (numero <- 0 to warlord.size) {
        if (warlord(numero).HP <= 0) {
          warlord.remove(numero)
        }
      }
    }
    else if (!greenGreatWyrmDragon.isEmpty) {
      for (numero <- 0 to greenGreatWyrmDragon.size) {
        if (greenGreatWyrmDragon(numero).HP <= 0) {
          greenGreatWyrmDragon.remove(numero)
        }
      }
    } else {
      println("Combat terminé : win")
    }
  }

}