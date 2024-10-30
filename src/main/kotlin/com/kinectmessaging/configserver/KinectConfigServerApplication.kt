package com.kinectmessaging.configserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class KinectConfigServerApplication

fun main(args: Array<String>) {
	val app = SpringApplication(
		KinectConfigServerApplication::class.java
	)
	app.applicationStartup = BufferingApplicationStartup(2048)
	app.run(*args)
//	runApplication<KinectConfigServerApplication>(*args)
}
