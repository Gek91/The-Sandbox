package algorithms.patternMatching;

public class PatternMatching {

	public static void ingenuo(String P, String T){
	    int m=P.length(); //grandezza stringa P
	    int n=T.length(); //grandezza stringa T
	    for (int i=0; i<n-m+1; i++)  {//scorre il testo T
	        int j=0;
	        while(j<m && P.charAt(j) == T.charAt(i+j)) { //se c'è matching
	            j=j+1;
	        }
	        if(j>=m) //condizione di terminazione di occorrenza completa
	        	System.out.println("Occorrenza in posizione " + i);
	    }
	    System.out.println("Termine");
	}


	////////////////////////////////////////////////////////////////////////////////////////////

	public static void ingenuoSentinella(String P, String T){
	    int m=P.length(); //grandezza stringa P
	    int n=T.length(); //grandezza stringa T
	    P=P+'$'; //allungata con sentinella
	    T=T+'x'; //allungata con carattere diverso da sentinella
	    for (int i=0; i<n-m+1; i++){ //scorre il testo T
	        int j=0;
	        while(P.charAt(j)==T.charAt(i+j)){ //se c'è matching
	            j=j+1;
	        }
	        if(j>=m) //condizione di terminazione di occorrenza completa
	            System.out.println("Occorrenza in posizione " + i);
	    }
	    System.out.println("Termine");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	public static void KnuthMorrisPratt(String P, String T) {
	    int m=P.length(); //grandezza stringa P
	    int n=T.length(); //grandezza stringa T
	    int[] d=new int[m+1]; //d[j] indicano gli spostamenti che si effettueranno una volta trovato un mismatch all'interno di un occorrenza. I casi sono due: nuova occorrenza prima dell'indice di mismatch di quella precedente oppure occorrenza oltre quell'indice
	    P=P+'$'; //allungata con sentinella
	    T=T+'x'; //allungata con carattere diverso da sentinella
	    //FASE PREELABORAZIONE O(m): calcola i prefissi e i valori di d[j]
	    int[] pref=funzionePrefisso(P); //calcola valori prefissi di P

	    //i due cicli coalcolano d[j]=min({j} U {h | 1<=h<j, j=1+h+pref[1+h]}) che realizzano le due casistiche(j=occorrenza oltre l'indice di mismatch j, altrimenti se la condizione è verificata si avrò un occorrenza in posizione 1+h <j in P )
	    for (int j=0; j<=m; j++){ //si assegna a d[j] il valore j
	        d[j]=j;
	    }
	    for (int h=m-1; h>=0; h--) { //aggiorno d[j] se trovo un h con-> j=1+h+pref[1+h]
	        d[1+h+pref[1+h]]=h;
	    }
	    
	    //FASE RICERCA O(n)
	    int j=0; // indice che scorre P
	    int i=j; //indice che scorre T
	    while (i<=n-m)
	    {
	        while(P.charAt(j)==T.charAt(i+j)){ //se trovo un match tra l'emento P e l'elemento T presi in considerazione
	            j=j+1; //confronto i caratteri successivi
	        }
	        if(j>=m){ //trovato un occorrenzza di P in T in posizione i
	            System.out.println("Occorrenza in "+i);
	        }
	        //l'aggiornamento dei due valori di i e j è in relazione al valore di d[j] calcolato nella preelaborazione. I due casi realizzando le situazione in cui la prossima occorrenza di P in T sia prima dell'indice in cui la precedenti ha avuto un mismatch (in questo caso i confronti partiranno da j in P) oppure nel caso l'occorrenzza successiva sia dopo e si continuano i confronti oltre quell'indice (dal primo elemento di P)
	        i=i+d[j]+1; //aggiorno i passando all'indice successivo in cui può esserci un occorrenza
	        j=Math.max(0,j-d[j]-1); //scelgo l'indice in P in relazione al caso
	    }
	    System.out.println("Termine");
	}
	
	//calcola tutti i prefissi di una stringa che si ripetono in essa e li ritorna in un array lungo quanto la stringa in cui ogni elemento contiene la lunghezza del prefisso che inizia in quell'elemento della stringa. Per ogni indice della stringa calcola il prefiso più lungo che inizia in quell'indice in esso.
	private static int[] funzionePrefisso(String S) { 
	    int n=S.length();
	    int[] pref=new int[n];        // array contenente il risultato
	    pref[0]=n;          //lunghezza del prefisso del primo carattere è tutta la stringa
	    int h=0;            //lunghezza prefisso preso in esame
	    int l,r;            //variabili di supporto che conterranno i limiti dei prefissi trovati, r è l'estremo massimo destro trovato per un prefisso(valore destro più alto), li è l'estremo sinistro corrispondente
	    while(S.charAt(0+h)==S.charAt(1+h)){   //caso prefisso che inizia dal secondo carattere
	        h=h+1;
	    }
	    pref[1]=h;
	    l=1;
	    r=1+h-1;                //inizializzazione r l con i valori relativi al prefisso del secondo carattere
	    for (int i=2; i<n; i++){ //scorre tutta la stringa
	        if(r<i) {           //caso 1: il prefisso precedente termina prima dell'emento preso in analisi. si calcola direttamente la lunghezza del prefisso e si aggiornano i valori di r e l
	            h=0;
	            while ( (i+h < n) && (S.charAt(h) == S.charAt(i+h)) ){ //confronto carattere per carattere fino mismatch
	                h=h+1;
	            }
	            pref[i]=h;      //aggiorna i valori di pref,l ed r
	            l=i;
	            r=i+h-1;
	        } //gli altri casi il prefisso termina oltre all'elemento preso in analisi, quindi lo contiene
	        else if(pref[i-l]<r-i+1){      //caso 2a: si è all'interno di un prefisso precedente, il prefisso corrispondente (ad i-l+1) è gia stato calcolato poiché già trovato in precedenza ed è totalmente contenuto nel prefisso più a destra(<r-i+1). I valori di r e l non si migliorano
	            pref[i]=pref[i-l];
	        } else { //caso 2b: si è all'interno del prefisso precedente ma il prefisso corrispondente è già stato trovato in precedenza e termina oltre a quello che ne contiene l'inizio quindi si controlla se il prefisso relativo a questa posizione continui oltre a quello già trovato ad i-l+1 aggiornando r e l al termine.
	            h=r-i+1; //inizia il confronto dal primo carattere oltre a quelli gia sicuri (r+1)
	            while (S.charAt(h)==S.charAt(i+h)) {   //confronta fino ad un mismatch
	                h=h+1;
	            }
	            pref[i]=h;  //aggiorna i valor di r e l oltre a salvarsi la lunghezza del prefisso trovato in posizione i
	            l=i;
	            r=i+h-1;
	        }
	    }
	    return pref;
	}

	 // concatena P$T ed esegue la funzione prefisso, i matching sono i prefissi che si trovano in T di lunghezza pari a quella di P
	public static void matchingPrefisso(String P, String T) {
	    String input=P+'$'+T;
	    int[] res=funzionePrefisso(input);
	    int Plenght=P.length();
	    for(int i=0;i<input.length()-(Plenght+1);i++)
	    {
	        if(res[i+Plenght+1]==Plenght)
	            System.out.println("Occorrenza in "+i);
	    }
	    System.out.println("Termine");
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	public static void BoyerMoore(String P, String T) {
	    int m=P.length(); //grandezza stringa P
	    int n=T.length(); //grandezza stringa T
	    int[] d= new int[m+1]; //d[j] indicano gli spostamenti che si effettueranno una volta trovato un mismatch all'interno di un occorrenza. I casi sono due: nuova occorrenza prima dell'indice di mismatch di quella precedente oppure occorrenza oltre quell'indice
	    P='$'+ P; //allungata con sentinella
	    T='x'+ T; //allungata con carattere diverso da sentinella
	    String Pr=reverse(P);
	    
	    //PREELABORAZIONE, calcola i valori dello spostamento da effettuare quando si trova un mismatch. Lo spostamento calcolato varia in base alla possibile posizione della prossima occorrenza in T. Sarà pari ad min ({h | 1<=h<j, j=m - prefrr[h+1]} U {h | j<=h<=m, h=m - prefrr[h+1]} con prefr[] prefissi calcolati su Pr -> P rovesciato
	    int[] pref=funzionePrefisso(Pr); //calcola valori prefissi di P rovesciato
	    for (int j=m; j>=0; j--){// {h | 1<=h<j, j=m - prefr[1+h]}
	        int h=m;
	        if(j==m-pref[j])
	            h=j;
	        d[j]=h;
	    }
	    for (int h=m; h>=1; h--){// {h | j<=h<=m, h=m - prefr[1+h]}
	        int j=m-pref[h];
	        if (h<j)
	        {
	            d[j]=h;
	        }
	    }
	    
	    //RICERCA :fase di confronto caratteri, scorre lungo T e P fino a che non trova un mismatch, quando lo trova effettua lo spostamento in i mentre in j ricomincia dal primo carattere
	    int i=0;
	    while (i<=n-m){
	        int j=m;
	        while (P.charAt(j)==T.charAt(i+j)){ //fino a che non trova un mismatch
	            j=j-1; //carattere successivo sia in P che in T
	        }
	        if (j==0){ //trovata occorrenza
	            System.out.println("Occorrenza in " + i);
	        }
	        i=i+d[j]; //spostamento in caso di mismatch
	    }
	    System.out.println("Termine");
	}
	
	//restituisce la stringa in ingresso rovesciata
	private static String reverse(String S)  {
	    String H = "";
	    for(int i=0; i<S.length(); i++)
	    {
	        H = S.charAt(i) + H;
	    }
	    return H;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	public static void Shift_And(String P, String T) {
		
	    int m=P.length(); //grandezza stringa P
	    int n=T.length(); //grandezza stringa T
	    boolean U[][] = new boolean [126-33+1][m]; //Matrice che contiene i vettori U[j] di lunghezza m, contengono le posizioni nel pattern del catarrete j
	    boolean R[] = new boolean[m];
	    //Creazione vettori U[j]
	    for(int j=0; j<=126-33;j++) //Crea un vettore per ogni valore ascii da 33 a 126
	    {
	        for(int i=0;i<m;i++)
	        {
	            if(P.charAt(i)==(char)(j+33))
	                U[j][i]=true;
	            else
	                U[j][i]=false;
	        }
	    }
	    for(int i=0;i<m;i++) //Inizializza il vettore del risultato tutto a 0
	        R[i]=false;
	    
	    if(P.charAt(0)==T.charAt(0))
	        R[0]=true;
	    
	    for(int i=1; i < n; i++) //Ciclo per il calcolo del risultato
	    {
	        Bit_Shift(R,m); //Bitshift
	        for(int j=0; j<m; j++) //AND(R,U[T(i)])
	        {
	            R[j]=U[((int)(T.charAt(i)))-33][j] && R[j];
	        }
	        if(R[m-1]==true) //Condizione di occorrenza
	            System.out.println("Occorrenza in " + (i-m+1) );
	    }
	    System.out.println("Termine");
	}

	//Scifta il contenuto del vettore booleano di una casella verso destra. Ultimo elemento è perso, all'inizio è inserito un 1
	private static void Bit_Shift(boolean vec[],int n) {
	    boolean h1=vec[0];
	    boolean h2;
	    for( int i=1; i<n ; i++ ){
	        h2=vec[i];
	        vec[i]=h1;
	        h1=h2;
	    }
	    vec[0]= true;
	}
	
}
