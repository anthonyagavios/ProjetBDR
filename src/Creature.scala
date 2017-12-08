import scala.collection.mutable.ArrayBuffer;
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.{PairRDDFunctions, RDD}


class creature(val name: String, val link: String) extends Serializable {
    var spells = ArrayBuffer[String]()
    def addspell(spell: String): Unit = {
      spells += spell
    }
  def getNomSpell: Unit ={
    for(spe<-spells){
      var nomSpell = spe.toString.split("#");
      return nomSpell;
    }
  }
  }


