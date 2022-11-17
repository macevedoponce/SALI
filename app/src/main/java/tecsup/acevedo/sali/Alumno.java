package tecsup.acevedo.sali;

public class Alumno {
    private Integer id;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer estado;
    private String fecha;
    private Integer id_alumno;

    public Alumno() {
    }


    public Alumno(Integer id, String dni, String nombres, String apellidoPaterno, String apellidoMaterno, Integer estado, String fecha, Integer id_alumno) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.fecha = fecha;
        this.id_alumno = id_alumno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", estado=" + estado +
                ", fecha='" + fecha + '\'' +
                ", id_alumno=" + id_alumno +
                '}';
    }
}
