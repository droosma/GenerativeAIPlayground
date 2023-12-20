package org.example;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.search.documents.indexes.SearchIndexClient;
import com.azure.search.documents.indexes.SearchIndexClientBuilder;

public class Main {
    public static void main(String[] args) {
        final String AZURE_OPENAI_API_KEY = "";
        final String AZURE_OPENAI_END_POINT = "";
        final String AZURE_SEARCH_API_KEY = "";
        final String AZURE_SEARCH_END_POINT = "";
        final String DEPLOYMENT_NAME = "gpt-4";

        OpenAIAsyncClient client = new OpenAIClientBuilder()
            .credential(new AzureKeyCredential(AZURE_OPENAI_API_KEY))
            .endpoint(AZURE_OPENAI_END_POINT)
            .buildAsyncClient();

        SearchIndexClient searchIndexClient = new SearchIndexClientBuilder()
                .endpoint(AZURE_SEARCH_END_POINT)
                .credential(new AzureKeyCredential(AZURE_SEARCH_API_KEY))
                .buildClient();

        AzureSearchExample azureSearch = new AzureSearchExample(client, searchIndexClient);
        azureSearch.Execute();

//        ChatExample example = new ChatExample(client, DEPLOYMENT_NAME);
//        example.Execute();
    }
}