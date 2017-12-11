package Combattants

import GestionCombat.PartyWyrm

class Solar() {
  var name = "solar";
  var initiative=9
  var HP = 363;
  var AC = 44;

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }



  def attaqueMelee( ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {
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


    return 0;
  }

  def attaqueDistance(ennemi: PartyWyrm, nomEnnemi: String, numero: Int): Int = {

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

    return 0

  }

  def attaqueMagic(){}

  def regeneration(): Unit ={
    HP=HP+15;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }


}
