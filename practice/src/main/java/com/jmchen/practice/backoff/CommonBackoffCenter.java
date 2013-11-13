// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.backoff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * common backoff center, it defines a map of values, a list of backoff unit
 * according to the backoffunitlist, it will give the result of each backoff step
 *
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class CommonBackoffCenter {
  private Map<String, Object> initValueMap = new HashMap<String, Object>();
  private List<BackoffUnit> backoffList = new ArrayList<BackoffUnit>();
  private int currentStep = 0;
  
  public Map<String, Object> backoff() {
    Map<String, Object> resultValueMap = initValueMap;
    increaseStep();
    
    for (int i = 0; i < currentStep && i < backoffList.size(); i++) {
      BackoffUnit backoffUnit = backoffList.get(i);
      resultValueMap = backoffValueMap(backoffUnit, resultValueMap);
    }
    
    return resultValueMap;
  }
  
  public void addValue(String field, Object value) {
    if (value != null) {
      initValueMap.put(field, value);
    }
  }
  
  public void addBackoffUnit(BackoffUnit backoffUnit) {
    backoffList.add(backoffUnit);
  }
  
  public BackoffUnit getCurrentBackoffUnit() {
    return backoffList.get(currentStep);
  }
  
  public boolean canBackoff() {
    return currentStep == backoffList.size();
  }
  
  public boolean canRollback() {
    return currentStep == 0;
  }
  
  public Map<String, Object> rollback() {
    Map<String, Object> resultValueMap = initValueMap;
    decreateStep();
    
    for (int i = 0; i < currentStep && i < backoffList.size(); i++) {
      BackoffUnit backoffUnit = backoffList.get(i);
      resultValueMap = backoffValueMap(backoffUnit, resultValueMap);
    }
    
    return resultValueMap;
  }
  
  private void increaseStep() {
    if (currentStep < backoffList.size()) {
      currentStep++;
    }
  }

  private void decreateStep() {
    if (currentStep > 0) {
      currentStep--;
    }
  }

  protected Map<String, Object> backoffValueMap(BackoffUnit backoffUnit, 
      Map<String, Object> backoffValueMap) {
    Map<String, Object> dupValueMap = new HashMap<String, Object>(backoffValueMap);
    for (Action action : backoffUnit.actionList) {
      switch (action.operation) {
      case Delete:
        dupValueMap.remove(action.field);
        break;
      case Add:
        dupValueMap.put(action.field, action.value);
        break;
      case Change:
        dupValueMap.put(action.field, action.value);
        break;
      default:
        break;
      }
    }
    return dupValueMap;
  }
}
