package org.example;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.*;

import java.util.ArrayList;
import java.util.List;

public class ChatExample {
    private final OpenAIAsyncClient openAIAsyncClient;
    private final String deployment;

    public ChatExample(OpenAIAsyncClient client, String deployment) {
        this.openAIAsyncClient = client;
        this.deployment = deployment;
    }

    public void Execute(){
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant. You will talk like a pirate."));
        chatMessages.add(new ChatRequestUserMessage("Can you help me?"));
        chatMessages.add(new ChatRequestAssistantMessage("Of course, me hearty! What can I do for ye?"));
        chatMessages.add(new ChatRequestUserMessage("What's the best way to train a parrot?"));

        ChatCompletions chatCompletions = openAIAsyncClient.getChatCompletions(deployment, new ChatCompletionsOptions(chatMessages)).block();

        assert chatCompletions != null;

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }
    }
}
