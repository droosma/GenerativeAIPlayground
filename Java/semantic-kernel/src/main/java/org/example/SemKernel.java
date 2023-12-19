package org.example;

import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.chatcompletion.ChatCompletion;
import com.microsoft.semantickernel.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.connectors.ai.openai.chatcompletion.OpenAIChatHistory;

public class SemKernel {
    private final Kernel kernel;

    public SemKernel(Kernel kernel) {
        this.kernel = kernel;
    }
    public void Execute() {
        ChatCompletion<OpenAIChatHistory> chatGPT = kernel.getService(null, ChatCompletion.class);

        OpenAIChatHistory chatHistory = chatGPT.createNewChat("You are a librarian, expert about books");

        // First user message
        chatHistory.addUserMessage("Hi, I'm looking for book suggestions");
        messageOutputAsync(chatHistory);

        // First bot assistant message
        String reply = chatGPT.generateMessageAsync(chatHistory, null).block();
        chatHistory.addAssistantMessage(reply);
        messageOutputAsync(chatHistory);

        // Second user message
        chatHistory.addUserMessage(
            "I love history and philosophy, I'd like to learn something new about Greece, any suggestion?");
        messageOutputAsync(chatHistory);

        // Second bot assistant message
        reply = chatGPT.generateMessageAsync(chatHistory, null).block();
        chatHistory.addAssistantMessage(reply);
        messageOutputAsync(chatHistory);
    }

    private static void messageOutputAsync(ChatHistory chatHistory) {
        ChatHistory.Message message = chatHistory.getMessages().getLast();

        System.out.println(message.getAuthorRoles() + ": " + message.getContent());
        System.out.println("------------------------");
    }
}
