package Combattants

import GestionCombat.PartyWyrm
import Graph.node
import org.apache.spark.graphx.Graph

/**
  * Created by adrie on 11/12/2017.
  */
abstract class Combattant extends Serializable{
    var name : String
  var initiative : Int
  var HP : Int
  var AC : Int
  var posX : Int
  var posY : Int

  def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaqueMelee( ennemi: PartyWyrm, nomEnnemi: String, numero: Int, sommet : node): node = {
    if (nomEnnemi == "angelSlayer") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.angelSlayer(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.angelSlayer(numero).priseDeDegats(degats)
          if (ennemi.angelSlayer(numero).HP <= 0) {
            ennemi.angelSlayer.remove(numero)
            return sommet
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
            return sommet
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
            return sommet
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
            return sommet
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
            return sommet
          }
        }

      }
    }


    return sommet;
  }

  def attaqueDistance(ennemi: PartyWyrm, nomEnnemi: String, numero: Int, sommet : node): node = {

    if (nomEnnemi == "angelSlayer") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.angelSlayer(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.angelSlayer(numero).priseDeDegats(degats)
          if (ennemi.angelSlayer(numero).HP <= 0) {
            ennemi.angelSlayer.remove(numero)
            return sommet
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
            return sommet
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
            return sommet
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
            return sommet
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
            return sommet
          }
        }

      }
    }

    return sommet

  }

  def attaqueMagic(){}

  def regeneration(): Unit ={
    HP=HP+15;
  }

  def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }

}
