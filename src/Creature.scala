import scala.collection.mutable.ArrayBuffer;
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.{PairRDDFunctions, RDD}


class creature(val name: String, val link: String) extends Serializable {
  var spells = ArrayBuffer[String]()

  def addspell(spell: String): Unit = {
    spells += spell
  }

  def getNomSpell: Unit = {
    for (spe <- spells) {
      var nomSpell = spe.toString.split("#");
      return nomSpell;
    }
  }
}

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


class Solar() {
  var name = "solar";
  var HP = 363;
  var AC = 44;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }

  def attaque(typeAttaque: String, ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "ranged") {
      if (nomEnnemi == "angelSlayer") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (31 - 5 * att) > ennemi.angelSlayer(numero).AC) {
            var degats = scala.util.Random.nextInt(26)
            ennemi.angelSlayer(numero).priseDeDegats(degats)
            if (ennemi.angelSlayer(numero).HP <= 0) {
              ennemi.angelSlayer.remove(numero)
              return 0
            }
          }

        }
      } else if (nomEnnemi == "babareOrc") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (31 - 5 * att) > ennemi.barbareOrc(numero).AC) {
            var degats = scala.util.Random.nextInt(26)
            ennemi.barbareOrc(numero).priseDeDegats(degats)
            if (ennemi.barbareOrc(numero).HP <= 0) {
              ennemi.barbareOrc.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "warlord") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (31 - 5 * att) > ennemi.warlord(numero).AC) {
            var degats = scala.util.Random.nextInt(26)
            ennemi.warlord(numero).priseDeDegats(degats)
            if (ennemi.warlord(numero).HP <= 0) {
              ennemi.warlord.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "worgsRider") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (31 - 5 * att) > ennemi.worgsRider(numero).AC) {
            var degats = scala.util.Random.nextInt(26)
            ennemi.worgsRider(numero).priseDeDegats(degats)
            if (ennemi.worgsRider(numero).HP <= 0) {
              ennemi.worgsRider.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "greenGreatWyrmDragon") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (31 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
            var degats = scala.util.Random.nextInt(26)
            ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
            if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
              ennemi.greenGreatWyrmDragon.remove(numero)
              return 0
            }
          }

        }
      }


    }
    else if (typeAttaque == "melee") {
      if (nomEnnemi == "angelSlayer") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (35 - 5 * att) > ennemi.angelSlayer(numero).AC) {
            var degats = scala.util.Random.nextInt(36)
            ennemi.angelSlayer(numero).priseDeDegats(degats)
            if (ennemi.angelSlayer(numero).HP <= 0) {
              ennemi.angelSlayer.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "babareOrc") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (35 - 5 * att) > ennemi.barbareOrc(numero).AC) {
            var degats = scala.util.Random.nextInt(36)
            ennemi.barbareOrc(numero).priseDeDegats(degats)
            if (ennemi.barbareOrc(numero).HP <= 0) {
              ennemi.barbareOrc.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "warlord") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (35 - 5 * att) > ennemi.warlord(numero).AC) {
            var degats = scala.util.Random.nextInt(36)
            ennemi.warlord(numero).priseDeDegats(degats)
            if (ennemi.warlord(numero).HP <= 0) {
              ennemi.warlord.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "worgsRider") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (35 - 5 * att) > ennemi.worgsRider(numero).AC) {
            var degats = scala.util.Random.nextInt(36)
            ennemi.worgsRider(numero).priseDeDegats(degats)
            if (ennemi.worgsRider(numero).HP <= 0) {
              ennemi.worgsRider.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "greenGreatWyrmDragon") {
        for (att <- 0 to 3) {
          if (jetDeDes() + (35 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
            var degats = scala.util.Random.nextInt(36)
            ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
            if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
              ennemi.greenGreatWyrmDragon.remove(numero)
              return 0
            }
          }

        }
      }

    }
    return 0;
  }
}

