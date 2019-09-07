package live.ipso.domain;

import java.util.Arrays;
import java.util.List;

public class User {
   private String user;
   private int age;
   private String[] hobbit;
   private Dog dog;
   private List<Dog> dogs;

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String[] getHobbit() {
      return hobbit;
   }

   public void setHobbit(String[] hobbit) {
      this.hobbit = hobbit;
   }

   public Dog getDog() {
      return dog;
   }

   public void setDog(Dog dog) {
      this.dog = dog;
   }

   public List<Dog> getDogs() {
      return dogs;
   }

   public void setDogs(List<Dog> dogs) {
      this.dogs = dogs;
   }

   @Override
   public String toString() {
      return "User{" + "user='" + user + '\'' + ", age=" + age + ", hobbit=" + Arrays.toString(hobbit) + ", dog=" + dog + ", dogs=" + dogs + '}';
   }
}
