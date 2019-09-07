package live.ipso.domain;

public class Dog {
   private String name;
   private String color;

   public String getName() {
      return name;
   }

   public String getColor() {
      return color;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setColor(String color) {
      this.color = color;
   }

   @Override
   public String toString() {
      return "Dog{" + "name='" + name + '\'' + ", color='" + color + '\'' + '}';
   }
}
