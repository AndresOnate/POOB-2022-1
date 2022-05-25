package domain;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ActivityTest{
   
    private CompoundActivity plan;
    

    @Before
    public void setUp() throws ActivityException{
        plan=new CompoundActivity("PIS", "Plan Ingenieria de Sistemas");
        CompoundActivity is=new CompoundActivity("AIS","Ingenieria de Software");
        CompoundActivity isb=new CompoundActivity("SAISB","Ingenieria de Software Basico");
        isb.addActivity(new SimpleActivity("MBDA","Modelos y Bases de Datos",4));
        isb.addActivity(new SimpleActivity("POOB","Programacion Orientada a Objetos",4));
        CompoundActivity isi=new CompoundActivity("SAISI","Ingenieria de Software Intermedio");
        isi.addActivity(new SimpleActivity("CVDS","Ciclos de Vida de Desarrollo del Software",4));
        isi.addActivity(new SimpleActivity("ARSW","Arquitecturas de Software",4));
        isi.addActivity(new SimpleActivity("IETI","Innovacion y Emprendimiento con TIC",4));
        SimpleActivity isf=new SimpleActivity("TDI","Trabajo Dirigido",3);
        plan.addActivity(isb);
        plan.addActivity(isi);
        plan.addActivity(isf);
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void shouldCalculateTheCreditsOfAWellDefinedActivity(){
        try {
           assertEquals(23,plan.credits());
        } catch (ActivityException e){
            fail("Exception : "+e.getMessage());
        }    
    }    
       
    
    @Test
    public void shouldNotCalculateTheCreditsWhenACompoundActivityIsNotWellDefined(){
        plan.addActivity(new CompoundActivity("AET","Electivas tecnicas"));
        try { 
           assertEquals(23,plan.credits());
           fail("Threw an exception");
        } catch (ActivityException e) {
            assertEquals(ActivityException.EMPTY_COMPOUND,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldNotCalculateTheCreditsWhenASimpleActivityIsNotWellDefined() throws ActivityException{
        plan.addActivity(new SimpleActivity("PEM","Practica Empresarial",null));
        try { 
           assertEquals(23,plan.credits());
           fail("Threw an exception");
        } catch (ActivityException e) {
            assertEquals(ActivityException.SIMPLE_WITHOUT_CREDITS,e.getMessage());
        }    
    }     
    
   @Test
   public void shouldCalculateTheCreditsWhenASimpleActivityIsWellDefined() throws ActivityException{
        plan.addActivity(new SimpleActivity("PEM","Practica Empresarial",null));
        assertEquals(23,plan.definedCredits());
   }
   
   @Test
   public void shouldCalculateTheCreditsOfAWellDefinedActivityWhitDefined(){
       assertEquals(23,plan.definedCredits());  
   }    
    
    @Test
   public void shouldAddSimpleActivityFromCurriculum() throws ActivityException{
       Curriculum cursos = new Curriculum();
       cursos.addSimple("MBDA","Modelos y Bases de Datos", "4", "Curso de bases");
       cursos.addSimple("CVDS","Ciclos de Vida de Desarrollo del Software","4","Curso de ciclos");
       cursos.addSimple("IETI","Innovacion y Emprendimiento con TIC","4", "Curso de Innovacion");
       SimpleActivity act1 = new SimpleActivity("MBDA","Modelos y Bases de Datos","Curso de bases", 4);
       SimpleActivity act2 = new SimpleActivity("CVDS","Ciclos de Vida de Desarrollo del Software","Curso de ciclos", 4);
       SimpleActivity act3 = new SimpleActivity("IETI","Innovacion y Emprendimiento con TIC","Curso de Innovacion", 4);
       assertEquals( act1.results(),cursos.getActivity("MBDA").results());
       assertEquals( act2.results(),cursos.getActivity("CVDS").results());
       assertEquals( act3.results(),cursos.getActivity("IETI").results());
   }   
   
   @Test
   public void shouldAddSimpleActivityFromCurriculumWhitSearch() throws ActivityException{
       Curriculum cursos = new Curriculum();
       cursos.addSimple("MBDA","Modelos y Bases de Datos", "4", "Curso de bases");
       SimpleActivity act1 = new SimpleActivity("MBDA","Modelos y Bases de Datos","Curso de bases", 4);
       assertEquals( act1.results(),cursos.getActivity("MBDA").results());
   }
   
    @Test
   public void shouldSeachActivities() throws  ActivityException {
       try{
           Curriculum cursos = new Curriculum();
           cursos.addSimple("MBDA","Modelos y Bases de Datos", "4", "Curso de bases");
           assertEquals("Modelos y Bases de Datos", cursos.search("Modelos y Bases de Datos").get(0).getName());
           cursos.search("Modelos y Bases de Datos");
        } catch (Exception e) {
            fail ("Ocurrio una excepción");
        }
   }
   
    @Test
   public void NoDeberiaPermitirInsertarCursossinSigla() throws  ActivityException {
       try{
           Curriculum cursos = new Curriculum();
           cursos.addSimple("","Modelos y Bases de Datos", "4", "Curso de bases");
        } catch (Exception e) {
            assertEquals(ActivityException.MISSING_CODE, e.getMessage());
        }
   }
   
    @Test
   public void NoDeberiaAdicionarcursosConsiglasExistentes() throws  ActivityException {
       try{
           Curriculum cursos = new Curriculum();
           cursos.addSimple("POOB","Teoria de la programación", "4", "El estudiantes debe estar en la capcidad de");
        } catch (Exception e) {
            assertEquals(ActivityException.MISSING_CODE, e.getMessage());
        }
   }
   
    @Test
   public void NoDeberiaAdicionarcursosCreditosFueradeRango() throws  ActivityException {
       try{
           Curriculum cursos = new Curriculum();
           cursos.addSimple("TPRO","Teoria de la programación", "11", "El estudiantes debe estar en la capacidad de");
        } catch (Exception e) {
            assertEquals(ActivityException.RANGE_CREDITS, e.getMessage());
        }
   }
}

