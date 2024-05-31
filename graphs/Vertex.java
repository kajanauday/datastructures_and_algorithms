import java.util.ArrayList;

public class Vertex {
   int data;
   ArrayList<Vertex> vertices = new ArrayList<>();

   public Vertex(int data) {
      this.data = data;
   }

   void addEdge(int data) {
      this.vertices.add(new Vertex(data));
   }

   public String getNeighbours() {
      StringBuilder sb = new StringBuilder("");
      if (vertices.size() == 0)
         sb.append("its a leaf node");
      for (Vertex node : vertices) {
         sb.append(node.data + "->");
      }
      sb.append("EOP");
      return sb.toString();
   }
}