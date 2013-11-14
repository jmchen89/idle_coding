// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.variable;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a variable
 * 
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class Variable {
  private final static Pattern pattern = Pattern.compile("\\{([a-z0-9|_]+)\\}");
  private String name = "";
  private String template = "";
  private boolean isValid = true;

  public Variable(String name, String template) {
    this(name, template, true);
  }

  public Variable(String name, String template, boolean isValid) {
    this.name = name;
    this.template = template;
    this.isValid = isValid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public boolean isValid() {
    return isValid;
  }

  public void setValid(boolean isValid) {
    this.isValid = isValid;
  }

  public String value(Map<String, Variable> runtimeContext) {
    Matcher matcher = compile();
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      String name = matcher.group(1);
      Variable variable = runtimeContext.get(name);
      if(variable != null && variable.isValid()) {
        matcher.appendReplacement(sb, variable.value(runtimeContext));
      } else {
        setValid(false);
        break;
      }
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  protected Matcher compile() {
    return pattern.matcher(template);
  }
}
