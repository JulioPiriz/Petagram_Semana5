package uy.drako.petagram.pojo;

public class Mascotas {
    private int id;
    private int foto;
    private String nombre;
    private int like;

    public Mascotas(int foto, String nombre, int like) {
        this.nombre = nombre;
        this.foto = foto;
        this.like = like;
    }

    public Mascotas() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
        if(this.like <= 0)
        {
            this.like = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
