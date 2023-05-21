apply{
    from("$rootDir/library-build.gradle")
}

plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
//    id(SqlDelight.plugin)
    id("app.cash.sqldelight")
}

dependencies{


    "implementation"(project(Modules.heroDomain))
    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)
//    "implementation"("app.cash.sqldelight:android-driver:2.0.0-alpha05")
    "implementation"(SqlDelight.runtime)
//    "implementation"(SqlDelight.androidDriver)


}
sqldelight {
    databases {
        create("Database") {
            packageName.set("com.shohpolat.hero_datasource.cache")
            sourceFolders.set(listOf("sqldelight"))
        }
    }
}

//sqldelight {
//    database("HeroDatabase") {
//        packageName = "com.shohpolat.hero_datasource.cache"
//        sourceFolders = listOf("sqldelight")
//    }
//}