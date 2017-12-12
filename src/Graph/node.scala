package Graph

import Bestiaire.creature
import org.apache.spark.graphx.VertexId

/**
  * Created by adrie on 11/12/2017.
  */
class node(var id: VertexId = 1L) extends Serializable {
  //Utile pour imprimer le graphe après
  override def toString: String = s"id : $id"
}