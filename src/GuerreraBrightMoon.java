public class GuerreraBrightMoon {
    private int id;
    private String alias;
    private String poderBatalla;
    private int nivelEstrategia;
    private String ubicacion;
    GuerreraBrightMoon anterior;
    GuerreraBrightMoon siguiente;

    public GuerreraBrightMoon(int id, String alias, String poderBatalla, int nivelEstrategia, String ubicacion) {
        this.id = id;
        this.alias = alias;
        this.poderBatalla = poderBatalla;
        this.nivelEstrategia = nivelEstrategia;
        this.ubicacion = ubicacion;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPoderBatalla() {
        return poderBatalla;
    }

    public void setPoderBatalla(String poderBatalla) {
        this.poderBatalla = poderBatalla;
    }

    public int getNivelEstrategia() {
        return nivelEstrategia;
    }

    public void setNivelEstrategia(int nivelEstrategia) {
        this.nivelEstrategia = nivelEstrategia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


}
