package designPattern.behavioralPattern;

public class Memento {

	public static void main(String[] args) {
		
		Editor editor = new Editor();
		
		editor.setState("before memento");
		
		System.out.println(editor.getState());
		
		EditorMemento memento = editor.save();
	
		editor.setState("after memento");
		
		System.out.println(editor.getState());
		
		editor.restoreToState(memento);
		
		System.out.println(editor.getState());
	}
}

//Memento
class EditorMemento {
	
	  private final String editorState;
	  
	  public EditorMemento(String state) {
	    editorState = state;
	  }
	  
	  public String getSavedState() {
	    return editorState;
	  }
}

//Originator
class Editor {

	public String editorContents;
	
	public void setState(String contents) {
		this.editorContents = contents;
	}
	
	public String getState() {
		return this.editorContents;
	}
	
	public EditorMemento save() {
		return new EditorMemento(editorContents);
	}
	
	public void restoreToState(EditorMemento memento) {
		editorContents = memento.getSavedState();
	}
}

