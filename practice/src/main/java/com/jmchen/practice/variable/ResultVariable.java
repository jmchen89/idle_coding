// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.variable;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * it is a result of the variable
 *
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class ResultVariable extends Variable {
  public String defaultResult = "";
  public ResultVariable(String name, String template, boolean isValid, String defaultResult) {
    super(name, template, isValid);
    this.defaultResult = defaultResult;
  }

  public ResultVariable(String name, String template, String defaultResult) {
    this(name, template, true, defaultResult);
  }

  @Override
  public String value(Map<String, Variable> runtimeContext) {
    boolean isAllUnavailable = true;
    Matcher matcher = compile();
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      String name = matcher.group(1);
      Variable variable = runtimeContext.get(name);
      if(variable != null && variable.isValid()) {
        matcher.appendReplacement(sb, variable.value(runtimeContext));
        isAllUnavailable = false;
      } else {
        matcher.appendReplacement(sb, "");
      }
    }
    matcher.appendTail(sb);
    if(isAllUnavailable) {
      return defaultResult;
    } else {
      return sb.toString();
    }
  }
}
