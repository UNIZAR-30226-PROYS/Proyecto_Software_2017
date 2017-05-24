package barbarahliskov.cambialibros;

/* Clase que representa la fila de una lista */

public class Row {
    private String titulo;
    private String autor;
    private String usuario;
    private String city;
    private long dist;
    private int id;

    /** Constructor
     * @param t titulo del libro
     * @param a autor del libro
     * @param u usuario del libro
     * @param d localizacion del libro
     */

    Row (String t, String a, String u, Long d){
        titulo = t;
        autor = a;
        usuario = u;
        dist = d;
        id = -1;
    }
    Row (String t, String a, String u, String d){
        titulo = t;
        autor = a;
        usuario = u;
        city = d;
        id = -1;
    }
    Row (String t, String a, String u, Long d, int i){
        titulo = t;
        autor = a;
        usuario = u;
        dist = d;
        id = i;
    }

    /**
     *
     * @return titulo del libro
     */

    String getTitulo(){
        return this.titulo;
    }

    /**
     *
     * @param t titulo del libro
     */
    void setTitulo(String t){
        this.titulo = t;
    }

    /**
     *
     * @return autor del libro
     */
    String getAutor(){
        return this.autor;
    }

    /**
     *
     * @param a autor del libro
     */
    void setAutor(String a){
        this.autor = a;
    }

    /**
     *
     * @return usuario del libro
     */
    String getUsuario(){
        return this.usuario;
    }

    /**
     *
     * @param u usuario del libro
     */
    void setUsuario(String u){
        this.usuario = u;
    }

    /**
     *
     * @return localizacion del libro
     */
    long getDist(){
        return this.dist;
    }


    String getCity(){
        return this.city;
    }

    void setDist(long d){
        this.dist = d;
    }

    public int getId() {return id; }

    public void setId(int i){this.id = i;}
}
