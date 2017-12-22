package Combattants

import GestionCombat.PartySolar
import Graph.node
import org.apache.spark.graphx.Graph


class GreenGreatWyrmDragon() extends Combattant{
  var name = "greenGreatWyrmDragon"
  var initiative=2
  var vitesse=40
  var HP = 391;
  var AC = 37;
  override var posX = 0
  override var posY = 0

  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }

  def attaqueMelee( ennemi: PartySolar, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    newNode.target = sommet.target
    var hp = sommet.combatant.HP

    if (nomEnnemi == "solar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (33 - 5 * att) > ennemi.solar(numero).AC) {
          var degats = scala.util.Random.nextInt(53)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "astralDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (33 - 5 * att) > ennemi.astralDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(53)
          ennemi.astralDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.astralDeva(numero).HP <= 0) {
            ennemi.astralDeva.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "planetar") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (33 - 5 * att) > ennemi.planetar(numero).AC) {
          var degats = scala.util.Random.nextInt(53)
          ennemi.planetar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.planetar(numero).HP <= 0) {
            ennemi.planetar.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "movanicDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() + (33 - 5 * att) > ennemi.movanicDeva(numero).AC) {
          var degats = scala.util.Random.nextInt(53)
          ennemi.movanicDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.movanicDeva(numero).HP <= 0) {
            ennemi.movanicDeva.remove(numero)
            return newNode
          }
        }

      }
    }
    return newNode
  }

  def attaqueMagic(ennemi: PartySolar, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    var hp = sommet.combatant.HP
    if (nomEnnemi == "solar") {
      for (att <- 0 to 0) {
        if (jetDeDes() > 31) {
          var degats = scala.util.Random.nextInt(144)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }else{
          var degats = scala.util.Random.nextInt(144/2)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "astralDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() > 31) {
          var degats = scala.util.Random.nextInt(144)
          ennemi.astralDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.astralDeva(numero).HP <= 0) {
            ennemi.astralDeva.remove(numero)
            return newNode
          }
        }else{
          var degats = scala.util.Random.nextInt(144/2)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "planetar") {
      for (att <- 0 to 0) {
        if (jetDeDes() > 31) {
          var degats = scala.util.Random.nextInt(144)
          ennemi.planetar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.planetar(numero).HP <= 0) {
            ennemi.planetar.remove(numero)
            return newNode
          }
        }else{
          var degats = scala.util.Random.nextInt(144/2)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }

      }
    }
    else if (nomEnnemi == "movanicDeva") {
      for (att <- 0 to 0) {
        if (jetDeDes() > 31) {
          var degats = scala.util.Random.nextInt(144)
          ennemi.movanicDeva(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.movanicDeva(numero).HP <= 0) {
            ennemi.movanicDeva.remove(numero)
            return newNode
          }
        }else{
          var degats = scala.util.Random.nextInt(144/2)
          ennemi.solar(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          if (ennemi.solar(numero).HP <= 0) {
            ennemi.solar.remove(numero)
            return newNode
          }
        }

      }
    }
    return newNode
  }

  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }
}
