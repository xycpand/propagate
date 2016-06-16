package com.hummingbird.propagate.util;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
/**
 * <p>日志管理</p>
 * @author panda
 */
@Component("loggerUtil")
public class LoggerUtil
{
   Class<?> clazz;
   Logger logger = null;

  public LoggerUtil() {
    this.logger = Logger.getLogger(getClass());
  }

  public LoggerUtil(Class<?> clazz) {
    this.clazz = clazz;
    this.logger = Logger.getLogger(clazz);
  }

  public void error(String message)
  {
    this.logger.error(message);
  }

  public void error(String message, Throwable t)
  {
    this.logger.error(message, t);
  }

  public void info(String message)
  {
    this.logger.info(message);
  }

  public void debug(String message)
  {
    this.logger.debug(message);
  }

  public void warn(String message)
  {
    this.logger.warn(message);
  }
}