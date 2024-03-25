package com.kinect.messaging.kinectconfigserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class KinectConfigServerApplication

fun main(args: Array<String>) {
	runApplication<KinectConfigServerApplication>(*args)
}
