package com.rehi.jdbcManager

import com.rehi.logger.loggerGlobal

import java.sql.DriverManager
import java.sql.Connection

/**
 * @author DRehi
 */
trait jdbc extends loggerGlobal{

  logger.info("Started JDBC TRAIT")
  var connection:Connection = _

  def closeJdbcConnection(con:Connection) = {
    con.close
    logger.info("Jdbc Connection Closed")
  }
  
  def getJdbcConnection(driver: String, url:String, username:String, password:String): Connection = {
    
    logger.info(s"""Getting the connection with given info:
	                | driver  : ${driver}
	                | url     : ${url}
	                | username: ${username}
	                | password: ${password}""".stripMargin)
    
    try {

      logger.info("Attempt to make Connection")
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      
      logger.info("Connection Established!")
      
    } catch {
      case e: Throwable => {
        logger.error("Something Went Wrong @jdbc.scala .......Opps")
        e.printStackTrace
      }
    }
    connection
  }
  
}