# springmvc

## # springmvc执行流程

* 用户发送请求至前端控制器DispatcherServlet
* DispatcherServlet收到请求调用HandlerMapping处理器映射器
* 处理器映射器根据请求url找到具体的处理器，生成处理器对象及处理器拦截器(如果有则生成)，一并返回给DispatcherServlet
* DispatcherServlet通过HandlerAdapter处理器适配器调用处理器
* 执行处理器(Controller, 也叫后端控制器)
* Controller执行完返回ModelAndView
* HandlerAdapter将Controller执行结果ModelAndView返回给DispatcherServlet
* DispatcherServlet将ModelAndView传给ViewReslover视图解析器
* ViewReslover解析后返回具体的view
* DispatcherServlet对View进行渲染视图(即将模型数据填至视图中)
* DispatcherServlet响应用户

### 组件说明

DispatcherServlet: 前端控制器，用户请求到达前端控制器，它就相当于mvc中的c。DispatcherServlet是整个流程控制的中心，由它调用其它组件处理用户请求（人工编写）

HandlerMapping: 处理器映射器，负责根据用户请求的url找到Handler处理器(要处理的方法)，springmvc提供了不同的映射器来实现不同的映射方式，它会把找到的映射返回给DispatcherServlet(前端控制器)，将@Controller注解的类中标有@RequestMapping注解的方法进行映射

HandlerAdapter: 处理器适配器，找到对应处理器进行执行

Handler: 后端控制器，在DispatcherServlet的控制下Handler对具体的用户请求进行处理。（用户请求处理类(即数据逻辑处理)，需要编写）

ViewReslover: 视图解析器，负责将处理结果生成View视图

View: springmvc框架提供类很对View类型的支持，包括：jstlView(jsp)、freemarkerView、pdfView等，我们常用的视图是jsp。（我们需要编写）

## # 视图解析器的前后缀配置

在配置文件中添加如下代码：

```
<!-- 视图解析器的前后缀配置 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  <property name="prefix" value="/WEB-INF/"/>
  <property name="suffix" value=".jsp" />
</bean>

MyController.java类：

@Controller
public class MyController {

   @RequestMapping("/first.action")
   public ModelAndView show(){

      /* 创建ModelAndView用来存储数据和视图 */
      ModelAndView modelAndView = new ModelAndView();

      /* 设置数据到模型中 */
      modelAndView.addObject("name","ipso-spring-mvc");

      /* 设置视图jsp */
      modelAndView.setViewName("result1"); // 组合后 "/WEB-INF/result1.jsp"
      return modelAndView;

   }
}

```

## #接收参数

1. 使用传统的request对象接收参数

2. 不使用request对象接收简单的类型参数

3. 以一个javaBean对象接收

4. 数组接收数据

5. List集合接收数据

6. 包装类接收参数

7. 自定义参数绑定

### 自定义转换器demo

```

# 案列是将传过来的参数由原来的String类型转换成date类型，值得注意的是springmvc已经为我们定义了很多转换器，只有像日期# 这种特殊情况才有可能需要我们重新自定义转换器。

/**
 * converter类，实现String --> date类的转换
 * 自定义参数类型转换器，由"yyyy-MM-dd"格式的string转换为date日期,可以
 * springmvc默认接收的是"yyyy/MM/dd"才能转换成date类型，其它格式会报错
 * @author ipso
 * @date   2019-09-05
 */
public class DateConverter implements Converter<String, Date> {
   @Override
   public Date convert(String s) {
      if (s != null){
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
         try {
            return simpleDateFormat.parse(s);
         } catch (ParseException e) {
            e.printStackTrace();
         }
      }
      return null;
   }
}

# springmvc.xml中的配置

<!-- 将自定义的转换器bean在该位置集中引入 -->
<bean id="dateConverter" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
    <property name="converters">
        <list>
            <!-- 自定义的转换器列表 -->
            <bean class="live.ipso.springmvc2.converter.DateConverter"></bean>
        </list>
    </property>
</bean>

<!-- 注解驱动(相当于给转换器安装驱动) -->
<mvc:annotation-driven conversion-service="dateConverter" />


# 注：做好以上步骤即可使用，但是注意的是使用以上方法配置完后自定义的转换器后，默认的相同类型的转换器将不再生效，使用默# 认格式传值将会报错。

```

