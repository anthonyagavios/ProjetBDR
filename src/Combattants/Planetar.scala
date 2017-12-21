package Combattants

import GestionCombat.PartyWyrm
import Graph.node
import org.apache.spark.graphx.Graph

class Planetar() extends Combattant{
  var name = "planetar"
  var initiative=8
  var vitesse=30
  var HP = 229;
  var AC = 32;
  override var posX = 0
  override var posY = 0

  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  override def attaqueMelee(ennemi: PartyWyrm, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    var hp = sommet.combatant.HP

    if (nomEnnemi == "angelSlayer") {
      for (att <- 0 to 2) {
        if (jetDeDes() + (27 - 5 * att) > ennemi.angelSlayer(numero).AC) {
          var degats = scala.util.Random.nextInt(33)
          ennemi.angelSlayer(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.angelSlayer(numero).HP <= 0) {
            ennemi.angelSlayer.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "babareOrc") {
      for (att <- 0 to 2) {
        if (jetDeDes() + (27 - 5 * att) > ennemi.barbareOrc(numero).AC) {
          var degats = scala.util.Random.nextInt(33)
          ennemi.barbareOrc(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.barbareOrc(numero).HP <= 0) {
            ennemi.barbareOrc.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "warlord") {
      for (att <- 0 to 2) {
        if (jetDeDes() + (27 - 5 * att) > ennemi.warlord(numero).AC) {
          var degats = scala.util.Random.nextInt(33)
          ennemi.warlord(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.warlord(numero).HP <= 0) {
            ennemi.warlord.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "worgsRider") {
      for (att <- 0 to 2) {
        if (jetDeDes() + (27 - 5 * att) > ennemi.worgsRider(numero).AC) {
          var degats = scala.util.Random.nextInt(33)
          ennemi.worgsRider(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.worgsRider(numero).HP <= 0) {
            ennemi.worgsRider.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "greenGreatWyrmDragon") {
      for (att <- 0 to 2) {
        if (jetDeDes() + (27 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
          var degats = scala.util.Random.nextInt(33)
          ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
            ennemi.greenGreatWyrmDragon.remove(numero)
            return newNode
          }
        }

      }
    }
    return newNode;
  }


  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}