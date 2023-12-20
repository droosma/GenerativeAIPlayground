const { SearchClient } = require("@azure/search-documents");
const { AzureKeyCredential } = require("@azure/core-auth");
const { OpenAIClient } = require("@azure/openai");

const dotenv = require("dotenv");
dotenv.config();

const search_endpoint = process.env.SEARCH_ENDPOINT || "";
const search_apiKey = process.env.SEARCH_API_ADMIN_KEY || "";
const search_index_name = "polisblad";

const azure_openai_endpoint = process.env.AZURE_OPENAI_ENDPOINT || "";
const azure_openai_apiKey = process.env.AZURE_OPENAI_KEY || "";

async function getEmbeddings(client, query) {
    const response = await client.getEmbeddings("text-embedding-ada-002", query);
    return response.data[0].embedding;
}

async function search(client, embedding) {
    const searchResults = await client.search(undefined, {
        vectorQueries: [{
            kind: "vector",
            vector: embedding,
            kNearestNeighborsCount: 3,
            fields: ["contentVector"],
          }]
    });

    for await (const result of searchResults.results) {
        console.log(result);
    }
}

async function main() {
    if (!search_endpoint || !search_apiKey) {
        console.log("Make sure to set valid values for endpoint and apiKey with proper authorization.");
        return;
    }

    if (!azure_openai_endpoint || !azure_openai_apiKey) {
        console.log("Make sure to set valid values for endpoint and apiKey with for azure openai proper authorization.");
        return;
    }

    const query = "toyota";

    const searchClient = new SearchClient(search_endpoint, search_index_name, 
        new AzureKeyCredential(search_apiKey));

    const openAiClient = new OpenAIClient(azure_openai_endpoint, 
        new AzureKeyCredential(azure_openai_apiKey));

    var embedding = await getEmbeddings(openAiClient, query);
    await search(searchClient, embedding);
}

main().catch((err) => {
    console.error("The sample encountered an error:", err);
  });