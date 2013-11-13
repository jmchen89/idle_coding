// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.backoff;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * use for common backoff center test, it initializes a init vlaue map, and defines some action
 * try to do the backoff
 *
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class CommonBackoffCenterTest {
  private CommonBackoffCenter commonBackoffCenter;
  
  @Before
  public void setup() {
    commonBackoffCenter = new CommonBackoffCenter();
    commonBackoffCenter.addValue("distance", "500");
    commonBackoffCenter.addValue("name", "肯德基");
    commonBackoffCenter.addValue("category", "川菜");
    commonBackoffCenter.addValue("dish", "水煮鱼");
    
    Action action1 = new Action(Operation.Add, "special", "WIFI");
    BackoffUnit backoffUnit1 = new BackoffUnit(action1, "增加一个WIFI的特色");
    commonBackoffCenter.addBackoffUnit(backoffUnit1);
    
    Action action2 = new Action(Operation.Change, "distance", "1000");
    BackoffUnit backoffUnit2 = new BackoffUnit(action2, "把距离变为1000");
    commonBackoffCenter.addBackoffUnit(backoffUnit2);
    
    Action action3 = new Action(Operation.Delete, "dish");
    BackoffUnit backoffUnit3 = new BackoffUnit(action3, "把推荐菜删除掉");
    commonBackoffCenter.addBackoffUnit(backoffUnit3);
    
    Action action4 = new Action(Operation.Delete, "special");
    BackoffUnit backoffUnit4 = new BackoffUnit(action4, "把特色删除掉");
    commonBackoffCenter.addBackoffUnit(backoffUnit4);
  }
  
  @Test
  public void test1() throws Exception {
    assertEquals(commonBackoffCenter.backoff().toString(), 
        "{dish=水煮鱼, distance=500, category=川菜, name=肯德基, special=WIFI}");
  }
}
