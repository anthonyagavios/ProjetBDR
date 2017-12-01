import org.apache.spark.{SparkConf, SparkContext}
import org.jsoup.Jsoup

import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit = {
    var bestiaire=new ArrayBuffer[creature]()
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
    println(page.links);

    // On creer toute les creatures du bestiare dans notre base de donnee
    for (lienCreature <- page.links) {
      // On extrait le nom de la creature de son url
      var nomCreature = lienCreature.split("#")
      // On recupere les liens des spells de la creature
      page.getSpell(lienCreature)
      if (nomCreature.size == 2) {
        // On construit la creature et on la nomme
        var Creature = new creature(nomCreature(1),lienCreature)
        // On ajoute les liens des spells que la creature possede
        for (nomTemp <- page.spells) {
          Creature.addspell(nomTemp);
        }
        bestiaire.append(Creature)
      }
    }
    println("Creation creature done")

    // On affiche le bestiaire
    println(bestiaire)


    // Creation du tableau de liens de tout les spells
    /*for (url <- page.links) {
      page.getSpell(url);
    }*/

    // Affichage de tous les liens des spells
    //println(page.spells);


    /*val rdd = sc.parallelize(Seq(solar, test, test2))
  val blbl = rdd.map(creature => creature.spells.toList -> creature.name)
  val y = blbl.flatMap { case (a, b) => a.map(_ -> b) }
  val z = y.reduceByKey((x, y) => x + ";" + y)
  val sorted = z.collect().sortBy(pair => pair._1)
  println("Liste des spells")
  println("-------------------")
  sorted.foreach { case (a, b: String) => afficherCreature(a, b) }*/
  }

}