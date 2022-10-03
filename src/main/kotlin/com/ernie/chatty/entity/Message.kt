package com.ernie.chatty.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "messages")
data class Message(
    var sender: String,
    var parentMessageId: String?,
    var content: String,
    var timeStamp: String,
    var likes: Int = 0,
    var dislikes: Int = 0
) {
    @Id
    var id: String = ""

    @Transient
    var replies: List<Message> = ArrayList()
}