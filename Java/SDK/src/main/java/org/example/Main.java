package org.example;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;

public class Main {
    public static void main(String[] args) {
        final String API_KEY = "";
        final String END_POINT = "";
        final String DEPLOYMENT_NAME = "";

        OpenAIAsyncClient client = new OpenAIClientBuilder()
            .credential(new AzureKeyCredential(API_KEY))
            .endpoint(END_POINT)
            .buildAsyncClient();

        SDK scenario = new SDK(client, DEPLOYMENT_NAME);
        scenario.Execute();
    }
}