package com.kebinr.loginactivity.Data

data class User (val correo: String, val contraseña: String) {

    override fun toString(): String {
        return "$correo"+"$contraseña"
    }
}