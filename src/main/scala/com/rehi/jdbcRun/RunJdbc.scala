package com.rehi.jdbcRun

import com.rehi.jdbcManager._
import com.rehi.logger._
import scala.collection.mutable.HashMap

object RunJdbc extends App with jdbc with loggerGlobal {

  logger.info("This is RunJdbc Main")
  var dataMap: HashMap[String, String] = _

  dataMap = openJdbcConnection

  logger.info("These are the following users:"+dataMap.values)
  
  
  def openJdbcConnection: HashMap[String, String] = {

    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://localhost/mysql"
    val username = "root"
    val password = "root"

    getJdbcConnection(driver, url, username, password)

    try {
      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT host, user FROM user")
      while (resultSet.next()) {
        val host = resultSet.getString("host")
        val user = resultSet.getString("user")
        println("host, user = " + host + ", " + user)

        dataMap.put(host, user)

    logger.info(s"""Getting the connection with given info:
	                | host  : ${host}
	                | user  : ${user}""".stripMargin)
      }

    } catch {
      case e: Throwable => {
        logger.error("Something Went Wrong @RUNJDBC.scala.......Opps")
        e.printStackTrace
      }
    }
    dataMap
  }
  
  closeJdbcConnection(connection)
}