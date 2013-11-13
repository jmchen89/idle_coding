// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.backoff;

/**
 * an action, it contains a operation and the target field and value
 * 
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class Action {
  public final static Object object = new Object();
  public Operation operation = Operation.None;
  public String field = "";
  public Object value = object;

  public Action(Operation operation, String field, Object value) {
    this.operation = operation;
    this.field = field;
    this.value = value;
  }

  public Action(Operation operation, String field) {
    this(operation, field, object);
  }
}
