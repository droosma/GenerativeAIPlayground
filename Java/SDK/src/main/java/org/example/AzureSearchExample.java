package org.example;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.core.util.Context;
import com.azure.search.documents.SearchClient;
import com.azure.search.documents.indexes.SearchIndexClient;
import com.azure.search.documents.models.*;
import com.azure.search.documents.util.SearchPagedIterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AzureSearchExample {
    private final OpenAIAsyncClient client;
    private final SearchIndexClient searchIndexClient;
    private final String SearchIndex = "polisblad";

    public AzureSearchExample(OpenAIAsyncClient client,
                              SearchIndexClient searchIndexClient) {
        this.client = client;
        this.searchIndexClient = searchIndexClient;
    }

    private List<Float> getEmbedding(String input){
        EmbeddingsOptions embeddingsOptions = new EmbeddingsOptions(Collections.singletonList(input));
        Embeddings result = client.getEmbeddings("text-embedding-ada-002", embeddingsOptions).block();
        List<Double> embeddingList = result.getData().get(0).getEmbedding();

        List<Float> floatList = new ArrayList<>();
        for (Double d : embeddingList) {
            floatList.add(d.floatValue());
        }

        return floatList;
    }

    public void Execute(){
        List<Float> embedding = getEmbedding("toyota");
        SearchClient searchClient = searchIndexClient.getSearchClient(SearchIndex);

        VectorQuery query = new VectorizedQuery(embedding)
                .setKNearestNeighborsCount(3)
                .setFields("contentVector");

        SearchPagedIterable searchResults = searchClient.search(null, new SearchOptions()
                        .setVectorSearchOptions(new VectorSearchOptions().setQueries(query)),
                Context.NONE);

        int count = 0;
        for (SearchResult searchResult : searchResults)
        {
            count++;
        }
        System.out.println("Total number of search results: " + count);
    }
}
