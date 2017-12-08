import org.jsoup.Jsoup
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class RecuperationEtTraitementDonnée {
  var links = new ArrayBuffer[String]()


  // Fonction qui retourne un tableau de liens url correspondant aux spells d'une creature
  def getSpell(url: String): ArrayBuffer[String] = {

    var spells = new ArrayBuffer[String]()
    // Les liens url des creature traiter dans getBestiaire
    // Pour la phase de devellopement et de test for(url<-links){

    try {
      var conn = Jsoup.connect(url)
      var doc = conn.get()

      // On extrait le nom de la creature de son url
      var nom = url.split("#")

      if (nom.length == 2) {
        // On décompose la page via le char <h1
        var pageSplit = doc.toString.split("<h1")

        // On defini un pattern qui permettra d'extraire les liens url des spells
        var pattern = "<a href.*</a>".r

        for (value <- pageSplit) {

          if (value.contains("id=\"" + nom(1) + "\"")) {
            // On extrait les donne correspondant au pattern ici les lien url
            var liens = pattern.findAllIn(value)

            for (linkSpell <- liens) {
              // On split car la variable liens contient plusieurs lien par ligne (du coup il ne sont pas valable)
              var tab = linkSpell.split(",")

              for (linkTraiter <- tab) {
                var link = "http://paizo.com" + linkTraiter.split("\"")(1)
                if (link.contains(("spells"))) {
                  // On ajoute les liens dans le tableau de spells
                  //println(nom(1) + " " + link)
                  spells.append(link)
                }
              }
            }
          }

        }

      }
    }
    catch {
      case e: Exception => None
    }

    return spells;


  }


  // Cette fonction permet d'extraire de la page web le bestiaire et les liens url pointant vers la description de chaque creature
  def getBestiaire(urlToCrawl: String, urlOption: String): Unit = {
    try {
      var conn = Jsoup.connect(urlToCrawl + urlOption)
      var doc = conn.get()

      // On extrait de la variable doc les blocs du code html qui sont defini par l'ID "monster-index-wrapper"
      var content = doc.getElementById("monster-index-wrapper")

      // On extrait de la variable content via select les lignes href dans le code html
      var elements = content.select("a[href]")


      for (a <- 0 to elements.size() - 1) {

        // On ajoute le liens de la creature dans un tableau qui constitura notre bestiaire
        links.append(urlToCrawl + "/" + elements.get(a).attr("href"));
      }
    }
    catch {
      case e: Exception => None
    }

  }

}
