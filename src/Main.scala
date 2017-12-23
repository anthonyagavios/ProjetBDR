import org.apache.spark.{SparkConf, SparkContext}
import org.jsoup.Jsoup
import Bestiaire._

// Import pour break les boucle while , for
import scala.util.control.Breaks
import scala.util.control.Breaks._

// Import pour graphX
import org.apache.spark._
import org.apache.spark.graphx._

// To make some of the examples work we will also need RDD
import org.apache.spark.rdd.RDD

import GestionCombat._
import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit = {
      def exo1(){
        var bestiaire = new ArrayBuffer[Bestiaire.creature]()
        val conf = new SparkConf().setAppName("blbl").setMaster("local[*]")
        val sc = new SparkContext(conf)
        sc.setLogLevel("WARN")


        // Creation du tableau de liens du bestiaire
        var page = new Bestiaire.RecuperationEtTraitementDonnée
        page.getBestiaire("http://paizo.com/pathfinderRPG/prd/bestiary/", "monsterIndex.html")
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary2/additionalMonsterIndex.html")
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary3/monsterIndex.html")
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary4/monsterIndex.html")
        page.getBestiaire("http://paizo.com/", "pathfinderRPG/prd/bestiary5/index.html")

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
            var tempSpell=page.getSpell(lienCreature)
            for (nomTemp <- tempSpell) {
              Creature.addspell(nomTemp)
            }
            bestiaire.append(Creature)
          }
        }
        println("Creation Bestiaire done")

        // Creation du tableau de liens de tout les spells
        for (url <- page.links) {
          page.getSpell(url)
        }

        // Affichage de tous les liens des spells
        //println(page.spells)

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
      }

    // Exo1
    //exo1()

    // Combat
    var combat =new Combat
    combat.premierCombat()
         //combat.deuxiemeCombat()


  }

}