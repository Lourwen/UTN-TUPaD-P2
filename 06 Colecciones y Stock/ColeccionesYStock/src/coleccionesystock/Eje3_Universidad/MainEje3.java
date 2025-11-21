
package coleccionesystock.Eje3_Universidad;

public class MainEje3 {
    public static void main(String[] args) {
        
        // 1. Crear 3 profesores y 5 cursos
        Profesor p1 = new Profesor("P01", "Lourdes Paco", "Dibujo botanico");
        Profesor p2 = new Profesor("P02", "Yamaguchi Aoi", "Produccion Animal");
        Profesor p3 = new Profesor("P03", "Takahata Umi", "Mejoramiento genetico");

        Curso c1 = new Curso("C101", "Botanica sistematica");
        Curso c2 = new Curso("C102", "Produccion bovina");
        Curso c3 = new Curso("C103", "Mejoramiento vegetal");
        Curso c4 = new Curso("C104", "Produccion de leche");
        Curso c5 = new Curso("C105", "Taller Gramineas");
  
        // 2. Crear universidad y agregar profesores y cursos
        Universidad uni = new Universidad("Universidad de Yamanashi");

        uni.agregarProfesor(p1);
        uni.agregarProfesor(p2);
        uni.agregarProfesor(p3);

        uni.agregarCurso(c1);
        uni.agregarCurso(c2);
        uni.agregarCurso(c3);
        uni.agregarCurso(c4);
        uni.agregarCurso(c5);

        // 3. Asignar profesores a cursos
        uni.asignarProfesorACurso("C101", "P01"); // Botanica sistematica,Lourdes Paco
        uni.asignarProfesorACurso("C102", "P02"); // Produccion bovina,Yamaguchi Aoi
        uni.asignarProfesorACurso("C103", "P03"); // Mejoramiento vegetal,Takahata Umi
        uni.asignarProfesorACurso("C104", "P02"); // Produccion de leche, Yamaguchi Aoi
        uni.asignarProfesorACurso("C105", "P01"); // Taller Gramineas,Lourdes Paco

        // 4. Listar cursos y profesores con sus cursos

        System.out.println("\n....... LISTA DE CURSOS ......");
        uni.listarCursos();

        System.out.println("\n..... LISTA DE PROFESORES......");
        uni.listarProfesores();

        System.out.println("\n...... CURSOS POR PROFESOR ......");
        p1.listarCursos();
        p2.listarCursos();
        p3.listarCursos();

        // 5. Cambiar el profesor de un curso 

        System.out.println("\n--- CAMBIO DE PROFESOR: C104 pasa de P02 a P03 ---");
        uni.asignarProfesorACurso("C104", "P03");

        // verificar sincronización
        p2.listarCursos(); // ya no debe aparecer Produccion de leche
        p3.listarCursos(); // sí debe aparecer Produccion de leche

        // 6. Remover un curso y confirmar que se elimina de la lista del profesor
    
        System.out.println("\n--- ELIMINANDO CURSO C105 (Taller gramineas) ---");
        uni.eliminarCurso("C105");

        p1.listarCursos(); 

    
        // 7. Remover un profesor y dejar sus cursos con profesor = null
    
        System.out.println("\n--- ELIMINANDO PROFESOR P03 (Takahata Umi) ---");
        uni.eliminarProfesor("P03");

        // cursos de p3 deben quedar con profesor = null
        c3.mostrarInfo();
        c4.mostrarInfo();

        // 8. Reporte: cantidad de cursos por profesor

        System.out.println("\n====== REPORTE FINAL ======");
        for (Profesor p : new Profesor[]{p1, p2, p3}) {
            System.out.println(p.getNombre() + ": " + p.getCursos().size() + " cursos");
        }
    }
}
