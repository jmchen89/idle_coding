package com.jmchen.practice.variable;

public class DefaultVaribale extends Variable {
  private String defaultValue = "";
  public DefaultVaribale(String name, String template, boolean isValid) {
    super(name, template, isValid);
  }

  public DefaultVaribale(String name, String template) {
    super(name, template);
  }
}
