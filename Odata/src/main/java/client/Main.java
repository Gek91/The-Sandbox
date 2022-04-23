package client;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.cud.UpdateType;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataEntityCreateResponse;
import org.apache.olingo.client.api.communication.response.ODataEntityUpdateResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.*;
import org.apache.olingo.commons.api.format.ContentType;

import java.util.Optional;

public class Main {

	private static final String SERVICE_BASE_URL = "https://services.odata.org/TripPinRESTierService";

	public static void main(String[] args) {

		ODataClient client = ODataClientFactory.getClient();
		client.getConfiguration().setDefaultPubFormat(ContentType.APPLICATION_JSON);
		//client.getConfiguration()
		//		.setHttpClientFactory(new BasicAuthHttpClientFactory("user", "pass"));

		//read EMD ($metadata)
		//Entity Data Model defines alla metadata information about the providede data of the service
		//required for serialization and deserialization of the data of an entity
		Edm edm = client.getRetrieveRequestFactory().getMetadataRequest(SERVICE_BASE_URL).execute().getBody();
		new EdmInsperctor(edm).readEdmInfo();

		//retrieving
		readPeopleEntrySet(client);

		readPeopleEntrySetUsingPagination(client);

		readPeopleEntrySetUsingSorting(client);

		readPeopleEntrySetUsingFiltering(client);

		//Not sure how it works
		readPeopleEntrySetUsingFullTextSearch(client);

		readPeopleEntry(client, "russellwhyte");

		readPeopleEntryWithProjection(client, "russellwhyte");

		//manipolation
		Optional<EdmEntityType> optionalType = edm.getSchema("Trippin").getEntityTypes().stream().filter(x -> x.getName().equals("Person")).findFirst();

		createPeopleNewEntity(client, optionalType.get().getFullQualifiedName());

		patchPeopleEntity(client, optionalType.get().getFullQualifiedName(),"russellwhyte");

		deletePeopleEntity(client,"russellwhyte");
	}

	private static void readPeopleEntrySet(ODataClient client) {
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People").build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		System.out.println("Entities count: " + entitySet.getEntities().size());
		entitySet.getEntities().forEach(entity -> {
			System.out.println("Name: " + entity.getProperties().get(0).getName());
			System.out.println("Value " + entity.getProperties().get(0).getValue().toString());
		});
	}

	private static void readPeopleEntrySetUsingPagination(ODataClient client) {

		System.out.println("Paginated request, offset 0, top 3");
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.count(true) //count total elements
						.top(3) //max number of element to return
						.skip(0) //offset
						.build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		System.out.println("Entities count value: " + entitySet.getCount());
		System.out.println("Entities count: " + entitySet.getEntities().size());
		entitySet.getEntities().forEach(entity -> {
			System.out.println("Name: " + entity.getProperties().get(0).getName());
			System.out.println("Value " + entity.getProperties().get(0).getValue().toString());
		});

		System.out.println("Paginated request, offset 2, top 5");
		requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.count(true) //count total elements+
						.top(5) //max number of element to return
						.skip(2) //offset
						.build());

		responseSet = requestSet.execute();
		entitySet = responseSet.getBody();

