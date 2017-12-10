import org.apache.spark.{SparkConf, SparkContext}
import org.jsoup.Jsoup

import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit = {
    /*
        var bestiaire = new ArrayBuffer[creature]()
        val conf = new SparkConf().setAppName("blbl").setMaster("local[*]")
        val sc = new SparkContext(conf)
        sc.setLogLevel("WARN")


        // Creation du tableau de liens du bestiaire
        var page = new RecuperationEtTraitementDonnée;
        page.getBestiaire("http://paizo.com/pathfinderRPG/prd/bestiary/", "monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary2/additionalMonsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary3/monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary4/monsterIndex.html");
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary5/index.html");

        // Affichage de tous les liens des creatures
        //println(page.links);

        // On creer toute les creatures du bestiare dans notre base de donnee
        for (lienCreature <- page.links) {

          // On extrait le nom de la creature de son url
          var nomCreature = lienCreature.split("#")

          // On recupere les liens des spells de la creature
          page.getSpell(lienCreature)

          if (nomCreature.size == 2) {

            // On construit la creature et on la nomme
            var Creature = new creature(nomCreature(1), lienCreature)

            // On ajoute les liens des spells que la creature possede
            var tempSpell=page.getSpell(lienCreature);
            for (nomTemp <- tempSpell) {
              Creature.addspell(nomTemp);
            }
            bestiaire.append(Creature)
          }
        }
        println("Creation creature done")

        // Creation du tableau de liens de tout les spells
        for (url <- page.links) {
          page.getSpell(url);
        }

        // Affichage de tous les liens des spells
        //println(page.spells);

        val rdd = sc.parallelize(bestiaire)
        val rddSpell = rdd.map(creature => creature.spells.toList -> creature.name)
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

    var gentil = new PartySolar(1, 2, 2, 5)
    var mechant = new PartyWyrm(1, 200, 10, 0, 0)

    while (!mechant.barbareOrc.isEmpty) {
      println(mechant.barbareOrc.size)
      if (!mechant.barbareOrc.isEmpty) {
        if (!gentil.solar.isEmpty) {
          for (sol <- gentil.solar) {
            sol.attaque("ranged", mechant, "babareOrc", 0)
          }
        }
      }
      if (!mechant.barbareOrc.isEmpty) {
        for (pla <- gentil.planetar) {
          pla.attaque("melee", mechant, "babareOrc", 0)
        }
      }
      if (!mechant.barbareOrc.isEmpty) {
        for (mov <- gentil.movanicDeva) {
          mov.attaque("melee", mechant, "babareOrc", 0)
        }
      }
      if (!mechant.barbareOrc.isEmpty) {
        for (ast <- gentil.astralDeva) {
          ast.attaque("melee", mechant, "babareOrc", 0)
        }
      }
      if (!gentil.solar.isEmpty) {
        for (drake <- mechant.greenGreatWyrmDragon) {
          // drake.attaque("melee", gentil, "solar", 0)
        }
      }
      if (!gentil.solar.isEmpty) {
        for (orc <- mechant.barbareOrc) {
          orc.attaque("melee", gentil, "solar", 0)
        }
      }
      if (!gentil.solar.isEmpty) {
        for (ang <- mechant.angelSlayer) {
          ang.attaque("melee", gentil, "solar", 0)
        }
      }
      if (!gentil.solar.isEmpty) {
        for (war <- mechant.warlord) {
          war.attaque("melee", gentil, "solar", 0)
        }
      }
      if (!gentil.solar.isEmpty) {
        for (worg <- mechant.worgsRider) {
          worg.attaque("melee", gentil, "solar", 0)
        }
      }

    }

  }

}