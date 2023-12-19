package org.example;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.SKBuilders;
import com.microsoft.semantickernel.chatcompletion.ChatCompletion;

public class Main {
    public static void main(String[] args) {
        final String API_KEY = "";
        final String END_POINT = "";
        final String DEPLOYMENT_NAME = "";

        OpenAIAsyncClient client = new OpenAIClientBuilder()
            .credential(new AzureKeyCredential(API_KEY))
            .endpoint(END_POINT)
            .buildAsyncClient();

        Kernel kernel = SKBuilders.kernel()
                .withAIService(
                        DEPLOYMENT_NAME,
                        SKBuilders.chatCompletion()
                                .withOpenAIClient(client)
                                .withModelId(DEPLOYMENT_NAME)
                                .build(),
                        true,
                        ChatCompletion.class
                )
                .build();

        SemKernel scenario = new SemKernel(kernel);
        scenario.Execute();
    }
}