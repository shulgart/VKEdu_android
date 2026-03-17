package com.example.helloworld.presentation.appcard

enum class AppCategory(desc: String){
    FINANCES("Финансы"),
    TOOLS("Инструменты"),
    TRANSPORT("Транспорт");

    val desc: String = ""
}

fun getAppCategory(category: AppCategory) =
    when (category){
        AppCategory.FINANCES -> "Финансы"
        AppCategory.TOOLS -> "Инструменты"
        AppCategory.TRANSPORT-> "Транспорт"
    }