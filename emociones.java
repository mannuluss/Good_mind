
public enum emociones {
    triste(-1), enojado(-1), nada(0) ,timido(1);
    private int tipo;
    private emociones(int tipo){
        this.tipo = tipo;
    }
    public int GetType(){
        return tipo;
    }
}
