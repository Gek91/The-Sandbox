package algorithms.geometry;


public class Evento implements Comparable<Evento>{
    private Segmento s;
    private boolean e;
    
    public Evento(Segmento pSegmento, boolean pBool){
        this.e = pBool;
        this.s = pSegmento; //T= inizio , F = fine
    }
    
    public Segmento gets(){
        return this.s;
    }
    
    public boolean gete(){
        return this.e;
    }
    
    @Override
    public int compareTo(Evento ev){
        int lIntx1;
        int lIntx2;
        //inizializzo lInt1 con coordinata x
        if(this.e){ //capo o coda
            lIntx1 = this.s.geti().getx();
        } else {
            lIntx1 = this.s.getf().getx();
        }
        //inizializzo lInt2 con coordinata x
        if(ev.gete()){ //Capo o coda
            lIntx2 = ev.gets().geti().getx();
        } else {
            lIntx2 = ev.gets().getf().getx();
        }
        
        if(lIntx1 < lIntx2){ //confronta coordinata x
            return -1;
        } else {
            if(lIntx1 == lIntx2){ //x uguale nei due eventi
                if( this.e != ev.gete() ){ //Se sono estremi diversi
                    if(this.e) //più piccolo è sinistro
                        return -1;
                    else 
                        return 1;
                } else { //se sono estremi uguali
                    int lInty1;
                    int lInty2;
                    if(this.e){ //inizializzo lInty1 e lInty2 con coordinata y
                        lInty1 = this.s.geti().gety();
                        lInty2 = ev.gets().geti().gety();
                    } else {
                        lInty1 = this.s.getf().gety();
                        lInty2 = ev.gets().getf().gety();
                    }
                    
                    if(lInty1 < lInty2) //confronta coordinata y
                        return -1;
                    else if(lInty1 == lInty2)
                    	return 0;
                    	else
                    		return 1;
                }
            } else { //se non uguale e minore il parametro
                return 1;
            }
        }
    }
}


