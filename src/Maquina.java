public class Maquina {
    private String nombre;
    private String departamento;
    private boolean funcionando;

    public Maquina(String nombre, String departamento, boolean funcionando) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.funcionando = funcionando;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

    @Override
    public String toString() {
        String estado = funcionando ? "Funcionando" : "Fuera de Servicio";
        return "Máquina: " + nombre + " | Departamento: " + departamento + " | Estado: " + estado;
    }
}