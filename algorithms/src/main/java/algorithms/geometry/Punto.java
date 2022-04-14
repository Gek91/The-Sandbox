package algorithms.geometry;

public class Punto implements Comparable<Punto>{
   private int x; //coordinata x
   private int y; //coordinata y
   
   public Punto(int a, int b){
       this.x = a;
       this.y = b;
   }
   
   public Punto(Punto pPunto){
       this.x = pPunto.getx();
       this.y = pPunto.gety();
   }
   
   //getter
   public int getx(){
       return this.x;
   }
   
   public int gety(){
       return this.y;
   }
   
   public boolean equals(Object obj){
       Punto p = (Punto) obj;
       if( this.x == p.getx() )
           if( this.y == p.gety() )
               return true;
       return false;
   }
   
   @Override
   public int compareTo(Punto other){
       if( this.x < other.getx() ){ //this < other
    	   return -1;
       } else if( this.x == other.getx() ){ //this x == other x
    	   if( this.y < other.gety() ){ //this < other
    		   return -1;
    	   } else if(this.y == other.gety() ){ // this x,y == other x,y 
    		   return 0;
    	   } else { //this > other
    		   return 1;
    	   }	
       } else { // this > other
    	   return 1;
       }
   }
}
