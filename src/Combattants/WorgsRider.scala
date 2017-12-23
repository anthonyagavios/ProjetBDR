package Combattants

import GestionCombat.PartySolar
import Graph.node
import org.apache.spark.graphx.Graph


class WorgsRider() extends Combattant {

  var name = "worgsRider"
  var initiative = 2
  var vitesse = 20
  var HP = 13;
  var AC = 18;
  override var posX = 20
  override var posY = 20

  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  override def attaqueMelee(ennemi: PartySolar, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    newNode.target = sommet.target
    var hp = sommet.combatant.HP

    if (nomEnnemi == "solar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (6 - 5 * att) > ennemi.solar(numero).AC) {
          var degats = scala.util.Random.nextInt(10)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "astralDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (6 - 5 * att) > ennemi.astralDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(10)
          ennemi.astralDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "planetar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (6 - 5 * att) > ennemi.planetar(numero).AC) {
          var degats = scala.util.Random.nextInt(10)
          ennemi.planetar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "movanicDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (6 - 5 * att) > ennemi.movanicDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(10)
          ennemi.movanicDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    if(hp <= 0){
      newNode.live = false
    }
    return newNode
  }

  override def attaqueDistance(ennemi: PartySolar, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    var hp = sommet.combatant.HP
    if (nomEnnemi == "solar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (4 - 5 * att) > ennemi.solar(numero).AC) {
          var degats = scala.util.Random.nextInt(6)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "astralDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (4 - 5 * att) > ennemi.astralDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(6)
          ennemi.astralDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "planetar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (4 - 5 * att) > ennemi.planetar(numero).AC) {
          var degats = scala.util.Random.nextInt(6)
          ennemi.planetar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    else if (nomEnnemi == "movanicDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (4 - 5 * att) > ennemi.movanicDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(6)
          ennemi.movanicDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
        }

      }
    }
    if(hp <= 0){
      newNode.live = false
    }
    return newNode
  }

  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}
