package algorithms.geometry;


public class Segmento implements Comparable<Segmento>{
   private Punto i; //Punto inizo
   private Punto f; //Punto fine
   
   //Costruttori
   public Segmento(int xa, int ya, int xb, int yb ){
       this.i = new Punto(xa,ya);
       this.f = new Punto(xb,yb);
   }
   
   public Segmento(Punto a, Punto b){
       this.i = a;
       this.f = b;
   }
   
   public Segmento(Segmento s){
       this.i = s.geti();
       this.f = s.getf();
   }
   
   //Setter
   public void seti(Punto pPunto){
       this.i = pPunto;
   }
   public void setf(Punto pPunto){
       this.f = pPunto;
   }
   
   //Getter
   public Punto geti(){
       return this.i;
   }
   public Punto getf(){
       return this.f;
   }
   
   @Override
   public int compareTo(Segmento other){
	    if( this.i.compareTo(other.geti()) < 0  ){ //this < other
	    	   return -1;
	       } else if( this.i.compareTo(other.geti()) == 0 ){ //this x == other x
	    	   if( this.f.compareTo(other.getf()) < 0 ){ //this < other
	    		   return -1;
	    	   } else if(this.f.compareTo(other.getf()) == 0 ){ // this x,y == other x,y 
	    		   return 0;
	    	   } else { //this > other
	    		   return 1;
	    	   }	
	       } else { // this > other
	    	   return 1;
	       }
   }
   
   //Equals override
   @Override
   public boolean equals(Object obj){
	   Segmento lSegmento = (Segmento) obj;
       if( i.equals(lSegmento.geti()) )
           if( f.equals(lSegmento.getf()) )
               return true;
       return false;
   }
}
