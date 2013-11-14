package com.jmchen.practice.variable;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class VariableTest {

  @Test
  public void test() {
    Variable variable1 = new ColorVariable("name", "蕉叶");
    Variable variable2 = new Variable("name_template", "名字为{name}的");
    Variable variable3 = new ColorVariable("category", "川菜馆");
    Variable variable4 = new Variable("category_template", "类别为{category}");
    Variable variable5 = new ColorVariable("dish", "水煮鱼");
    Variable variable6 = new Variable("dish_template", "有{dish}的");
    Variable variable7 = new Variable("result", "为您找到{dish_template}{name_template}{category}");
    Variable variable8 = new ResultVariable("result_backoff", "很抱歉！未能找到{dish_template}{name_template}{category}，" +
    		"为您推荐{dish_template}{name}", "为您找到美食");
    
    Map<String, Variable> runtimeContext = new HashMap<String, Variable>();
    runtimeContext.put(variable1.getName(), variable1);
//    runtimeContext.put(variable2.getName(), variable2);
//    runtimeContext.put(variable3.getName(), variable3);
//    runtimeContext.put(variable4.getName(), variable4);
//    runtimeContext.put(variable5.getName(), variable5);
//    runtimeContext.put(variable6.getName(), variable6);
//    
//    System.out.println(variable7.value(runtimeContext));
    System.out.println(variable8.value(runtimeContext));
  }
}
