package live.ipso.springmvc4.domain;

public class Pet {
   private String name;
   private int id;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Override
   public String toString() {
      return "Pet{" + "name='" + name + '\'' + ", id='" + id + '\'' + '}';
   }
}
