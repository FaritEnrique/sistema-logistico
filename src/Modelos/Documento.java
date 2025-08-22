package Modelos;

public abstract class Documento implements IDocumento {
    protected String codigo;

    public Documento(String codigo) { this.codigo = codigo; }

    @Override
    public String getCodigo() { return codigo; }
}