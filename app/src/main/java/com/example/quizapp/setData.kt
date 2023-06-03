package com.example.quizapp

object setData {

    const val name:String="name"
    const val score:String="score"

   fun getQuestion():ArrayList<QuestionData>{
       var que:ArrayList<QuestionData> = arrayListOf()

       var question1 = QuestionData(
           1,
           "What is Kotlin??",

           " A programming language for Android development.",
           "An integrated development environment (IDE) for Android.",
           "3h8TCQWA34dcmmKdMsDg9drZtezPbuivhQQBA5VFR5ME",
           "A database management system for Android applications.",
           1
       )
       var question2 = QuestionData(
           2,
           "Which of the following is an advantage of using Kotlin over Java in Android development?",

           "Kotlin has a simpler syntax than Java.",
           "Kotlin is fully backward-compatible with Java.",
           "Kotlin offers better performance than Java.",
           "None of the above",
           1
       )
       var question3 = QuestionData(
           3,
           "What is the Gradle build system used for in Android Studio?",

           " It provides a graphical user interface for designing app layouts.",
           "It manages the compilation and packaging of Android applications.",
           " It enables real-time testing and debugging of Android apps.",
           " It offers a database management system for storing app data.",
           2
       )
       var question4 = QuestionData(
           4,
           " What is the entry point function in a Kotlin program?",

           "onCreate()",
           "onCreateView()",
           "onStart()",
           "main()",
           4
       )

       var question5 = QuestionData(
           5,
           "Question: Which keyword is used to declare a variable in Kotlin?",

           "val",
           "let",
           "var",
           "varlet",
           3
       )

       que.add(question1)
       que.add(question2)
       que.add(question3)
       que.add(question4)
       que.add(question5)
       return que
   }
}