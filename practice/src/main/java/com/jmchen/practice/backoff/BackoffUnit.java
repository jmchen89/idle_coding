// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.backoff;

import java.util.ArrayList;
import java.util.List;

/**
 * back off unit, it defines a step of backoff, it contains the description of the backoff step, and
 * also a list of action of the backoff step
 *
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class BackoffUnit {
  public List<Action> actionList = new ArrayList<Action>();
  public String desc = "";
  
  public BackoffUnit(Action action, String desc) {
    this.actionList.add(action);
    this.desc = desc;
  }
}
