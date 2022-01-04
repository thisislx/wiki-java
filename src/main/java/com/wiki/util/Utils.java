package com.wiki.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
  private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

  /** 改变成员值 */
  public static <P, R> List<R> changeMember(Function<P, R> handle, List<P> list) {
    List<R> res = new ArrayList<R>();
    list.forEach(v -> res.add(handle.apply(v)));
    return res;
  }

  public static void info(String method, String path, String message) {
    LOG.info("[%s]%s: %s", method.toUpperCase(), path, message);
  }

  public static void info(String path, String message) {
    LOG.info("[]%s: %s", path, message);
  }
}
