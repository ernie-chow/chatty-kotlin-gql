import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
//	id("com.apollographql.apollo3").version("3.5.0")
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.ernie"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
//	implementation("com.apollographql.apollo3:apollo-runtime")
	implementation("com.graphql-java-kickstart:graphql-java-tools:13.0.0")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:14.0.0")
	implementation("com.graphql-java-kickstart:graphql-spring-boot-starter-test:14.0.0")
	implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.1.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}

//	apollo {
//		packageName.set("com.ernie.chatty")
//	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//tasks.register("downloadSchema", com.apollographql.apollo.gradle.internal.ApolloDownloadSchemaTask::class.java) {
//	endpointUrl.set("https://")
//	schemaFilePath.set("src/main/graphql/com/example/schema.json")
//}