### post请求中文参数乱码

form表单默认的get方式提交的数据支持中文格式，但是post方式提交的数据默认不支持中文格式。

**解决办法**

```

# 在web.xml配置文件中添加过滤器，在过滤器中可以设置参数编码格式

<!-- 处理编码的过滤器（解决post请求乱码问题） -->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>

    <!-- 设置编码 -->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

```

### @RequestMapping

**Value属性**

用来设置请求路径，只是一个字符串数组，可以设置多个路径访问同一个方法。

```
# 两个路径都可以访问该方法
@RequestMapping(value = {"testrequestmapping1", "testrequestmapping2"})
public ModelAndView testRequest(){
  return new ModelAndView();
}

```

**method属性**

RequestMapping的method属性，单个方式的时候就不用写成集合的形式，默认是所有方式均可以接收。

```

@RequestMapping(value = "testmethod", method = {RequestMethod.GET, RequestMethod.POST})
public ModelAndView testmethod(String user){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

**params属性**

RequestMapping的params属性，必须设置对应的请求参数和请求值才能访问到对应的内容

```

/**
 * RequestMapping的params属性，设定请求参数中必须具备的参数，否侧无法请求当前方法
 * @param user 参数user
 * @return modelAndView视图模型
 */
@RequestMapping(value = "testparams", params = {"name=ipso", "age=20"})
public ModelAndView testparams(String user){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

**headers属性**

发送的请求头必须要与设置的请求头相同时，才能访问到对应的方法

```

/**
 * RequestMapping的header属性，发送的请求头必须要与设置的请求头相同时，才能访问到对应的方法
 * @param user 参数user
 * @return modelAndView视图模型
 */
@RequestMapping(value = "testheaders",
headers = {"Host=localhost:8080", "Referer=http://localhost:8080/requestMapping.jsp"})
public ModelAndView testheaders(String user){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

# 其中headers = {"Host=localhost:8080", "Referer=http://localhost:8080/requestMapping.jsp"})
# 这里要求请求的头信息中Host与Referer的值必须与设置的值相同时才能访问到该方法

```

**ant风格地址**

请求路径的一种匹配方法

通配符：

?:  一个问号匹配一个字符(可以用任意多个?号匹配任意多个字符，但是字符数只会与?号数对应)

```

# 下面的RequestMapping表示在地址"我的域名/testheaders/任意两个字符"，都能方法到下面这个注解标记的方法，字符数量随着# 问号数量变换
@RequestMapping(value = "testheaders/??")  // testheaders/ + 任意两个字符

```

*:  匹配任意字符

```

# 下面的RequestMapping表示在地址"我的域名/testheaders/任意两个字符"，都能方法到下面这个注解标记的方法，字符数量随着# 问号数量变换
@RequestMapping(value = "testheaders/*/ipso") // testheaders/ + 任意字符 + /ipso

```

**: 匹配多重路径

```

/**
 * RequestMapping的ant风格地址,请求路径的一种匹配方法
 * @param user 参数user
 * @return modelAndView视图模型
 */
// @RequestMapping(value = "testheaders/??") // testheaders/ + 任意两个字符
// @RequestMapping(value = "testheaders/*/ipso") // testheaders/ + 任意字符 + /ipso
@RequestMapping(value = "testheaders/**/ipso") // testheaders/ + 多重路径(.../*/*...) + /ipso
public ModelAndView testant(String user){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

### @PathVariable

用来接收RestFul风格的参数

**rest风格**

资源定位及资源操作的风格，它**不是协议可以遵循也可以不遵循**。

REST即Representational State Transfer（资源）表现层状态转化; 用URL定位资源，用HTTP描述操作; 是目前最流行的一种互联网软件架构; 它结构清晰、符合标准、易于理解、扩展方便，所以正在得到越来越多的网站的采用; 使用POST,DELETE,PUT,GET分别对应CURD(传统的CURD基本都是：查询用get，增删改用post); Spring3.0开始支持REST风格的请求。

**用URL定位资源，用HTTP描述操作**

资源定位不用说与传统类似。什么是描述操作：rest风格请求使用POST,DELETE,PUT,GET分别对应CURD

**rest风格url及参数**

我的通俗理解：

与传统url做个对比
传统url：主机域名/资源路径/参数名=参数值  (传统的CURD基本都是：查询用get，增删改用post)
rest风参数：主机域名/资源路径/参数值 (rest风格的CURD是：使用POST,DELETE,PUT,GET分别对应CURD)

```

# 实例

/**
 * 接收rest风格参数
 * @param user 用户名
 * @return modelAndView视图模型
 */
@RequestMapping(value = "rest/{user}")
public ModelAndView rest(@PathVariable String user){

   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", "rest=====" + user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

**注意**

默认form表单是不支持PUT和DELETE请求的，但是在spring3.0后我们可以通过在web.xml中配置过滤器来让表单支持PUT请求与DELETE请求。

```

# web.xml
<!-- 处理表单请求方式的过滤器(解决表单不能发送PUT、DELETE请求的问题) -->
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

# form表单
<form action="${pageContext.request.contextPath}/testmethod" method="post">
  <input type="hidden" name="_method" value="put"> <%-- value值：get、put、post、delete等 --%>
  <input type="test" name="user"><br>
  <input type="submit" value="提交">
</form>

# 测试acion
/**
 * 测试接收form表单提交的put、delete数据
 * @param user 用户名
 * @return modelAndView视图模型
 */
@RequestMapping(value = "rest/{user}", method = {RequestMethod.PUT, RequestMethod.DELETE})
public ModelAndView testRestMethod(@PathVariable String user){

   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", "rest=====" + user);
   modelAndView.setViewName("redirect:secondMethod.action");
   return modelAndView;
}

/**
 * 跳转视图
 * @return modelAndView视图模型
 */
@RequestMapping(value = "secondMethod")
public ModelAndView secondMethod(String user){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", user);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

# 特别注意：modelAndView.setViewName("redirct:resultMapping");为什么在设置视图的时候要用redirect而不是forward呢,tomcat8开始jsp页默认只接受get、post、header方式的提交，所以我们使用redirect跳转到action,再跳转视图。

```

**HiddenHttpMethodFilter源码**：

可以在ctrl+单击，进入源码，查看源码

### @RequestHeader获取头信息

用来获取请求头信息

```

/**
 * 测试头信息
 * @return modelAndView视图模型
 */
@RequestMapping(value = "testHeader")
public ModelAndView testHeader(@RequestHeader("Cookie") String cookie, @RequestHeader("Host") String host){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", "cookie======" + cookie + "  host======" + host);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

### @CookieValue获取头信息中的cookie

用来获取头信息中单个Cookie的值

```

/**
 * 测试头信息
 * @return modelAndView视图模型
 */
@RequestMapping(value = "testCookie")
public ModelAndView testCookie(@CookieValue("JSESSIONID") String cookie){
   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("user", "cookie======" + cookie);
   modelAndView.setViewName("resultMapping");
   return modelAndView;
}

```

## # 页面传值（action发送数据给jsp）

### ModelAndView

存储在request域中, 需要实例化ModelAndView对象，需要return ModelAndView

```

# Controller action接收用户数据并并存储在ModelAndView中传递给jsp

/**
 * ModelAndView方式传值
 * @param data 要传递的数据
 * @return modelAndView对象
 */
@RequestMapping("testModelAndView")
public ModelAndView testModelAndView(String data){

   ModelAndView modelAndView = new ModelAndView();
   modelAndView.addObject("data", data);
   modelAndView.setViewName("/resultPage/result");
   return modelAndView;
}

# jsp接收数据

<h1>result: ${requestScope.data}</h1>

# 或写成 <h1>result: ${data}</h1>

```

### Model与ModelMap

Model和ModelMap的实例都是spring mvc框架来自动创建并作为控制器方法参数传入的，用户无需自己创建；可以简单地将一个Model的实现类理解成一个Map; Model是request级别的模型数据；Model是一个接口，其实现类为：ExtendedModelMap, ExtendedModelMap继承了ModelMap类

**Model**

也是存储在request域中,不用实例化Model，也不用return Model。

```

# 注：Model和ModelAndView的唯一不同点是，ModelAndView中加了一个View，所以以下方法在ModelAndView中同样用法

# Controller action接收用户数据并并存储在Model中传递给jsp

/**
 * Model方式传值（与ModelMap方式用法一样），Model的addAttribute()可以直接传key-value，也可以传一个对象可以将对象的类名称
 * 变小写字母后作为key值使用。可以用Model的asMap()方法显示key-value来验证是否是将类名小写作为类key
 *
 * 这里使用Map map也可以传值，并且也是默认的参数(只是不常用，常用的是Model和ModelAndView)
 * @param model springmvc的默认参数，不需要请求方人为显示提供
 * @param data 传递的数据
 * @return 视图路径字符串
 */
@RequestMapping("testModel")
public String testModel(Model model, String data){

  /* 传 key-value */
  model.addAttribute("data", data);

  /* 传 Goods对象 */
  Goods myGoods = new Goods();
  myGoods.setName("apple");
  myGoods.setPrice(5000.00);
  model.addAttribute(myGoods);
  System.out.println(model.asMap());

  /* addAllAttributes方法 */
  // 将map中的内容复制到Model中，如果由相同的key则覆盖原key值对应的内容
  HashMap<String, Object> hashMap = new HashMap<>();
  hashMap.put("name", "ipso1");
  hashMap.put("age", 22);
  model.addAllAttributes(hashMap);
  System.out.println(model.asMap()); // 直接打印在控制台

  /* addAllAttributes方法 传collection(集合，即list或者set) */
  // 以集合中数据的类型作为key，将所提供的collection中的所有属性复制到这个map中，如果同类型则会存在覆盖现象
  ArrayList<Object> arrayList = new ArrayList<>();
  arrayList.add("ipso222");
  arrayList.add(100);
  arrayList.add("ipso333"); // 覆盖第一个String类型的数据
  model.addAllAttributes(arrayList);
  System.out.println(model.asMap());

  /* model的containsAttribute方法查看是否包含key值 */
  System.out.println(model.containsAttribute("age"));

}

# 所有打印结果如下

[2019-09-06 11:04:58,928] Artifact springMVC3:war exploded: Artifact is deployed successfully
[2019-09-06 11:04:58,928] Artifact springMVC3:war exploded: Deploy took 4,606 milliseconds
{data=Model, goods=Goods{name='apple', price=5000.0}}
{data=Model, goods=Goods{name='apple', price=5000.0}, name=ipso1, age=22}
{data=Model, goods=Goods{name='apple', price=5000.0}, name=ipso1, age=22, string=ipso333, integer=100}
true

# jsp接收数据

<h1>result: ${requestScope.data}</h1>

# 或写成 <h1>result: ${data}</h1>

```

**ModelMap**

ModelMap主要用于传递控制方法处理数据到结果页; 也就是说我们把结果页面上需要的数据放到ModelMap对象中即可; request对象的setAttribute方法的作用：用来在一个请求过程中传递处理的数据。使用方法于Model一致

### @SessionAttributes注解与@SessionAttribute注解

**@SessionAttributes注解**：

将模型中的某个属性暂存到HttpSession中，以便多个请求之间共享该属性，注解为类注解

@SessionAttributes(value = ):将某个属性放到session域中, value关键字可以省略不写。

@SessionAttributes(value = {value1, value2, ...})，可以存多个

@SessionAttributes(type = ): 将某类型的所有属性存到session域中

```

# 用法

@Controller
@SessionAttributes(value = "u_name") // 将model中key为name的属性转存到session中,value可以省略不写
// @SessionAttributes(types = String.class) // 将model中String类型的属性全部转存到session中，其它类型也一样。
public class MyController {}

# 调用

<h1>session==: ${sessionScope.u_name}</h1>

# 注：如果不写明是sessionScope域的话将会在所有域中扫描，直到找到为止，requestScope也一样

```

**@SessionAttribute注解**：

与@SessionAttribute注解与@SessionAttributes注解的不同之处是：@SessionAttribute注解是取session中的属性，是取，并且是在方法的参数上用此注解。而@SessionAttributes注解是将model中的属性转存到session中, 是存。并且是在类上使用此注解。

```

# 在类

@Controller
@SessionAttributes({"u_name", "age"}) // 将model中key为name的属性转存到session中
// @SessionAttributes(types = String.class) // 将model中String类型的属性全部转存到session中
public class MyController {

  /**
   * 通过类上的@SessionAttributes注解存key为u_name的属性到session中
   * @param model model
   * @return 视图路径
   */
  @RequestMapping("testSession")
  public String testSession(Model model){

     /* 将数据存储到request中 */
     model.addAttribute("u_name", "ipso");
     model.addAttribute("age", 20);
     return "/resultPage/result1";
  }

  /**
   * 从session中取key为u_name的属性
   * @param model model
   * @param u_name 取出来的值赋给u_name
   * @return 视图路径
   */
  @RequestMapping("testSession2")
  public String testSession2(Model model, @SessionAttribute("u_name") String u_name){
     model.addAttribute("u_name", u_name);
     return "/resultPage/result2";
  }

}

# 在jsp

<h1>session==: ${sessionScope.u_name}</h1>
<h1>session==: ${sessionScope.age}</h1>


```

### @ModelAttribute修饰参数

**回顾**：

```

# 在Controller

@Controller
@SessionAttributes({"u_name", "age"}) // 将model中key为name的属性转存到session中
// @SessionAttributes(types = String.class) // 将model中String类型的属性全部转存到session中
public class MyController {

  /**
   * 当使用模型接收参数时，使用的模型会自动存入model模型中
   * @param myGoods Goods模型
   * @param model   model模型，默认自带模型
   * @return 视图路径
   */
  @RequestMapping("testModelAttribute")
  public String testModelAttribute(Goods myGoods, Model model){
     System.out.println(myGoods);
     System.out.println(model.asMap());
     return "/resultPage/result3";
  }

}

# 在request jsp

<h3>测试@ModelAttribute</h3>
<form action="${pageContext.request.contextPath}/testModelAttribute">
  商品名：   <input type="text" name="name"> <br>
  商品价格： <input type="text" name="price"> <br>
  <input type="submit" value="提交">
</form>

# Goods模型类，在domian

public class Goods {
   private String name;
   private double price;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "Goods{" + "name='" + name + '\'' + ", price=" + price + '}';
   }
}

# 在result jsp requestScope可以不写，goods为类小写后被用来作为key存入model

<h1>result==: ${requestScope.goods.name}</h1>
<h1>result==: ${goods.price}</h1>


# 综上：当使用模型接收参数时，使用的模型也会自动传入model模型，并且模型类名的小写形式作为key值。当然原来的模型也还可以使用，只是存入model的话可以再存其它数据

```

**现在来看看@ModelAttribute修饰参数**：

修饰参数时的作用：上面的例子中我们知道，当我们带Model这个参数时，其它的模型参数就会自动以模型名的小写形式作为key将模型存入model模型中，如果要改变存入模型的key值，就可以使用@ModelAttribute(这里是自定义key)来修饰用来接收参数的模型。如修改上例中的代码为Controller的public String testModelAttribute(@ModelAttribute("myGoods") Goods myGoods, Model model)，这样Goods模型存在model模型中的key就被重新定义为myGoods了。


**现在来看看@ModelAttribute修饰方法**：

当某个方法被@ModelAttribute修饰后，该方法在该源文件中，将在所有@RequestMapping之前执行

```

/**
 * 假设当前用户请求该方法
 * 当使用模型接收参数时，使用的模型会自动存入model模型中
 * @param myGoods Goods模型
 * @param model   model模型，默认自带模型
 * @return 视图路径
 */
@RequestMapping("testModelAttribute")
public String testModelAttribute(@ModelAttribute("myGoods") Goods myGoods, Model model){
   System.out.println(myGoods);
   System.out.println(model.asMap());
   return "/resultPage/result3";
}


/**
 * 执行上面方法前先执行该方法，所以上面方法执行时Model中已经存在user属性和pass属性，当然这两个属性也可以被覆盖
 * 该方法将在所有被@RequestMapping修饰的方法前执行，他的作用是可以用来提前给model做一些赋值
 * @param model Model模型
 */
@ModelAttribute
public void testModelAttribute1(Model model){
   model.addAttribute("user", "root");
   model.addAttribute("pass", "root");
}

```

**@ModelAttribute注意点**:

1. 在@ModelAttribute修饰的方法执行时会自动将已有的session存入model中

2. 之前说过在@ModelAttribute修饰的方法存属性时，如果在后续执行@RequestMapping时出现key值重复，则会覆盖掉该key值的属性，但是如果该key值对应存的是模型数据且存的不是session域时，key值覆盖并不是完全覆盖，比如模型类有三个属性，我在@ModelAttribute修饰的方法中给三个属性都赋值，并存入了model中，当在@RequestMapping标记的控制器中接收用户的参数使用同一个模型，并且用户只传类两个属性的值，也使用了Model模型存该模型，模型名也没有改变(使用默认的将模型名小写形式作为key)，所以在@ModelAttribute修饰的方法中存的模型传值的两个属性将被覆盖，而没有传值的那个属性将继续使用原来在@ModelAttribute修饰的方法中设定的值，但是注意的是如果在在@ModelAttribute修饰的方法中存的是session域的话，模型的所有属性都将被替换，没有传的属性使用null代替。

## # form标签与验证

### mvc:view-controller 从jsp跳转到jsp

我们知道jsp点击跳转链接时，springmvc会根据url去@Controller修饰的类中找@RequestMapping修饰的方法,如果找到方法则执行方法中的代码，再return到对应的结果视图(jsp页面)，如果找不到方法就会去扫面springmvc.xml配置文件(名字不一定是springmvc.xml,这是自定义的)中的
```<mvc:view-controller path="" view-name="" />``` 有没有配置，都没有就弹出错误页。

利用以上的原理，我们配置mvc:view-controller就可以实现从jsp跳转到jsp页了,其中path请求的路径，view-name是要跳转的view视图jsp

**特别注意的点**：如果使用了在配置文件中使用了mvc:view-controller，则默认springmvc将不再扫描Controller中的RequestMapping,即在Controller中的地址映射将不再起作用，并且如果在配置文件中配置了视图解析器的前后缀，默认也不会再起作用，解决方法是在配置文件中添加
```<mvc:annotation-driven />```就可以解决上面的问题了，此处还有个注意的点是，如果配置了视图解析器的前后缀并且也解决了上述问题，则
```mvc:view-controller```的view-name属性(跳转视图的路径)springmvc也会加上配置好的视图解析器前后缀。

**原因**：如果springmvc.xml中什么都不配置时：springmvc会默认自动注册三个Bean(即：RequestMappingHandlerMapping、RequestMappingHandlerAdapter、ExceptionHandlerExceptionResolver)，但是如果使用mvc:view-controller就会导致springmvc不加载这三个Bean中的RequestMappingHandlerMapping，所以就不会扫描RequestMapping修饰的方法。可以用```<mvc:annotation-driven />```解决，它让springmvc再注册这三个Bean。

### mvc:annotation-driven的作用(开发中一般都会加上)

```<mvc:annotation-driven />```1.是一种简写形式；2.会自动默认自动注册三个Bean(即：RequestMappingHandlerMapping、RequestMappingHandlerAdapter、ExceptionHandlerExceptionResolver)；3.提供了：数据绑定支持；@NumberFormatannotation支持；@DateTimeFormat支持；@Valid支持，读写XML的支持(JAXB)；读写json格式的支持(jackson)

**注意**：

在以后使用类似```<mvc:... />```都可以加上```<mvc:annotation-driven />```

## # springmvc中的form标签

**简介**：

在使用springmvc的时候我们可以使用spring封装的一系列表单标签。这些标签都可以访问到ModelMap中的内容

**作用**：

第一是它会自动的绑定来自Model中的一个属性值到当前form对应的实体对象；第二是它支持我们在提交表单的时候使用除get和post外的其它方法进行提交，包括DELETE和PUT。

**使用场景**：

需要使用form表单展示数据时，才使用spring封装的form。

```

# 基本用法实例

# request jsp
<a href="${pageContext.request.contextPath}/update/1" target="_blank">更新用户</a>

# domain user
public class User {
   private String name;
   private int age;

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

   @Override
   public String toString() {
      return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
   }
}

# Controller action
@Controller
public class MyController {

   @RequestMapping("update/{id}")
   public String update(@PathVariable Integer id, Model model){
      System.out.println(id);
      User user = new User();
      user.setName("ipso1");
      user.setAge(20);
      model.addAttribute("user", user);
      return "result";
   }
}

# result jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<html>
<head>
    <title>Result</title>
</head>
<body>
<div style="width: 700px; margin: auto; padding-top: 20px; padding-bottom: 40px">
    <h1>Result</h1>
    <%-- 如果不写modelAttribute="user"，默认到model中找的是modelAttribute="command",如果没有就报错 --%>
    <fm:form modelAttribute="user">
        <fm:input path="name"/>
        <fm:input path="age"/>
    </fm:form>
</div>
</body>
</html>

```

**radiobutton单选框、checkboxs多选框、select下拉列表**：

```java

# 基本用法实例

# request jsp
<a href="${pageContext.request.contextPath}/update/1" target="_blank">更新用户</a>

# User javaBean
public class User {
   private String name;
   private int age;
   private int gender;
   private String[] hobby;
   private Pet pet;

   getter and setter

   tostring()
}

# Pet javaBean
public class Pet {
   private String name;
   private int id;

   getter and setter

   tostring()
}

# Controller
@Controller
public class MyController {

   @RequestMapping("update/{id}")
   public String update(@PathVariable Integer id, Model model){
      System.out.println(id);

      // 所有爱好列表
      ArrayList<Object> hobbyList = new ArrayList<>();
      hobbyList.add("篮球");
      hobbyList.add("足球");
      hobbyList.add("棒球");
      model.addAttribute("allhobby", hobbyList);

      // 宠物列表
      ArrayList<Object> petList = new ArrayList<>();
      Pet pet1 = new Pet();
      pet1.setId(1);
      pet1.setName("dog");

      Pet pet2 = new Pet();
      pet2.setId(2);
      pet2.setName("pig");

      Pet pet3 = new Pet();
      pet3.setId(3);
      pet3.setName("cat");

      petList.add(pet1);
      petList.add(pet2);
      petList.add(pet3);

      model.addAttribute("petList", petList);

      // 用户自己的信息
      User user = new User();
      user.setName("ipso1");
      user.setAge(20);
      user.setGender(1);
      user.setPet(pet2);
      String[] hobby = new String[]{"篮球", "足球"}; // 我的爱好
      user.setHobby(hobby);
      model.addAttribute("user", user);

      return "result";
   }

   @RequestMapping("update2")
   public String update2(User user, Model model){
      System.out.println(user);
      model.addAttribute("user", user);
      return "result1";
   }
}
```

```jsp
# result jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>  # 不要忘记
<html>
<head>
    <title>Result</title>
</head>
<body>
<div style="width: 700px; margin: auto; padding-top: 20px; padding-bottom: 40px">
    <h1>Result</h1>
    <%-- 如果不写modelAttribute="user"，默认到model中找的是modelAttribute="command",如果没有就报错 --%>
    <%-- action默认是从什么地址来返回什么地址，一般都是重新设置 --%>
    <fm:form modelAttribute="user" action="${pageContext.request.contextPath}/update2">
        姓名：<fm:input path="name"/> <br>
        年龄：<fm:input path="age"/> <br>

        <%-- 单选框 path对应属性名，value对应属性值 --%>
        性别：<fm:radiobutton path="gender" value="0" label="男" />
        <fm:radiobutton path="gender" value="1" label="女" /><br>

        <%-- 复选框 path是user的hobby属性的值，items是所有爱好的集合 --%>
        爱好：<fm:checkboxes path="hobby" items="${allhobby}" /> <br>

        <%-- 下拉列表 path:设置当把表单提交的时候把itemValue的值传给pet对象对应的属性 --%>
        <%-- items:数据来源 --%>
        <%-- itemValue:提交的值 --%>
        <%-- itemLabel:视图显示的值 --%>
        <fm:select path="pet.id" items="${petList}" itemValue="id" itemLabel="name" /><br>

        <input type="submit" value="修改">
    </fm:form>
</div>
</body>
</html>

```

## # 服务器端表单验证

**JSR**:

JSR 303是java为bean数据合法性校验提供的标准框架，已经包含在javaEE 6.0中；JSR 303通过在bean属性上标注类似于@NotNull、@Max等标准的注解指定校验规则，并通过标准的验证接口对bean进行验证。

**Hibernate Validator**:

Hibernate Validator实现了JSR 303的接口，是JSR 303的一个参考实现除了支持所有标准的验证注解外，它还支持其它的扩展注解，比如@Email注解、@Length(min=,max=)等。

### 使用Hibernate Validator

* 在配置文件中写上```<mvc:annotation-driven>```
* 在模型中添加对应的校验规则
* 在处理器方法的入参标记@valid注解即可
* 错误信息页回显

**验证数据，并获取验证信息**:

```java

/* 模型类中的属性 */

@NotNull(message = "名字不能为空")  // 不允许为空
private String name;

@NotNull(message = "年龄不能为空")
@Max(value = 150, message = "请输入合法的年龄") // 设置年龄允许的最大值
private int age;

@Email(message = "请输入正确的邮箱") // 验证邮箱
private String email;

@Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$") // 匹配一个正则表达式
private String phone;

/* Controller中action */

/**
 * 打印修改后的数据
 * 其中@Valid，是对提交过来的数据进行验证，验证规则已经在User对象中
 * BindingResult用来接收验证结果
 * @param user 用户对象模型
 * @param model Model模型
 * @return 视图
 */
@RequestMapping("update2")
public String update2(@Valid User user, BindingResult result, Model model){
  System.out.println(user);
  List<FieldError> fieldErrors = result.getFieldErrors(); // 接收并存储验证结果
  for (FieldError fieldError:fieldErrors) { // 遍历打印验证结果，及错误提示信息
     System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
  }
  model.addAttribute("user", user);
  return "result1";
}

```

**回显验证信息**:

在Controller中通过result.getErrorCount() != 0 来判断是否有验证为通过的数据

```jsp

# 使用了spring封装的form标签
# 举两个例子其它类似,注意看标签的属性和属性值

姓名：<fm:input path="name"/> <fm:errors path="name" /><br>
年龄：<fm:input path="age"/> <fm:errors path="age" /> <br>

```

**使用常规form标签**:

我们知道spring封装的标签的使用场景是用表单显示数据，然后修改数据再提交，然后进行服务器端的数据验证，有验证错误就回到提交数据的界面，此过程又要重新传一次数据，其实验证错误信息springmvc已经在回显数据时帮我们写到了model中，这个可以断点调试查看model信息看到。所以我们可以在原来的页面中用```<error>```标签显示错误信息。

所以我们可以效仿上面原理，在普通form表单提交接收数据后对数据进行验证，如果有验证未通过，则获取验证错误信息，并将错误信息手动存入model，这样就可以在普通form表单中接收到错误信息了。写的形式建议是：

```java

public String commitData(@Valid User user, BindingResult result, Model model){
  List<FieldError> fieldErrors = result.getFieldErrors(); // 接收并存储验证结果
  if (result.getErrorCount() != 0){
    for (FieldError fieldError:fieldErrors) { // 遍历打印验证结果，及错误提示信息
      model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage()); // 建议使用这个key
    }
    return "原提交页"
  }
  return "验证通过后的去向"
}
```

## # 静态资源访问

当我们去访问静态文件时出现了错误，其实是因为我们在web.xml配置文件设置```<servlet-mapping>```时，设置了```<url-pattern>/</url-pattern>```,它将图片等资源全部过滤了。解决办法是：在springmvc.xml配置文件中加上```<mvc:default-servlet-handler />```, 作用是开放静态资源的访问，判断访问是否是静态资源，是就放行，不是就去@RequestMapping中匹配。
