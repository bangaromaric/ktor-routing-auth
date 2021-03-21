package ga.example.routes

import ga.example.jwtConfig.JwtConfig
import ga.example.models.User
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.registerAuthRoutes(){
    routing{
        generateToker()
    }

}


fun Route.generateToker(){
    post("/generate_token"){
        val user = call.receive<User>()
        print("${user.name} , pwd= ${user.password}")
        val token = JwtConfig.generateToken(user)
        call.respond(token)
    }
}