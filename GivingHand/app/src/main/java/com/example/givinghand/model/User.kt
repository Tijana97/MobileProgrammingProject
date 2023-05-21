package com.example.givinghand.model

sealed class User(
    open val id: Int,
    open val username: String,
    open var password: String,
    open var email: String,
    open val name: String,
    open var address: String,
    open val authorized: Boolean
){
    data class UnauthorizedUser(
        override val id: Int,
        override val username: String,
        override var password: String,
        override var email: String,
        override val name: String,
        override var address: String,
        override val authorized: Boolean,
        var userActions: List<Int>
    ) : User(id, username, password, email, name, address, authorized)

    data class AuthorizedUser(
        override val id: Int,
        override val username: String,
        override var password: String,
        override var email: String,
        override val name: String,
        override var address: String,
        override val authorized: Boolean
    ) : User(id, username, password, email, name, address, authorized)
}
