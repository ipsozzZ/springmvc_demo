package live.ipso.springmvc4.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Arrays;


public class User {

   @NotBlank(message = "名字不能为空")  // 不允许为空
   private String name;

   @NotNull(message = "年龄不能为空")
   @Max(value = 150, message = "请输入合法的年龄") // 设置年龄允许的最大值
   private int age;

   @Email(message = "请输入正确的邮箱") // 验证邮箱
   private String email;

   @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$") // 匹配一个正则表达式
   private String phone;

   private int gender;
   private String[] hobby;
   private Pet pet;

   public Pet getPet() {
      return pet;
   }

   public void setPet(Pet pet) {
      this.pet = pet;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public int getGender() {
      return gender;
   }

   public void setGender(int gender) {
      this.gender = gender;
   }

   public String[] getHobby() {
      return hobby;
   }

   public void setHobby(String[] hobby) {
      this.hobby = hobby;
   }

   @Override
   public String toString() {
      return "User{" + "name='" + name + '\'' + ", age=" + age + ", gender=" + gender + ", hobby=" + Arrays.toString(hobby) + ", pet=" + pet + '}';
   }
}
