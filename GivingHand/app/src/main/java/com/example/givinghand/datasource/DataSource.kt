package com.example.givinghand.datasource

import com.example.givinghand.R
import com.example.givinghand.model.ActionItem
import com.example.givinghand.model.User

object DataSource {
    var DonateActionItems = mutableListOf(
        ActionItem.DonateActionItem(
            id = 1,
            name = "Red Cross",
            address = "Kolodvorska 12, Sarajevo 71000",
            description = "Collecting donations for building a women's shelter.",
            currentVolunteers = 0,
            picture = R.drawable.redcross,
            maxVolunteers = 2147483647,
            date = "Unknown"
        ),
        ActionItem.DonateActionItem(
            id = 2,
            name = "Heart for Children",
            address = "Zmaja od Bosne bb, 71000 Sarajevo",
            description = "Donate to help with medical bills for children in need.",
            currentVolunteers = 0,
            picture = R.drawable.srcezadejcu,
            maxVolunteers = 2147483647,
            date = "Unknown"
        ),
        ActionItem.DonateActionItem(
            id = 3,
            name = "Save Sarajevo",
            address = "Hamdije Čemerlića 39a, 71000 Sarajevo",
            description = "Donate to  to provide relief from the conditions of extreme poverty beset on the city of Sarajevo.",
            currentVolunteers = 0,
            picture = R.drawable.savesarajevo,
            maxVolunteers = 2147483647,
            date = "Unknown"
        ),
        ActionItem.DonateActionItem(
            id = 4,
            name = "SOS Children's Villages",
            address = "Ahmeda Muradbegovića 1c, Sarajevo",
            description = "Donate to help children and youth who are in risk and without parental guidance.",
            currentVolunteers = 0,
            picture = R.drawable.sos,
            maxVolunteers = 2147483647,
            date = "Unknown"
        ),
    )

    var AnimalCareActionItems = mutableListOf(
        ActionItem.AnimalCareActionItem(
            id = 1,
            name = "Noah's Ark",
            address = "Franjčevićeva ul., 10361, Dumovec, Croatia",
            description = "Accompany us to walk and play with animals living in our shelter",
            currentVolunteers = 0,
            maxVolunteers = 50,
            picture = R.mipmap.sos_foreground,
            date = "25.05.2023 12:00-16:00"

        ),
        ActionItem.AnimalCareActionItem(
            id = 2,
            name = "ZOO Zagreb Volunteering",
            address = "Ulica Fakultetsko dobro 1, 10000 Zagreb",
            description = "Volunteer in our ZOO and help feed and care for our animals",
            currentVolunteers = 0,
            maxVolunteers = 100,
            picture = R.mipmap.sos_foreground,
            date = "17.07.2023 10:00-18:00"

        ),
        ActionItem.AnimalCareActionItem(
            id = 3,
            name = "Animal Friend",
            address = "Jurišićeva 25, 10000 Zagreb",
            description = "Join us in caring for animals and making the world cruelty-free, safe space for them",
            currentVolunteers = 0,
            maxVolunteers = 20,
            picture = R.mipmap.sos_foreground,
            date = "20.06.2023 16:00-19:30"
        ),
    )

    var EnvironmentActionItems = mutableListOf(
        ActionItem.EnvironmentActionItem(
            id = 1,
            name = "Volunteering in Earth Network",
            address = "Maršala Tita 21, Sarajevo",
            description = "Join a project of cleaning Sarajevo from trash and help the community.",
            currentVolunteers = 0,
            maxVolunteers = 15,
            picture = R.mipmap.sos_foreground,
            date = "14.08.2023 10:00-16:30"
        ),
        ActionItem.EnvironmentActionItem(
            id = 2,
            name = "Living Healthy",
            address = "Rockefellerova 7, 10000 Zagreb",
            description = "Become a part of a group of volunteers with the aim of planting healthy, young trees in the parks of Zagreb.",
            currentVolunteers = 0,
            maxVolunteers = 20,
            picture = R.mipmap.sos_foreground,
            date = "17.06.2023 14:00-16:30"
        ),
        ActionItem.EnvironmentActionItem(
            id = 3,
            name = "Prevention Action",
            address = "Josipa Vancaša 21, 71000 Sarajevo",
            description = "Join us in clearing our mountains and rivers from trash that has been accumulating over the years.",
            currentVolunteers = 0,
            maxVolunteers = 27,
            picture = R.mipmap.sos_foreground,
            date = "14.08.2023 10:00-18:00"
        ),
        ActionItem.EnvironmentActionItem(
            id = 4,
            name = "Let's Do It",
            address = "Tepebašina 7, 71 000 Sarajevo",
            description = "Help us clean our neighborhood and make it a good place for people to live in.",
            currentVolunteers = 0,
            maxVolunteers = 35,
            picture = R.mipmap.sos_foreground,
            date = "22.07.2023 11:00-15:00"
        ),
    )

    var SocialActionItems = mutableListOf(
        ActionItem.SocialActionItem(
            id = 1,
            name = "Oasis Association Action",
            address = "Sutjeska 18, Sarajevo",
            description = "Join us and help elderly people by doing their shopping and everyday chores.",
            currentVolunteers = 0,
            maxVolunteers = 30,
            picture = R.mipmap.sos_foreground,
            date = "04.07.2023 10:00-12:00"
        ),
        ActionItem.SocialActionItem(
            id = 2,
            name = "Strebaj.ba Instructor Positions",
            address = "Trg Djece Sarajeva 18, Sarajevo",
            description = "Become a tutor and help children with understand and learn.",
            currentVolunteers = 0,
            maxVolunteers = 10,
            picture = R.mipmap.sos_foreground,
            date = "08.06.2023 08:00-14:00"
        ),
        ActionItem.SocialActionItem(
            id = 3,
            name = "Sarajevo Film Festival Volunteers",
            address = "CineStar Sarajevo",
            description = "Become a volunteer for SFF and help in festival realization.",
            currentVolunteers = 0,
            maxVolunteers = 45,
            picture = R.mipmap.sos_foreground,
            date = "12.08.2023 09:00-20:30"
        ),

    )

    var UnauthorizedUsers = mutableListOf(
        User.UnauthorizedUser(
            id = 1,
            username = "johndoe1",
            password = "test123",
            email = "johndoe@gmail.com",
            name = "John Doe",
            address = "Baker Street 22",
            authorized = false
        ),
        User.UnauthorizedUser(
            id = 2,
            username = "tombrown",
            password = "testbest",
            email = "tombrown@gmail.com",
            name = "Tom Brown",
            address = "La Rambla 13",
            authorized = false
        ),
        User.UnauthorizedUser(
            id = 3,
            username = "janedoe1",
            password = "test123",
            email = "janedoe@gmail.com",
            name = "Jane Doe",
            address = "Sutjeska 1",
            authorized = false
        ),
    )

    var AuthorizedUsers = mutableListOf(
        User.AuthorizedUser(
            id = 1,
            username = "saranalo",
            password = "test123",
            email = "saranalo@gmail.com",
            name = "Sara Nalo",
            address = "Alije Nametka 22, Sarajevo",
            authorized = false
        ),
        User.AuthorizedUser(
            id = 2,
            username = "aminatpkvc",
            password = "testbest",
            email = "aminatpkvs@gmail.com",
            name = "Amina Tupković",
            address = "Dobrinjska 45",
            authorized = false
        ),
        User.AuthorizedUser(
            id = 3,
            username = "tijanab",
            password = "test123",
            email = "burtijana@gmail.com",
            name = "Tijana Burazorović",
            address = "Džemala Bijedića 62",
            authorized = false
        ),
    )




}