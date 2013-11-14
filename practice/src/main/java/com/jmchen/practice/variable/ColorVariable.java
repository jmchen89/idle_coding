// Copyright 2013 Jmchen Inc. All Rights Reserved.
package com.jmchen.practice.variable;

/**
 * It will set the varibal will color html tag
 * 
 * @author Jimmy Chen, <ggm19890303@qq.com>
 */
public class ColorVariable extends Variable {
  private final static String COLOR_TEMPALTE = "<font color=\"#923875\">%s</font>";

  public ColorVariable(String name, String template) {
    this(name, template, true);
  }

  public ColorVariable(String name, String template, boolean isValid) {
    super(name, String.format(COLOR_TEMPALTE, template), isValid);
  }
}
