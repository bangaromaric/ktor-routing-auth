package ga.example.models

import io.ktor.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val password: String, val other:String="default"): Principal