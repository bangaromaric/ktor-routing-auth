package ga.example

import ga.example.jwtConfig.JwtConfig
import ga.example.models.User
import ga.example.routes.registerAuthRoutes
import ga.example.routes.registerCustomerRoutes
import ga.example.routes.registerOrderRoutes
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.metrics.dropwizard.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.serialization.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.TimeUnit

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */

fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        json()
    }

    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "com.imran"
            validate {
                val name = it.payload.getClaim("name").asString()
                val password = it.payload.getClaim("password").asString()
                if(name != null && password != null){
                    User(name, password )
                }else{
                    null
                }
            }
        }
    }


    registerAuthRoutes()
    registerCustomerRoutes()
    registerOrderRoutes()

    initDB()



}

fun initDB(){

    Database.connect("jdbc:mysql://localhost:3306/pers", driver = "com.mysql.jdbc.Driver",
        user = "romaric", password = "azerty")

    transaction {
        // print sql to std-out
        addLogger(StdOutSqlLogger)
    }

}

