import org.apache.spark.{SparkConf, SparkContext}
import org.jsoup.Jsoup

// Import pour break les boucle while , for
import scala.util.control.Breaks
import scala.util.control.Breaks._

// Import pour graphX
import org.apache.spark._
import org.apache.spark.graphx._
// To make some of the examples work we will also need RDD
import org.apache.spark.rdd.RDD


import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit = {
    /*
        var bestiaire = new ArrayBuffer[Bestiaire.creature]()
        val conf = new SparkConf().setAppName("blbl").setMaster("local[*]")
        val sc = new SparkContext(conf)
        sc.setLogLevel("WARN")


        // Creation du tableau de liens du bestiaire
        var page = new Bestiaire.RecuperationEtTraitementDonnée;
        page.getBestiaire("http://paizo.com/pathfinderRPG/prd/bestiary/", "monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary2/additionalMonsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary3/monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary4/monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary5/index.html");

        // Affichage de tous les liens des creatures
        //println(page.links);

        // On creer toute les creatures du bestiare dans notre base de donnee
        for (lienCreature <- page.links) {

          // On extrait le nom de la Bestiaire.creature de son url
          var nomCreature = lienCreature.split("#")

          // On recupere les liens des spells de la Bestiaire.creature
          page.getSpell(lienCreature)

          if (nomCreature.size == 2) {

            // On construit la Bestiaire.creature et on la nomme
            var Creature = new Bestiaire.creature(nomCreature(1), lienCreature)

            // On ajoute les liens des spells que la Bestiaire.creature possede
            var tempSpell=page.getSpell(lienCreature);
            for (nomTemp <- tempSpell) {
              Creature.addspell(nomTemp);
            }
            bestiaire.append(Creature)
          }
        }
        println("Creation Bestiaire.creature done")

        // Creation du tableau de liens de tout les spells
        for (url <- page.links) {
          page.getSpell(url);
        }

        // Affichage de tous les liens des spells
        //println(page.spells);

        val rdd = sc.parallelize(bestiaire)
        val rddSpell = rdd.map(Bestiaire.creature => Bestiaire.creature.spells.toList -> Bestiaire.creature.name)
        val y = rddSpell.flatMap { case (a, b) => a.map(_ -> b) }
        val z = y.reduceByKey((x, y) => x + ";" + y)
        val sorted = z.collect().sortBy(pair => pair._1)
        println("Liste des spells")
        println("-------------------")
        sorted.foreach { case (a, b: String) => afficherCreature(a, b) }


      }

      def afficherCreature(m: String, n: String) = {
        println(m + " : ")
        val listeMonstre = n.split(";")
        if (listeMonstre == null) {
          println("    - " + n)
        } else {
          for (i <- 0 until listeMonstre.length) {
            println("    - " + listeMonstre(i))
          }
        }
        println("-----------------")
        */

    // Test de combat sans IA
    /*
    var gentil = new GestionCombat.PartySolar(1, 2, 2, 5)
    var mechant = new GestionCombat.PartyWyrm(1, 200, 10, 0, 0)
    var fin = false
    var tour = 1

    breakable {
      while (!fin) {
        //TODO
        // il faudra tenir compte de l'initiative des creatures pour les vrai combats

        for (drake <- mechant.greenGreatWyrmDragon) {
          if (!gentil.solar.isEmpty) {
            drake.attaque("melee", gentil, gentil.solar(0).name, 0)
          }
          else if (!gentil.planetar.isEmpty) {
            drake.attaque("melee", gentil, gentil.planetar(0).name, 0)
          }
          else if (!gentil.movanicDeva.isEmpty) {
            drake.attaque("melee", gentil, gentil.movanicDeva(0).name, 0)
          }
          else if (!gentil.astralDeva.isEmpty) {
            drake.attaque("melee", gentil, gentil.astralDeva(0).name, 0)
          } else Breaks

        }

        for (sol <- gentil.solar) {
          if (!mechant.greenGreatWyrmDragon.isEmpty) {
            sol.attaque("melee", mechant, mechant.greenGreatWyrmDragon(0).name, 0)
          }
          else if (!mechant.angelSlayer.isEmpty) {
            sol.attaque("melee", mechant, mechant.angelSlayer(0).name,0)
          }
          else if (!mechant.warlord.isEmpty) {
            sol.attaque("melee", mechant, mechant.warlord(0).name, 0)
          }
          else if (!mechant.barbareOrc.isEmpty) {
            sol.attaque("melee", mechant, mechant.barbareOrc(0).name, 0)
          }
          else if (!mechant.worgsRider.isEmpty) {
            sol.attaque("melee", mechant, mechant.worgsRider(0).name, 0)
          }else Breaks
        }

        for (pla <- gentil.planetar) {
          if (!mechant.greenGreatWyrmDragon.isEmpty) {
            pla.attaque("melee", mechant, mechant.greenGreatWyrmDragon(0).name, 0)
          }
          else if (!mechant.angelSlayer.isEmpty) {
            pla.attaque("melee", mechant, mechant.angelSlayer(0).name, 0)
          }
          else if (!mechant.warlord.isEmpty) {
            pla.attaque("melee", mechant, mechant.warlord(0).name, 0)
          }
          else if (!mechant.barbareOrc.isEmpty) {
            pla.attaque("melee", mechant, mechant.barbareOrc(0).name, 0)
          }
          else if (!mechant.worgsRider.isEmpty) {
            pla.attaque("melee", mechant, mechant.worgsRider(0).name, 0)
          } else Breaks
        }

        for (mov <- gentil.movanicDeva) {
          if (!mechant.greenGreatWyrmDragon.isEmpty) {
            mov.attaque("melee", mechant, mechant.greenGreatWyrmDragon(0).name, 0)
          }
          else if (!mechant.angelSlayer.isEmpty) {
            mov.attaque("melee", mechant, mechant.angelSlayer(0).name, 0)
          }
          else if (!mechant.warlord.isEmpty) {
            mov.attaque("melee", mechant, mechant.warlord(0).name, 0)
          }
          else if (!mechant.barbareOrc.isEmpty) {
            mov.attaque("melee", mechant, mechant.barbareOrc(0).name, 0)
          }
          else if (!mechant.worgsRider.isEmpty) {
            mov.attaque("melee", mechant, mechant.worgsRider(0).name, 0)
          } else Breaks
        }

        for (ast <- gentil.astralDeva) {
          if (!mechant.greenGreatWyrmDragon.isEmpty) {
            ast.attaque("melee", mechant, mechant.greenGreatWyrmDragon(0).name, 0)
          }
          else if (!mechant.angelSlayer.isEmpty) {
            ast.attaque("melee", mechant, mechant.angelSlayer(0).name, 0)
          }
          else if (!mechant.warlord.isEmpty) {
            ast.attaque("melee", mechant, mechant.warlord(0).name, 0)
          }
          else if (!mechant.barbareOrc.isEmpty) {
            ast.attaque("melee", mechant, mechant.barbareOrc(0).name, 0)
          }
          else if (!mechant.worgsRider.isEmpty) {
            ast.attaque("melee", mechant, mechant.worgsRider(0).name, 0)
          } else Breaks
        }

        for (angS <- mechant.angelSlayer) {
          if (!gentil.solar.isEmpty) {
            angS.attaque("melee", gentil, gentil.solar(0).name, 0)
          }
          else if (!gentil.planetar.isEmpty) {
            angS.attaque("melee", gentil, gentil.planetar(0).name, 0)
          }
          else if (!gentil.movanicDeva.isEmpty) {
            angS.attaque("melee", gentil, gentil.movanicDeva(0).name, 0)
          }
          else if (!gentil.astralDeva.isEmpty) {
            angS.attaque("melee", gentil, gentil.astralDeva(0).name, 0)
          } else Breaks

        }

        for (warL <- mechant.warlord) {
          if (!gentil.solar.isEmpty) {
            warL.attaque("melee", gentil, gentil.solar(0).name, 0)
          }
          else if (!gentil.planetar.isEmpty) {
            warL.attaque("melee", gentil, gentil.planetar(0).name, 0)
          }
          else if (!gentil.movanicDeva.isEmpty) {
            warL.attaque("melee", gentil, gentil.movanicDeva(0).name, 0)
          }
          else if (!gentil.astralDeva.isEmpty) {
            warL.attaque("melee", gentil, gentil.astralDeva(0).name, 0)
          } else Breaks

        }

        for (barOrc <- mechant.barbareOrc) {
          if (!gentil.solar.isEmpty) {
            barOrc.attaque("melee", gentil, gentil.solar(0).name, 0)
          }
          else if (!gentil.planetar.isEmpty) {
            barOrc.attaque("melee", gentil, gentil.planetar(0).name, 0)
          }
          else if (!gentil.movanicDeva.isEmpty) {
            barOrc.attaque("melee", gentil, gentil.movanicDeva(0).name, 0)
          }
          else if (!gentil.astralDeva.isEmpty) {
            barOrc.attaque("melee", gentil, gentil.astralDeva(0).name, 0)
          } else Breaks

        }

        for (worgsR <- mechant.worgsRider) {
          if (!gentil.solar.isEmpty) {
            worgsR.attaque("melee", gentil, gentil.solar(0).name, 0)
          }
          else if (!gentil.planetar.isEmpty) {
            worgsR.attaque("melee", gentil, gentil.planetar(0).name, 0)
          }
          else if (!gentil.movanicDeva.isEmpty) {
            worgsR.attaque("melee", gentil, gentil.movanicDeva(0).name, 0)
          }
          else if (!gentil.astralDeva.isEmpty) {
            worgsR.attaque("melee", gentil, gentil.astralDeva(0).name, 0)
          } else Breaks

        }

        if (gentil.solar.isEmpty) {
          if (gentil.planetar.isEmpty) {
            if (gentil.movanicDeva.isEmpty) {
              if (gentil.astralDeva.isEmpty) {
                println("Les gentils ont perdu")
                fin = true
              }
            }
          }
        }

        if (mechant.greenGreatWyrmDragon.isEmpty) {
          if (mechant.angelSlayer.isEmpty) {
            if (mechant.warlord.isEmpty) {
              if (mechant.barbareOrc.isEmpty) {
                if (mechant.worgsRider.isEmpty) {
                  println("Les gentils ont gagnés")
                  fin = true
                }
              }
            }
          }
        }

        println("tour :" + tour)
        println("dragon vide ? :"+mechant.greenGreatWyrmDragon.isEmpty)
        println("nombre de solar :" + gentil.solar.size + " nombre de planetar :" + gentil.planetar.size + " nombre de mova :" + gentil.movanicDeva.size + " nombre d'astralDeva :" + gentil.astralDeva.size)
        println("nombre de dragon :" + mechant.greenGreatWyrmDragon.size + " nombre de angelSlayer :" + mechant.angelSlayer.size + " nombre de warlord :" + mechant.warlord.size + " nombre de barbareOrc :" + mechant.barbareOrc.size + " nombre de wargsRider :" + mechant.worgsRider.size)
        tour = tour + 1
        if (tour > 120) break()
      }

    }
*/

    // Test graphX

  }

}