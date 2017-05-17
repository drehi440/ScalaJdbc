package com.rehi.logger

import com.typesafe.scalalogging.slf4j.LazyLogging


/**
 * @author DRehi
 */
trait loggerGlobal extends LazyLogging {
  
  def getLogger = logger
  
  
  
}