package client;

import org.apache.olingo.commons.api.edm.*;

import java.util.ArrayList;
import java.util.List;

public class EdmInsperctor {

	private Edm edm;

	public EdmInsperctor(Edm edm) {
		this.edm = edm;
	}

	public void readEdmInfo() {

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

		System.out.println("\n----- Inspect each property and its type For all complex types ----");
		for (FullQualifiedName name : complexTypeNames) {
			EdmComplexType ttype = edm.getComplexType(name);

			System.out.println("\n-----Complex Type: " + name + " ----");
			printStructureTypePropertiesName(ttype);
		}

		System.out.println("\n----- Inspect each property and its type For all entities ----");
		for (FullQualifiedName name : entityTypeNames) {
			EdmEntityType etype = edm.getEntityType(name);

			System.out.println("\n-----Entity: " + name + " ----");
			printStructureTypePropertiesName(etype);
		}
	}

	private void printStructureTypePropertiesName(EdmStructuredType type) {
		for (String propertyName : type.getPropertyNames()) {
			EdmProperty property = type.getStructuralProperty(propertyName);
			FullQualifiedName typeName = property.getType().getFullQualifiedName();
			System.out.println("property '" + propertyName + "' " + typeName);
		}
	}
}
