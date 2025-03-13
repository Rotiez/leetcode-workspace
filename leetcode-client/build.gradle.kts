dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.23")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("ch.qos.logback:logback-classic:1.4.6")
    implementation("ch.qos.logback:logback-core:1.4.6")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("org.aspectj:aspectjrt:1.9.20")
    implementation("org.aspectj:aspectjweaver:1.9.20")

    api(platform("org.junit:junit-bom:5.10.0"))
    api("org.junit.jupiter:junit-jupiter")
}
