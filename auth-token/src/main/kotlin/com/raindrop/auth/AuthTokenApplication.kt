package com.raindrop.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthTokenApplication

fun main(args: Array<String>) {
    runApplication<AuthTokenApplication>(*args)
}
