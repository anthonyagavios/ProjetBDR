package Combattants

import GestionCombat.PartyWyrm

class AstralDeva() extends Combattant{
  var name = "astralDeva"
  var initiative=8
  var HP = 172;
  var AC = 29;
  var posX: Int = 20
  var posY: Int = 20

  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  override def attaqueMelee(ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {


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
    return 0;
  }

  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}