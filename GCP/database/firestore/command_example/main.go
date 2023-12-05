package main

import (

	"log"
	"context"
	"os"
	"gopkg.in/yaml.v3"
	"cloud.google.com/go/firestore"
	"google.golang.org/api/iterator"
)

type Config struct {
	ProjectId 		string
	DatabaseName 	string
}

func main() {

	ctx := context.Background()

	client := buildFirestoreClientFromConfiguration(ctx);
	defer client.Close()


    /////////////////////////////////////////query
	getSingleCity(ctx, client)
	//getAllDocuments(ctx, client)
	//simpleCollectionsQuery(ctx, client)
	//compoundQuery(ctx, client)
	//inequalityQuery(ctx, client)
	//disjunctionQuery(ctx, client)
	//collectionGroupQuery(ctx, client)
	//orderingLimitQuery(ctx, client)

    /////////////////////////////////////////manage
    //createDocument(ctx, client)
	//mergeDocument(ctx, client)
	//updateDocument(ctx, client)
	//deleteDocumentFields(ctx, client)
	//deleteDocument(ctx, client)
	//batchWrite(ctx, client)
	//transactionOperation(ctx, client)

}

func buildFirestoreClientFromConfiguration(ctx context.Context) *firestore.Client {

	byteArray, err := os.ReadFile("config.yml")
	if err != nil {
		log.Fatalf("Error in loading config file: %v", err)
	}

	var config Config
	err = yaml.Unmarshal(byteArray, &config)
	if err != nil {
        log.Fatalf("Error in processing file: %v", err)
    }

	client, err := firestore.NewClientWithDatabase(ctx, config.ProjectId, config.DatabaseName)
	if err != nil {
		log.Fatalf("Failed to create client: %v", err)
	}

	return client
}

func getSingleCity(ctx context.Context, client *firestore.Client) {

	document, err := client.Collection("cities").Doc("SF").Get(ctx)
	if err != nil {
		log.Fatalf("Failed to get document: %v", err)
	}
	data := document.Data()
	log.Printf("Document data: %#v\n", data)
}

func getAllDocuments(ctx context.Context, client *firestore.Client) {

	iter := client.Collection("cities").Documents(ctx)
	printDocuments(iter)
}

func simpleCollectionsQuery(ctx context.Context, client *firestore.Client) {

	iter := client.Collection("cities").Where("capital", "==", true).Documents(ctx)
	printDocuments(iter)
}

func compoundQuery(ctx context.Context, client *firestore.Client) {
	iter := client.Collection("cities").Where("capital", "==", true).Where("country", "==", "USA").Documents(ctx)

	printDocuments(iter)
}

func inequalityQuery(ctx context.Context, client *firestore.Client) {
	iter := client.Collection("cities").Where("state", "!=", "AK").Documents(ctx)
	printDocuments(iter)
}

func disjunctionQuery(ctx context.Context, client *firestore.Client) {
	q1 := firestore.PropertyFilter{
			Path:     "population",
			Operator: ">=",
			Value:    10000000,
	}

	q2 := firestore.PropertyFilter{
			Path:     "regions",
			Operator: "array-contains",
			Value:    "west_coast",
	}

	orFilter := firestore.OrFilter{
			Filters: []firestore.EntityFilter{q1, q2},
	}

	orQuery := client.Collection("cities").WhereEntity(orFilter)
	iter := orQuery.Documents(ctx)

	printDocuments(iter)
}

func collectionGroupQuery(ctx context.Context, client *firestore.Client) {

	iter := client.CollectionGroup("landmarks").Where("type", "==", "museum").Documents(ctx)
	printDocuments(iter)
}

func printDocuments(iter *firestore.DocumentIterator) {
	for {
		doc, err := iter.Next()
		if err == iterator.Done {
			break
		}
		if err != nil {
			log.Fatalf("Failed to get documents: %v", err)
		}
		log.Println(doc.Data())
	}
}

func orderingLimitQuery(ctx context.Context, client *firestore.Client) {

	iter := client.Collection("cities").OrderBy("name", firestore.Desc).Limit(2).Documents(ctx)
	printDocuments(iter)
}

func createDocument(ctx context.Context, client *firestore.Client) {

	data := map[string]interface{}{
			"name":    "Rome",
			"capital":   true,
			"country": "Italy",
			"regions": []string{"lazio", "centro"},
			"population": 3000000,
		}

	//in case already exists, complete override -> remove not declared existing field
	_, err := client.Collection("cities").Doc("RM").Set(ctx, data)

	if err != nil {
		log.Fatalf("An error has occurred: %s", err)
	}
}

func mergeDocument(ctx context.Context, client *firestore.Client) {

	data := map[string]interface{}{
			"name":    "Rome",
			"country": "Italy",
			"population": 3000000,
		}

	//in case already exists, override only declared values
	_, err := client.Collection("cities").Doc("RM").Set(ctx, data, firestore.MergeAll)

	if err != nil {
		log.Fatalf("An error has occurred: %s", err)
	}
}

func updateDocument(ctx context.Context, client *firestore.Client) {

	_, err := client.Collection("cities").Doc("RM").Update(ctx, []firestore.Update{
		{
			Path: "capital",
			Value: false,
		},
		{
			Path: "timestamp",
            Value: firestore.ServerTimestamp, //timestamp from the server
		},
		{
			Path: "regions",
			Value: firestore.ArrayRemove("centro"), //array example
		},
	})

	if err != nil {
		log.Fatalf("An error has occurred: %s", err)
	}
}

//single document field deletion
func deleteDocumentFields(ctx context.Context, client *firestore.Client) {

	_, err := client.Collection("cities").Doc("RM").Update(ctx, []firestore.Update{
	   {
			   Path:  "capital",
			   Value: firestore.Delete,
	   },
	})

	if err != nil {
		log.Fatalf("An error has occurred: %s", err)
	}
}

func deleteDocument(ctx context.Context, client *firestore.Client) {
	_, err := client.Collection("cities").Doc("RM").Delete(ctx)

	if err != nil {
		log.Fatalf("An error has occurred: %s", err)
	}
}

func batchWrite(ctx context.Context, client *firestore.Client) {
	bulkWriter := client.BulkWriter(ctx)

	bulkWriter.Update(client.Collection("cities").Doc("RM"), []firestore.Update{
	   {
		   Path:  "capital",
			Value: false,
	   },
	})

	bulkWriter.Update(client.Collection("cities").Doc("LA"), []firestore.Update{
	   {
		   Path: "timestamp",
           Value: firestore.ServerTimestamp, //timestamp from the server
	   },
	})

	bulkWriter.Flush()
}

func transactionOperation(ctx context.Context, client *firestore.Client) {

	cityRef := client.Collection("cities").Doc("RM")

	err := client.RunTransaction(ctx, func(ctx context.Context, transaction *firestore.Transaction) error {
		doc, err := transaction.Get(cityRef)
		if err != nil {
			return err
		}
		pop, err := doc.DataAt("population")
		if err != nil {
			return err
		}
		return transaction.Set(cityRef, map[string]interface{}{
			"population": pop.(int64) + 1,
		}, firestore.MergeAll)
	})

	if err != nil {
		// Handle any errors appropriately in this section.
		log.Printf("An error has occurred: %s", err)
	}

}