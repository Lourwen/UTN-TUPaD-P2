
package empleados;

    public class Empleado{
    //Atributos
    private int id;
    private String nombre;
    private String puesto;
    private double salario;
    
    private static int totalEmpleados=0;
    
    // CONSTRUCTOR PARA TOSOS LOS PARAMETROS
    public Empleado(int id,String nombre, String puesto,double salario){
        this.id = id;
        this.nombre=nombre;
        this.puesto =puesto;
        this.salario= salario;
        totalEmpleados++;
    }
    //CONSTRUCTOR CON NOMBRE Y PUESTO (ID AUTOMATIC,SALARIO POR DEFECTO)
    public Empleado(String nombre, String puesto){
    this.id= ++totalEmpleados;//automatizacion de id
    this.nombre=nombre;
    this.puesto=puesto;
    this.salario= 100000; //salario por defecto
           
     }
    
   //geters y serters 
    public int getId(){
        return id;}
    public String getNombre(){
        return nombre;} 
    public String getPuesto(){
        return puesto;}
    public double getSalario(){
        return salario;}
    
    public void setNombre(String nombre){
        this.nombre=nombre; }
    public void setPuesto(String puesto){
        this.puesto=puesto;}
    public void setSalario(double salario){ 
        this.salario=salario;}
    
  //sobrecarga de metodos actualizarSalario
    
    public void actualizarSalario(double porcentaje){
    this.salario +=this.salario*(porcentaje/100);
    }
    public void actualizarSalario(int aumentoCantFija){
    this.salario =salario + aumentoCantFija;
    }
    
    //Metodo estatico
    public static int mostrarTotalEmpleados(){
        return totalEmpleados;
    }
    //Descripcion con toString
    @Override
    public String toString(){
    return "Empleado (ID="+id+
            ",Nombre="+nombre+
            ",Puesto="+puesto+
            ",salario="+salario+")";
       
    }
    
}
    
    
    
    
    
    

