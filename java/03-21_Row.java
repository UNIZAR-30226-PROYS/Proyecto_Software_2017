package barbarahliskov.cambialibros;

/* Clase que representa la fila de una lista */

public class Row {
    private String titulo;
    private String autor;
    private String usuario;
    private long dist;

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

    /**
     *
     * @param d localizacion del libro
     */
    void setDist(long d){
        this.dist = d;
    }
}
