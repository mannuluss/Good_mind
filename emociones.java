
public enum emociones {
    triste(-1), enojado(-1), indiferente(0) ,feliz(1), exaltado(1);
    private int tipo;
    private emociones(int tipo){
        this.tipo = tipo;
    }
    public int GetType(){
        return tipo;
    }
}