class Planetar() {
  var name = "planetar"
  var HP = 229;
  var AC = 32;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "angelSlayer") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (27 - 5 * att) > ennemi.angelSlayer(numero).AC) {
            var degats = scala.util.Random.nextInt(33)
            ennemi.angelSlayer(numero).priseDeDegats(degats)
            if (ennemi.angelSlayer(numero).HP <= 0) {
              ennemi.angelSlayer.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "babareOrc") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (27 - 5 * att) > ennemi.barbareOrc(numero).AC) {
            var degats = scala.util.Random.nextInt(33)
            ennemi.barbareOrc(numero).priseDeDegats(degats)
            if (ennemi.barbareOrc(numero).HP <= 0) {
              ennemi.barbareOrc.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "warlord") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (27 - 5 * att) > ennemi.warlord(numero).AC) {
            var degats = scala.util.Random.nextInt(33)
            ennemi.warlord(numero).priseDeDegats(degats)
            if (ennemi.warlord(numero).HP <= 0) {
              ennemi.warlord.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "worgsRider") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (27 - 5 * att) > ennemi.worgsRider(numero).AC) {
            var degats = scala.util.Random.nextInt(33)
            ennemi.worgsRider(numero).priseDeDegats(degats)
            if (ennemi.worgsRider(numero).HP <= 0) {
              ennemi.worgsRider.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "greenGreatWyrmDragon") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (27 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
            var degats = scala.util.Random.nextInt(33)
            ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
            if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
              ennemi.greenGreatWyrmDragon.remove(numero)
              return 0
            }
          }

        }
      }

    }
    return 0;
  }


  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class MovanicDeva() {
  var name = "movanicDeva"
  var HP = 126;
  var AC = 24;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "angelSlayer") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (17 - 5 * att) > ennemi.angelSlayer(numero).AC) {
            var degats = scala.util.Random.nextInt(19)
            ennemi.angelSlayer(numero).priseDeDegats(degats)
            if (ennemi.angelSlayer(numero).HP <= 0) {
              ennemi.angelSlayer.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "babareOrc") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (17 - 5 * att) > ennemi.barbareOrc(numero).AC) {
            var degats = scala.util.Random.nextInt(19)
            ennemi.barbareOrc(numero).priseDeDegats(degats)
            if (ennemi.barbareOrc(numero).HP <= 0) {
              ennemi.barbareOrc.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "warlord") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (17 - 5 * att) > ennemi.warlord(numero).AC) {
            var degats = scala.util.Random.nextInt(19)
            ennemi.warlord(numero).priseDeDegats(degats)
            if (ennemi.warlord(numero).HP <= 0) {
              ennemi.warlord.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "worgsRider") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (17 - 5 * att) > ennemi.worgsRider(numero).AC) {
            var degats = scala.util.Random.nextInt(19)
            ennemi.worgsRider(numero).priseDeDegats(degats)
            if (ennemi.worgsRider(numero).HP <= 0) {
              ennemi.worgsRider.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "greenGreatWyrmDragon") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (17 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
            var degats = scala.util.Random.nextInt(19)
            ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
            if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
              ennemi.greenGreatWyrmDragon.remove(numero)
              return 0
            }
          }

        }
      }

    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class AstralDeva() {
  var name = "astralDeva"
  var HP = 172;
  var AC = 29;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "angelSlayer") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (26 - 5 * att) > ennemi.angelSlayer(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.angelSlayer(numero).priseDeDegats(degats)
            if (ennemi.angelSlayer(numero).HP <= 0) {
              ennemi.angelSlayer.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "babareOrc") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (26 - 5 * att) > ennemi.barbareOrc(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.barbareOrc(numero).priseDeDegats(degats)
            if (ennemi.barbareOrc(numero).HP <= 0) {
              ennemi.barbareOrc.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "warlord") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (26 - 5 * att) > ennemi.warlord(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.warlord(numero).priseDeDegats(degats)
            if (ennemi.warlord(numero).HP <= 0) {
              ennemi.warlord.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "worgsRider") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (26 - 5 * att) > ennemi.worgsRider(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.worgsRider(numero).priseDeDegats(degats)
            if (ennemi.worgsRider(numero).HP <= 0) {
              ennemi.worgsRider.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "greenGreatWyrmDragon") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (26 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
            if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
              ennemi.greenGreatWyrmDragon.remove(numero)
              return 0
            }
          }

        }
      }

    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class BarbareOrc() {
  var name = "babareOrc"
  var HP = 142;
  var AC = 17;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }


  def attaque(typeAttaque: String, ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (11 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (11 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (11 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (11 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(22)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    } else if (typeAttaque == "ranged") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (5 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(13)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (5 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(13)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (5 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(13)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (5 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(13)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class Warlord() {
  var name = "warlord"
  var HP = 141;
  var AC = 27;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (20 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(18)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (20 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(18)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (20 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(18)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (20 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(18)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    } else if (typeAttaque == "ranged") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(11)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(11)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(11)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(11)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class WorgsRider() {
  var name = "worgsRider"
  var HP = 13;
  var AC = 18;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (6 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(10)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (6 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(10)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (6 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(10)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (6 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(10)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    } else if (typeAttaque == "ranged") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (4 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(6)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (4 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(6)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (4 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(6)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (4 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(6)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class AngelSlayer() {
  var name = "angelSlayer"
  var HP = 112;
  var AC = 26;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (21 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(15)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (21 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(15)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (21 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(15)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (21 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(15)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    } else if (typeAttaque == "ranged") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(14)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(14)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(14)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 2) {
          if (jetDeDes() + (19 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(14)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}

class GreenGreatWyrmDragon() {
  var name = "greenGreatWyrmDragon"
  var HP = 391;
  var AC = 37;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaque(typeAttaque: String, ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

    if (typeAttaque == "melee") {
      if (nomEnnemi == "solar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (33 - 5 * att) > ennemi.solar(numero).AC) {
            var degats = scala.util.Random.nextInt(53)
            ennemi.solar(numero).priseDeDegats(degats)
            if (ennemi.solar(numero).HP <= 0) {
              ennemi.solar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "astralDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (33 - 5 * att) > ennemi.astralDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(53)
            ennemi.astralDeva(numero).priseDeDegats(degats)
            if (ennemi.astralDeva(numero).HP <= 0) {
              ennemi.astralDeva.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "planetar") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (33 - 5 * att) > ennemi.planetar(numero).AC) {
            var degats = scala.util.Random.nextInt(53)
            ennemi.planetar(numero).priseDeDegats(degats)
            if (ennemi.planetar(numero).HP <= 0) {
              ennemi.planetar.remove(numero)
              return 0
            }
          }

        }
      }
      else if (nomEnnemi == "movanicDeva") {
        for (att <- 0 to 0) {
          if (jetDeDes() + (33 - 5 * att) > ennemi.movanicDeva(numero).AC) {
            var degats = scala.util.Random.nextInt(53)
            ennemi.movanicDeva(numero).priseDeDegats(degats)
            if (ennemi.movanicDeva(numero).HP <= 0) {
              ennemi.movanicDeva.remove(numero)
              return 0
            }
          }

        }
      }
    }
    return 0;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}






