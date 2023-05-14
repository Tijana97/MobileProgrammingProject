package com.example.givinghand.model

import java.util.Date

sealed class ActionItem(
    open val id: Int,
    open var name: String,
    open var maxVolunteers: Int,
    open var address: String,
    open var description: String,
    open var currentVolunteers: Int,
    open var picture: String,
    open var date: String
){
    data class DonateActionItem(
        override val id: Int,
        override var name: String,
        override var maxVolunteers: Int,
        override var address: String,
        override var description: String,
        override var currentVolunteers: Int,
        override var picture: String,
        override var date: String
    ) : ActionItem(id, name, maxVolunteers, address, description, currentVolunteers, picture, date)

    data class AnimalCareActionItem(
        override val id: Int,
        override var name: String,
        override var maxVolunteers: Int,
        override var address: String,
        override var description: String,
        override var currentVolunteers: Int,
        override var picture: String,
        override var date: String
    ) : ActionItem(id, name, maxVolunteers, address, description, currentVolunteers, picture, date)


    data class EnvironmentActionItem(
        override val id: Int,
        override var name: String,
        override var maxVolunteers: Int,
        override var address: String,
        override var description: String,
        override var currentVolunteers: Int,
        override var picture: String,
        override var date: String
    ) : ActionItem(id, name, maxVolunteers, address, description, currentVolunteers, picture, date)


    data class SocialActionItem(
        override val id: Int,
        override var name: String,
        override var maxVolunteers: Int,
        override var address: String,
        override var description: String,
        override var currentVolunteers: Int,
        override var picture: String,
        override var date: String
    ) : ActionItem(id, name, maxVolunteers, address, description, currentVolunteers, picture, date)


}
