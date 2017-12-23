package Combattants

import Bestiaire.creature
import GestionCombat.{PartySolar, PartyWyrm}
import Graph.node
import org.apache.spark.graphx.Graph

class Solar() extends Combattant {
  var name = "solar"
  var initiative = 9
  var vitesse = 50
  var HP = 363;
  var AC = 44;
  override var posX = 0
  override var posY = 0


  override def jetDeDes(): Int = {
    // Jet de des en random avec une limite à 20
    val rand = scala.util.Random
    var jetDes = rand.nextInt(20)
    return jetDes;
  }
// sommet node qui est attaqué
  //nomEnnemi ennemi qui l'attaque
  //ennemi + numero : attaque de l'ennemi
  override def attaqueMelee(ennemi: PartyWyrm, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    newNode.target = sommet.target
    var hp = sommet.combatant.HP
    if (nomEnnemi == "angelSlayer") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.angelSlayer(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.angelSlayer(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp
          /*if (ennemi.angelSlayer(numero).HP <= 0) {
            ennemi.angelSlayer.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "babareOrc") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.barbareOrc(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.barbareOrc(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.barbareOrc(numero).HP <= 0) {
            ennemi.barbareOrc.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "warlord") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.warlord(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.warlord(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.warlord(numero).HP <= 0) {
            ennemi.warlord.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "worgsRider") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.worgsRider(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.worgsRider(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.worgsRider(numero).HP <= 0) {
            ennemi.worgsRider.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "greenGreatWyrmDragon") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (35 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
          var degats = scala.util.Random.nextInt(36)
          ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
            ennemi.greenGreatWyrmDragon.remove(numero)
            return newNode
          }*/
        }

      }
    }
    if(hp <= 0){
      newNode.live = false
    }

    return newNode
  }

  override def attaqueDistance(ennemi: PartyWyrm, nomEnnemi: String, numero: Int, sommet : node): node = {
    var newNode = new node()
    newNode.id = sommet.id
    newNode.team = sommet.team
    newNode.combatant = sommet.combatant
    var hp = sommet.combatant.HP
    if (nomEnnemi == "angelSlayer") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.angelSlayer(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.angelSlayer(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.angelSlayer(numero).HP <= 0) {
            ennemi.angelSlayer.remove(numero)
            return newNode
          }*/
        }

      }
    } else if (nomEnnemi == "babareOrc") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.barbareOrc(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.barbareOrc(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.barbareOrc(numero).HP <= 0) {
            ennemi.barbareOrc.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "warlord") {

      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.warlord(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.warlord(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.warlord(numero).HP <= 0) {
            ennemi.warlord.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "worgsRider") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.worgsRider(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.worgsRider(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.worgsRider(numero).HP <= 0) {
            ennemi.worgsRider.remove(numero)
            return newNode
          }*/
        }

      }
    }
    else if (nomEnnemi == "greenGreatWyrmDragon") {
      for (att <- 0 to 3) {
        if (jetDeDes() + (31 - 5 * att) > ennemi.greenGreatWyrmDragon(numero).AC) {
          var degats = scala.util.Random.nextInt(26)
          ennemi.greenGreatWyrmDragon(numero).priseDeDegats(degats)
          hp = hp - degats
          newNode.combatant.HP = hp

          /*if (ennemi.greenGreatWyrmDragon(numero).HP <= 0) {
            ennemi.greenGreatWyrmDragon.remove(numero)
            return newNode
          }*/
        }

      }
    }
    if(hp <= 0){
      newNode.live = false
    }

    return newNode

  }

   def massHeal(allier: PartySolar): Unit = {
    for (sol <- allier.solar) {
      if (sol.HP + 250 <= 363) {
        sol.HP + 250
      }
      else {
        sol.HP = 363
      }
    }
    for (mova <- allier.movanicDeva) {
      if (mova.HP + 250 <= 126) {
        mova.HP + 250
      }
      else {
        mova.HP = 126
      }
    }
    for (ast <- allier.astralDeva) {
      if (ast.HP + 250 <= 172) {
        ast.HP + 250
      }
      else {
        ast.HP = 172
      }
    }
    for (pla <- allier.planetar) {
      if (pla.HP + 250 <= 229) {
        pla.HP + 250
      }
      else {
        pla.HP = 229
      }
    }
  }

  override def regeneration(): Unit = {
    if (HP + 15 > 363) HP = 363
    else HP = HP + 15;
  }

  override def priseDeDegats(dammage: Int): Unit = {
    HP = HP - dammage;
  }


}

