
package relacionesuml;


public class CitaMedica {
    private String fecha;
    private String hora;
    //Asociacion unidireccional
    private Paciente paciente;
    private Profesional profesional;    

    public CitaMedica(String fecha, String hora, Paciente paciente, Profesional profesional) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.profesional = profesional;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }


    
}
