package org.compiere.process;

import java.awt.Color;
//Impresión en Java2 mediante PrinterJob
	import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.compiere.process.SvrProcess;

public class TestImpress extends SvrProcess {

	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {

//		try {

			// Esto saldría en la consola de java o en la consola de sistema
			// operatico
//			System.out.println("Probando, probando la impresión.");

			// enviar a imprimir en pantalla.
			// FileDescriptor fd = FileDescriptor.out;
			// FileOutputStream os = new FileOutputStream(fd);

			// enviar a imprimir a la impresora en red, debemos conocer el
			// nombre de la
			// impresora.
			// Desde windows se puede poner ...new
			// FileOutputStream("//Au-4022/hp1100");

			// Formato UNIX
			// FileOutputStream os = new
			// FileOutputStream("\\\\Au-4022\\hp1100");

			// Si tuviesemos la impresora en el puerto paralelo
			// FileOutputStream os = new FileOutputStream("LPT1:");
			// Si tuviesemos la impresora en el puerto serie
			// FileOutputStream os = new FileOutputStream("COM1:");

			// Enviar a un archivo de texto.
//			FileOutputStream os = new FileOutputStream();
			
//			PrintStream ps = new PrintStream(os);
//			ps.println("prueba de impresión realizadaasdf");
//			ps.close();
			
			//Cogemos el servicio de impresión por defecto (impresora por defecto)
//			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//			//Le decimos el tipo de datos que vamos a enviar a la impresora
//			//Tipo: bytes Subtipo: autodetectado
//			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//			//Creamos un trabajo de impresión
//			DocPrintJob pj = service.createPrintJob();
//			//Nuestro trabajo de impresión envía una cadena de texto
//			String ss=new String("Aquí lo que vamos a imprimir.");
//			byte[] bytes;
//			//Transformamos el texto a bytes que es lo que soporta la impresora
//			bytes=ss.getBytes();
//			//Creamos un documento (Como si fuese una hoja de Word para imprimir)
//			Doc doc=new SimpleDoc(bytes,flavor,null);
//
//			//Obligado coger la excepción PrintException
//			try {
//			  //Mandamos a impremir el documento
//			  pj.print(doc, null);
//			}
//			catch (PrintException e) {
//			  System.out.println("Error al imprimir: "+e.getMessage());
//			}
			
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			//Indicamos que lo que vamos a imprimir es un objeto imprimible
			DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
			DocPrintJob pj = service.createPrintJob();
			//Creamos el documento a imprimir que contendrá el objeto
			Doc doc=new SimpleDoc(new ObjetoAImprimir(),flavor,null);

			try {
			  pj.print(doc, null);
			}
			catch (PrintException e) {
			  System.out.println("Error al imprimir: "+e.getMessage());
			}

	        FileInputStream inputStream = null;
	        try {
	            inputStream = new FileInputStream("test.txt");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        if (inputStream == null) {
	            return null;
	        }
	 
	        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
	        Doc document = new SimpleDoc(inputStream, docFormat, null);
	 
	        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
	 
	        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	 
	 
	        if (defaultPrintService != null) {
	            DocPrintJob printJob = defaultPrintService.createPrintJob();
	            try {
	                printJob.print(document, attributeSet);
	 
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.err.println("No existen impresoras instaladas");
	        }
	 
	        inputStream.close();
	    
			
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//		}
//			
//		new SystemDemo().displayHelloWorld();
		
//		try {
//			String cmd = "konqueror"; //Comando de apagado en linux
//			Runtime.getRuntime().exec(cmd); 
//		} catch (IOException ioe) {
//			System.out.println (ioe);
//		}
//			
//			//exec("shutdown -s -t 3600");
//			//exec("shutdown -r -t 3600");
//			//exec("shutdown -a");
//			exec("konqueror http://programa.nii.com");
//			//exec("regedit");
			
		return null;
	}

	public static void exec(String cmd) {
		try {
		Runtime.getRuntime().exec(cmd);
		}
		catch (IOException e) {
		System.out.println("Failed");
		}
		}
		
	

	//La clase debe de implementar la impresión implements Printable
	class ObjetoAImprimir implements Printable
	{
	   public int print (Graphics g, PageFormat f, int pageIndex)
	   {
		   g.drawString("HOLA ESTO ES UNA GRAN IMPRESION ", 100, 120);
//	      //Creamos un objeto 2D para dibujar en el
//	      Graphics2D g2 = (Graphics2D) g;
//	      //Este código imprime 2 páginas una con un cuadrado o marco
//	      //y una segunda con un circulo en la esquina superior izquierda
//
//	      //Creamos el rectángulo
//	      //getImagebleX() coge la parte de la hoja donde podemos 
//	      //imprimir quitando los bordes. Si no hiciesemos 
//	      //esto así y tuviesemos bordes definidos en la impresión 
//	      //lo que dibujasemos fuera de los bordes no lo 
//	      //imprimiría aunque cupiese en la hoja físicamente.
//	      Rectangle2D rect = new Rectangle2D.Double(f.getImageableX(),
//	                                                f.getImageableY(),
//	                                                f.getImageableWidth(),
//	                                                f.getImageableHeight());
//
//	      //Creamos la circunferencia
//	      Ellipse2D circle = new Ellipse2D.Double(100,100,100,100);
//
//	      //pageIndex indica el número de la página que se imprime
//	      //cuando es 0 primera página a imprimir, es un rectángulo
//	      //cuando es 1 segunda página a imprimir, es una circunferencia
//	      //En otro caso se devulve que no hay más páginas a imprimir
	      switch (pageIndex)
	      {
	         case 0 : //Página 1: Dibujamos sobre g y luego lo pasamos a g2
	                  g.setColor(Color.black);
	                  g.fillRect(110,120,30,5);
	                  g.setColor(Color.pink);
	                  g.drawLine(0,0,200,200);
//	                  g2 = (Graphics2D) g;
	                  return PAGE_EXISTS; //La página 1 existe y se imprimirá
	         case 1 : //Página 2: Circunferencia y rectángulo
//	                  g2.setColor(Color.red);
//	                  g2.draw(circle);
//	                  g2.draw(rect);
	                  return PAGE_EXISTS;  //La página 2 existe y se imprimirá
	         default: return NO_SUCH_PAGE;        //No se imprimirán más páginas
	      }
	   }
	}

	//clase pública que se ejecuta donde debe de estar el main que llama a la
	//otra clase.
//	public class Imprime
//	{
//	   public static void main (String[] args)
//	   {
//	      // Creamos un objeto de impresión.
//	      PrinterJob job = PrinterJob.getPrinterJob();
//
//	      // Hacemos imprimible el objeto ObjetoAImprimir
//	      job.setPrintable(new ObjetoAImprimir());
//	      //Pondrá algo tipo Información job: sun.awt.windows.WPrinterJob@4a5ab2
//	      System.out.println("Información job: " + job.toString());
//
//	      //Abre el cuadro de diálogo de la impresora, si queremos que imprima
//	      //directamente sin cuadro de diálogo quitamos el if...
//	      if (job.printDialog())
//	      {
//	        //Imprime, llama a la función print del objeto a imprimir
//	        //en nuestro caso el Objeto ObjetoAImprimir
//	         try { job.print(); }
//	         catch (PrinterException e) { System.out.println("Error de impresión: " + e); }
//	      }
//	   }
//	}
	
}
