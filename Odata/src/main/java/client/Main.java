package client;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientPrimitiveValue;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.*;
import org.apache.olingo.commons.api.format.ContentType;

import java.util.ArrayList;
import java.util.List;
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
		readEdmInfo(edm);

		readPeopleEntrySet(client);

		readPeopleEntry(client, "russellwhyte");

		Optional<EdmEntityType> optionalType = edm.getSchema("Trippin").getEntityTypes().stream().filter(x -> x.getName().equals("Person")).findFirst();

		createNewEntity(client, optionalType.get().getFullQualifiedName());
	}

	private static void readEdmInfo(Edm edm) {

		System.out.println("\n----- Read Edm ------------------------------");
		List<FullQualifiedName> complexTypeNames = new ArrayList<>();
		List<FullQualifiedName> entityTypeNames = new ArrayList<>();
		List<String> namespaces = new ArrayList<>();

		for (EdmSchema schema : edm.getSchemas()) {
			namespaces.add(schema.getNamespace());
			for (EdmComplexType complexType : schema.getComplexTypes()) {
				complexTypeNames.add(complexType.getFullQualifiedName());
			}
			for (EdmEntityType entityType : schema.getEntityTypes()) {
				entityTypeNames.add(entityType.getFullQualifiedName());
			}
		}
		System.out.println("Found namespaces " + namespaces.toString());
		System.out.println("Found ComplexTypes " + complexTypeNames.toString());
		System.out.println("Found EntityTypes " + entityTypeNames.toString());

		System.out.println("\n----- Inspect each property and its type For all entities ----");
		for(FullQualifiedName name : entityTypeNames) {
			EdmEntityType etype = edm.getEntityType(name);
			System.out.println("\n-----Entity: " + name + " ----");

			for (String propertyName : etype.getPropertyNames()) {
				EdmProperty property = etype.getStructuralProperty(propertyName);
				FullQualifiedName typeName = property.getType().getFullQualifiedName();
				System.out.println("property '" + propertyName + "' " + typeName);
			}
		}

	}

	/*
		Read set of entities
	 */
	private static void readPeopleEntrySet(ODataClient client) {
		ODataEntitySetRequest<ClientEntitySet> requestSet = client.getRetrieveRequestFactory()
				.getEntitySetRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People").build());

		ODataRetrieveResponse<ClientEntitySet> responseSet = requestSet.execute();
		ClientEntitySet entitySet = responseSet.getBody();

		System.out.println(entitySet.getEntities().size());
		System.out.println(entitySet.getEntities().get(0).getProperties().get(0).getName());
		System.out.println(entitySet.getEntities().get(0).getProperties().get(0).getValue().toString());
	}

	/*
		Read single entity, need to specify the key
	 */
	private static void readPeopleEntry(ODataClient client, String key) {

		ODataEntityRequest<ClientEntity> request = client.getRetrieveRequestFactory()
				.getEntityRequest(client.newURIBuilder(SERVICE_BASE_URL)
						.appendEntitySetSegment("People")
						.appendKeySegment(key) //key of entity
						.build());

		ODataRetrieveResponse<ClientEntity> response = request.execute();
		ClientEntity entity = response.getBody();

		System.out.println(entity.getProperties().get(0).getName());
		System.out.println(entity.getProperties().get(0).getValue().toString());
	}

	/*
		create a single entity
	 */
	private static void createNewEntity(ODataClient client, FullQualifiedName entityFullQualifiedName) {

		ClientEntity newEntity = client.getObjectFactory().newEntity(entityFullQualifiedName);
		newEntity.getProperties().add(client.getObjectFactory().newPrimitiveProperty("UserName", client.getObjectFactory().newPrimitiveValueBuilder().buildString("newUser")));

		client.getCUDRequestFactory().getEntityCreateRequest(
				client.newURIBuilder(SERVICE_BASE_URL).appendEntitySetSegment("People").build(),
				newEntity
		).execute();
	}
}
