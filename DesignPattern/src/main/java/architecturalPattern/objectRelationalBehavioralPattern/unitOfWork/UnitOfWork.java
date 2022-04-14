package architecturalPattern.objectRelationalBehavioralPattern.unitOfWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.jsonwebtoken.lang.Assert;

public class UnitOfWork {

	private static ThreadLocal current = new ThreadLocal();
	
	private List newObject = new ArrayList<>();
	private List dirtyObject = new ArrayList<>();
	private List removedObject = new ArrayList<>();
	
	/*
	 * Unit of work belong to a thread session
	 */
	
	public static void newCurrent() {
		current.set(new UnitOfWork());
	}
	
	public static UnitOfWork getCurrent() {
		return (UnitOfWork) current.get();
	}
	
	/*
	 * Register methods
	 */
	
	public void registerNew(DomainObject obj) {
		
		Assert.notNull(obj.getId(), "id now null");
		Assert.isTrue(!dirtyObject.contains(obj), "object not dirty");
		Assert.isTrue(!removedObject.contains(obj), "object not removed");
		Assert.isTrue(!newObject.contains(obj), "object not already registered new");
		
		newObject.add(obj);;
	}
	
	public void registerDirty(DomainObject obj) {
		
		Assert.notNull(obj.getId(), "id now null");
		Assert.isTrue(!removedObject.contains(obj), "object not removed");

		if(!dirtyObject.contains(obj) &&  !newObject.contains(obj)) {
			dirtyObject.add(obj);
		}
	}
	
	public void registerRemoved(DomainObject obj) {
		
		Assert.notNull(obj.getId(), "id now null");
		Assert.isTrue(!removedObject.contains(obj), "object not removed");
		if(newObject.remove(obj)) {
			return; //new object already removed
		}
		dirtyObject.remove(obj);
		if(!removedObject.contains(obj)) {
			removedObject.add(obj);
		}
	}
	
	public void registerClean(DomainObject obj) {
		//clean identity map logic
	}
	
	/*
	 * commit methods
	 */
	
	//update persistence
	public void commit() {
		
		insertNew();
		updateDirty();
		deleteRemoved();
	}
	
	private void insertNew() {
		for(Iterator objects = newObject.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			//persistence insertion logic
		}
	}
	
	private void updateDirty() {
		for(Iterator objects = dirtyObject.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			//persistence update logic
		}
	}
	
	private void deleteRemoved() {
		for(Iterator objects = removedObject.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			//persistence delete logic
		}
	}
	
	
}
