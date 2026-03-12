package com.example.helloworld

enum class AppCategory(desc: String){
    FINANCES("Финансы"),
    TOOLS("Инструменты"),
    TRANSPORT("Транспорт");

    val desc: String = ""
}

fun getAppCategory(app: AppCategory) =
    when (app){
        AppCategory.FINANCES -> "Финансы"
        AppCategory.TOOLS -> "Инструменты"
        AppCategory.TRANSPORT-> "Транспорт"
    }

data class AppShort(
    val name: String,
    val description: String,
    val category: AppCategory,
    val icon: Int
)
