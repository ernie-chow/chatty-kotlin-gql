package com.ernie.chatty.resolvers

import com.ernie.chatty.entity.Message
import com.ernie.chatty.repository.MessageRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class MessageMutationResolver(private val messageRepository: MessageRepository) : GraphQLMutationResolver {
    fun newMessage(sender: String, parentMessageId: String? = "", content: String): Message {
        val message = Message(sender, parentMessageId, content, LocalDateTime.now().toString())
        message.id = UUID.randomUUID().toString()
        messageRepository.save(message)
        return message
    }

    fun deleteMessage(id: String): Boolean {
        messageRepository.deleteById(id)
        return true
    }

    fun editMessage(id: String, content: String, timeStamp: String): Message {
        val message = messageRepository.findById(id)
        message.ifPresent {
            it.content = content
            it.timeStamp = timeStamp
            messageRepository.save(it)
        }

        return message.get()
    }

    fun likeMessage(id: String): Message {
        val message = messageRepository.findById(id)
        message.ifPresent {
            it.likes += 1
            messageRepository.save(it)
        }

        return message.get()
    }

    fun dislikeMessage(id: String): Message {
        val message = messageRepository.findById(id)
        message.ifPresent {
            it.dislikes += 1
            messageRepository.save(it)
        }

        return message.get()
    }
}