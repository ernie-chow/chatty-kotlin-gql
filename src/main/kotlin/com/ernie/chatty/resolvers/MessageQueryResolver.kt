package com.ernie.chatty.resolvers

import com.ernie.chatty.entity.Message
import com.ernie.chatty.repository.MessageRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin(origins = ["https://localhost:3000"])
@Component
class MessageQueryResolver(
    val messageRepository: MessageRepository,
    private val mongoOperations: MongoOperations): GraphQLQueryResolver {
    fun getMessages(): List<Message> {
        val list = messageRepository.findAll()
        for (message in list) {
            message.replies = getReplies(messageId = message.id)
        }

        return list
    }

    fun getMessage(id: String): Message? {
        return messageRepository.findByIdOrNull(id)
    }

    private fun getReplies(messageId: String): List<Message> {
        val query = Query()
        query.addCriteria(Criteria.where("parentMessageId").`is`(messageId))

        return mongoOperations.find(query, Message::class.java)
    }
}