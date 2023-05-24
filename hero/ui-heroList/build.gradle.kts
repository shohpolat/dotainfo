apply{
    from("$rootDir/android-library-build.gradle")
}
dependencies{
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroInteractors))
    "implementation"(project(Modules.heroDomain))

    "implementation"(SqlDelight.androidDriver)
    "implementation"("com.google.dagger:hilt-android:2.44")
    "kapt"("com.google.dagger:hilt-android-compiler:2.44")

}