		System.out.println("Entities count value: " + entitySet.getCount());
		System.out.println("Entities count: " + entitySet.getEntities().size());
		entitySet.getEntities().forEach(entity -> {
			System.out.println("Name: " + entity.getProperties().get(0).getName());
			System.out.println("Value " + entity.getProperties().get(0).getValue().toString());
		});
	}

	private static void readPeopleEntrySetUsingSorting(ODataClient client) {

		System.out.println("Sorting request, FirstName");
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.orderBy("FirstName")
						.top(5) //max number of element to return
						.build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		entitySet.getEntities().forEach(entity -> {
			System.out.println("FirstName: " + entity.getProperties().stream().filter(property -> property.getName().equals("FirstName")).map(property -> property.getValue()).findFirst().get());
			System.out.println("LastName: " + entity.getProperties().stream().filter(property -> property.getName().equals("LastName")).map(property -> property.getValue()).findFirst().get());
		});

		System.out.println("Sorting request, LastName");
		requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.orderBy("LastName")
						.top(5) //max number of element to return
						.build());

		responseSet = requestSet.execute();
		entitySet = responseSet.getBody();

		entitySet.getEntities().forEach(entity -> {
			System.out.println("FirstName: " + entity.getProperties().stream().filter(property -> property.getName().equals("FirstName")).map(property -> property.getValue()).findFirst().get());
			System.out.println("LastName: " + entity.getProperties().stream().filter(property -> property.getName().equals("LastName")).map(property -> property.getValue()).findFirst().get());
		});
	}

	private static void readPeopleEntrySetUsingFiltering(ODataClient client) {

		System.out.println("Filtering request, FirstName eq 'Javier' or LastName eq 'Barlow'");
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.filter("FirstName eq 'Javier' or LastName eq 'Barlow'")
						.build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		entitySet.getEntities().forEach(entity -> {
			System.out.println("FirstName: " + entity.getProperties().stream().filter(property -> property.getName().equals("FirstName")).map(property -> property.getValue()).findFirst().get());
			System.out.println("LastName: " + entity.getProperties().stream().filter(property -> property.getName().equals("LastName")).map(property -> property.getValue()).findFirst().get());
		});

	}

	private static void readPeopleEntrySetUsingFullTextSearch(ODataClient client) {

		System.out.println("Searching fulltext, Geo or Ja");
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.search(client.getSearchFactory().or(
									client.getSearchFactory().literal("Barlow"),
									client.getSearchFactory().literal("Javier")
									)
								)
						.build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		entitySet.getEntities().forEach(entity -> {
			System.out.println("FirstName: " + entity.getProperties().stream().filter(property -> property.getName().equals("FirstName")).map(property -> property.getValue()).findFirst().get());
			System.out.println("LastName: " + entity.getProperties().stream().filter(property -> property.getName().equals("LastName")).map(property -> property.getValue()).findFirst().get());
		});

	}

	private static void readPeopleEntry(ODataClient client, Object key) {

		ODataEntityRequest<ClientEntity> request = client.getRetrieveRequestFactory()
				.getEntityRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.appendKeySegment(key) //key of entity
						.build());

		ODataRetrieveResponse<ClientEntity> response = request.execute();
		ClientEntity entity = response.getBody();

		System.out.println("Properties count " + entity.getProperties().size());
		System.out.println("Name: " + entity.getProperties().get(0).getName());
		System.out.println("Value " + entity.getProperties().get(0).getValue().toString());
	}

	private static void readPeopleEntryWithProjection(ODataClient client, Object key) {

		ODataEntityRequest<ClientEntity> request = client.getRetrieveRequestFactory()
				.getEntityRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.appendKeySegment(key) //key of entity
						.select("LastName")
						.build());

		ODataRetrieveResponse<ClientEntity> response = request.execute();
		ClientEntity entity = response.getBody();

		System.out.println("Properties count " + entity.getProperties().size());
		System.out.println("Name: " + entity.getProperties().get(0).getName());
		System.out.println("Value " + entity.getProperties().get(0).getValue().toString());
	}


	private static void createPeopleNewEntity(ODataClient client, FullQualifiedName entityFullQualifiedName) {

		//complex value building
		ClientComplexValue locationValue = client.getObjectFactory().newComplexValue("Trippin.Location");
		locationValue.add(client.getObjectFactory().newPrimitiveProperty("Address", client.getObjectFactory().newPrimitiveValueBuilder().buildString("via roma 1")));
		locationValue.add(client.getObjectFactory().newPrimitiveProperty("City", client.getObjectFactory().newPrimitiveValueBuilder().buildString("Milano")));

		ClientEntity newEntity = client.getObjectFactory().newEntity(entityFullQualifiedName);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"UserName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("newUser"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"FirstName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("Gino"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"LastName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("Gini"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newComplexProperty("AddressInfo", locationValue)); //complex field

		ODataEntityCreateResponse<ClientEntity> people = client.getCUDRequestFactory().getEntityCreateRequest(
				client.newURIBuilder(SERVICE_BASE_URL).appendEntitySetSegment("People").build(),
				newEntity
		).execute();

		//Jackson error, maybe because of public endpoint usage
		//System.out.println(people.getBody().getProperty("UserName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("FirstName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("LastName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("AddressInfo").getComplexValue().get("Address").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("AddressInfo").getComplexValue().get("City").getPrimitiveValue().toValue());
	}

	private static void patchPeopleEntity(ODataClient client, FullQualifiedName entityFullQualifiedName, Object entitykey) {

		ClientComplexValue locationValue = client.getObjectFactory().newComplexValue("Trippin.Location");
		locationValue.add(client.getObjectFactory().newPrimitiveProperty("Address", client.getObjectFactory().newPrimitiveValueBuilder().buildString("via roma 1")));
		locationValue.add(client.getObjectFactory().newPrimitiveProperty("City", client.getObjectFactory().newPrimitiveValueBuilder().buildString("Milano")));

		ClientEntity newEntity = client.getObjectFactory().newEntity(entityFullQualifiedName);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"UserName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("newUser"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"FirstName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("Gino"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty( //primitive field
				"LastName",
				client.getObjectFactory().newPrimitiveValueBuilder().buildString("Gini"))
		);
		newEntity.getProperties().add(client.getObjectFactory().newComplexProperty("AddressInfo", locationValue)); //complex field

		ODataEntityUpdateResponse<ClientEntity> people = client.getCUDRequestFactory().getEntityUpdateRequest(
				client.newURIBuilder(SERVICE_BASE_URL).appendEntitySetSegment("People").appendKeySegment(entitykey).build(),
				UpdateType.PATCH,
				newEntity
		).execute();

		//Jackson error, maybe because of public endpoint usage
		//System.out.println(people.getBody().getProperty("UserName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("FirstName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("LastName").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("AddressInfo").getComplexValue().get("Address").getPrimitiveValue().toValue());
		//System.out.println(people.getBody().getProperty("AddressInfo").getComplexValue().get("City").getPrimitiveValue().toValue());
	}

	private static void deletePeopleEntity(ODataClient client, Object entitykey) {

		client.getCUDRequestFactory().getDeleteRequest(
				client.newURIBuilder(SERVICE_BASE_URL).appendEntitySetSegment("People").appendKeySegment(entitykey).build()
		).execute();
	}
}
