package Combattants

import GestionCombat.PartySolar

class Warlord() extends Combattant{
  var name = "warlord"
  var initiative=2
  var HP = 141;
  var AC = 27;

  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaqueMelee(ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {


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
    return 0;
  }

  def attaqueDistance(ennemi: PartySolar, nomEnnemi: String, numero: Int): Int = {

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
    return 0
  }

  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}
