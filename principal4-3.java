/**************************************************************

Autor: Diego Elizondo Benet
Fecha:4 Octubre 2019
Objetivo:Crear un sistema con dos programas, y los programas con varios metodos
***************************************************************/
import java.io.*;
import java.util.*;
import java.text.*;
//inicio del programa
class Principal{
//se crea la clase main
public static void main(String arg[]){
   System.out.println("Ingresar el nombre del sistema"); //ingresar los datos del sistema
   String ns = Lectura.readString();
   System.out.println("Ingresar Dia de entrega");
   int dia = Lectura.readInt();
   System.out.println("Ingresar Mes de entrega");
   int mes = Lectura.readInt();
   System.out.println("Ingresar Anio de entrega");
   int anio = Lectura.readInt();
   System.out.println("nombre del Lider");
   String nom = Lectura.readString();
   System.out.println("apellido paterno del Lider");
   String ap = Lectura.readString();
   System.out.println("apellido materno del Lider");
   String am = Lectura.readString();
   
   System.out.println("Nombre del programa 1"); //ingresa datos del programa 1
   String nomProg = Lectura.readString();
   System.out.println("Numero de metodos del programa 1"); 
   int numM=Lectura.readInt();
   
   Metodo[] metodo= new Metodo[numM]; //arreglo para calcular numero de metodos que se elijio.
      for(int i=0;i<=(numM-1);i++){ //loop para ingresar datos de los metodos del programa 1
      System.out.println("Nombre del metodo "+(i+1));
      String nombreM=Lectura.readString();
      System.out.println("Tipo de retorno");
      String tipoRet=Lectura.readString();
      metodo[i] = new Metodo(nombreM, tipoRet); //crea objetos metodo en su arreglo.
   }
   

   System.out.println("El programa esta terminado? (s/n)"); //Ingresa si el programa fue terminado o no.
   char estatus =Lectura.readChar();
   System.out.println("Nombre del programa 2"); //ingresa datos del programa 2
   String nomProg2 = Lectura.readString();
   System.out.println("Numero de metodos del programa 2");
   int numM2=Lectura.readInt();
   
   Metodo[] metodo2= new Metodo[numM2];//arreglo para calcular numero de metodos que se elijio.
   for(int i=0;i<=(numM2-1);i++){//loop para ingresar datos de los metodos del programa 2
      System.out.println("Nombre del metodo "+(i+1));
      String nombreM=Lectura.readString();
      System.out.println("Tipo de retorno");
      String tipoRet=Lectura.readString();
      metodo2[i] = new Metodo(nombreM, tipoRet); //crea objetos metodos en su arreglo
   }
   System.out.println("El programa esta terminado? (s/n)");
   char estatus2 =Lectura.readChar();
   
   //crea objeto sistema (creando los objetos fecha, nombre y programa dentro de el)
Sistema s1 = new Sistema(ns,new Fecha(dia, mes, anio), new Nombre(nom, ap, am), new Programa(nomProg, metodo, estatus), new Programa(nomProg2, metodo2, estatus2));
System.out.println(s1);
}
}
//inicia clase Sistema
class Sistema{
   private String nombre;
   private Fecha fecha;
   private Nombre lider;
   private Programa programa1;
   private Programa programa2;
   
//metodo constructor de sistema
public Sistema(String nombre,Fecha fecha, Nombre lider, Programa programa1, Programa programa2){
   setNombre(nombre);
   setFecha(fecha);
   setLider(lider);
   setPrograma1(programa1);
   setPrograma2(programa2);
}

//metodos Sets y Gets..
public void setNombre(String nombre){
   this.nombre=nombre;
}
public String getNombre(){
   return nombre;
}
public void setFecha(Fecha fecha){
   this.fecha=fecha;
}
public Fecha getFecha(){
   return fecha;
}
public void setLider(Nombre lider){
   this.lider=lider;
}
public Nombre getLider(){
   return lider;
}
public void setPrograma1(Programa programa1){
   this.programa1=programa1;
}
public Programa getPrograma1(){
   return programa1;
}
public void setPrograma2(Programa programa2){
   this.programa2=programa2;
}
public Programa getPrograma2(){
   return programa2;
}
//metodo para validar tiempo y la terminacion de los programas.
public String validarTiempo(){
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
   Date fechaI = new GregorianCalendar(fecha.getAnio(), (fecha.getMes())-1, fecha.getDia()).getTime();
   
   String val="";
   if(date.before(fechaI) && programa1.getEstatus()=='s' && programa2.getEstatus()=='s') //condicion para checar si el sistema se entrega a tiempo o no
      val="\n\n*********************Sistema con entrega a tiempo*************************";
   else
      val="\n\n*********************Sistema no entregado a tiempo************************";
      
   return val;
}
//metodo para checar si el estado del sistema fue terminado o no.
public String[] checarEstado(){
   String[] estado = new String[2]; //arreglo para guardar los estados en el
   if(programa1.getEstatus()=='s')
      estado[0]="terminado";
      else
         estado[0]="no terminado";
   if(programa2.getEstatus()=='s')
      estado[1]="terminado";
      else
         estado[1]="no terminado";
   return estado;
}

//metodo toString para desplegar informacion del objeto.
public String toString(){

   return "Sistema:  "+nombre+"\n\tFecha de entrega: "+getFecha()+"\n\tLider del proyecto: "+getLider()+
         "\n\t\t\tPrograma: "+programa1.getNombre()+"\n\t\t\t\tEstatus del programa: "+checarEstado()[0]+
         "\n\t\t\t\t\t\tMetodos del Programa \n\t\t\t\t\t\t\t"+programa1+
         "\n\t\t\tPrograma: "+programa2.getNombre()+"\n\t\t\t\tEstatus del programa: "+checarEstado()[1]+
         "\n\t\t\t\t\t\tMetodos del Programa \n\t\t\t\t\t\t\t"+programa2+validarTiempo();
}
}
//inicia clase Programa
class Programa{
   private String nombre;
   private Metodo[] metodo;
   private char estatus;

//se crea metodo constructor con sus respectivas variables
public Programa(String nombre, Metodo[] metodo, char estatus){
   setNombre(nombre);
   setMetodo(metodo);
   setEstatus(estatus);
}
//metodos sets y gets
public void setNombre(String nombre){
   this.nombre=nombre;
}
public String getNombre(){
   return nombre;
}
public void setMetodo(Metodo[] metodo){
   this.metodo=metodo;
}
public Metodo[] getMetodo(){
   return metodo;
}
public void setEstatus(char estatus){
   this.estatus=estatus;
}
public char getEstatus(){
   return estatus;
}
//metodo toString para desplegar los metodos dentro de cada objeto programa
public String toString(){
   int x = metodo.length;
   String m="";
   for(int i=0;i<=(x-1);i++){ //loop para guardar en String 'm' todos los metodos del objeto
   if((x-1)==i)
      m+= metodo[i].getNombre()+"():"+metodo[i].getRetorno();
   else
      m+= metodo[i].getNombre()+"():"+metodo[i].getRetorno()+"\n\t\t\t\t\t\t\t";
   }
   return m;
}
}
//inicia clase metodo
class Metodo{
private String nombre;
private String retorno;

//metodo constructor de la clase Metodo
public Metodo(String nombre, String retorno){
   setNombre(nombre);
   setRetorno(retorno);
}
//metodos Sets y Gets
public void setNombre(String nombre){
   this.nombre=nombre;
}
public String getNombre(){
   return nombre;
}
public void setRetorno(String retorno){
   this.retorno=retorno;
}
public String getRetorno(){
   return retorno;
}
}
//inicia clase nombre
class Nombre{
   private String nom;
   private String ap;
   private String am;
   
//metodo constructor de clase Nombre
public Nombre(String nom, String ap, String am){
   setNom(nom);
   setAp(ap);
   setAm(am);
}
//metodos Sets y Gets
public void setNom(String nom){
   this.nom=nom;
}
public void setAp(String ap){
   this.ap=ap;
}
public void setAm(String am){
   this.am=am;
}
public String getNom(){
   return nom;
}
public String getAp(){
   return ap;
}
public String getAm(){
   return am;
}
//metodo toString que despliega nombre completo.
public String toString(){
   return nom+" "+ap+" "+am;
}
}
//inicia clase Fecha
class Fecha{
   private int dia;
   private int mes;
   private int anio;

//metodo constructor de clase Fecha
public Fecha(int dia, int mes, int anio){
   setDia(dia);
   setMes(mes);
   setAnio(anio);
   }
//metodos Sets y Gets
public void setDia(int dia){
   this.dia=dia;
}
public void setMes(int mes){
   this.mes=mes;
}
public void setAnio(int anio){
   this.anio=anio;
}
public int getDia(){
   return dia;
}
public int getMes(){
   return mes;
}
public int getAnio(){
   return anio;
}
//metodo toString para desplegar la fecha completa.
public String toString(){
   return dia+"-"+mes+"-"+anio;
}
}
//inicia clase lectura
class Lectura{
//INT
public static int readInt(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   int g2=Integer.parseInt(g);
   return g2;   
}

//BYTE
public static byte readByte(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   byte g2=Byte.parseByte(g);
   return g2;   
}

//SHORT
public static short readShort(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   short g2=Short.parseShort(g);
   return g2;   
}

//LONG
public static long readLong(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   long g2=Long.parseLong(g);
   return g2;   
}

//FLOAT
public static float readFloat(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   float g2=Float.parseFloat(g);
   return g2;   
   }
   
//DOUBLE
public static double readDouble(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g="0";}
   
   double g2=Double.parseDouble(g);
   return g2;   
}

//STRING
public static String readString(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g=" ";}
  
   return g;   
}

//CHAR
public static char readChar(){
   String g;
   DataInputStream w=new DataInputStream(System.in);
   try{
      g=w.readLine();
   }
   catch(IOException e){g=" ";}
   
   char g2= g.charAt(0);
   return g2;   
}}