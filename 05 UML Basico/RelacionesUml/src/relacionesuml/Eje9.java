
package relacionesuml;


public class Eje9 {
    public static void main(String[] args){
    /*9.CitaMédica - Paciente - Profesional
a.Asociación unidireccional: CitaMédica → Paciente,
b.Asociación unidirecciona: CitaMédica → Profesional
    */
    Paciente paciente =new Paciente("Lourdes Paco","Osde");
    
    Profesional profesional =new Profesional("Dr.Ivi","Cardiologia");
    
    CitaMedica citaMedica = new CitaMedica("07/10/25","9:30 am" , paciente , profesional);
    
    System.out.println("Fecha: " + citaMedica.getFecha());
    System.out.println("Hora: " + citaMedica.getHora());
    System.out.println("Paciente: " + citaMedica.getPaciente().getNombre() + " , Obra social: " + citaMedica.getPaciente().getObraSocial());
    System.out.println("Profesional: " + citaMedica.getProfesional().getNombre() + " , Especialidad: " + citaMedica.getProfesional().getEspecialidad());
    }
       
}         
            

