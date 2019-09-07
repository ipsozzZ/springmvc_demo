package live.ipso.springmvc2.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
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
