package com.qa.dayone.timezones

import java.util.TimeZone._

object Main extends App {
  getAvailableIDs
    .map(id => id.split(("/")))
    .filter(arr => arr.length > 1)
    .map(arr => arr(1))
    .grouped(10)
    .foreach(group => println(group(0)))
}
