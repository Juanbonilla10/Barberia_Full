package edu.proyectofinal.utilidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SENA
 */
public class Email {
    public static void sendModificacion(String para, String Nombres, String nombUsu, String clave) {
        final String user = "gaes10ayt@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345ayt*";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Actualizacion de datos en Banco-Pedagogico");
            java.util.Date fecha = new Date();

            message.setContent(
                    "<center><img src='https://ae01.alicdn.com/kf/HTB1Ik1GXsnrK1RjSspkq6yuvXXan/LED-letrero-de-ne-n-personalizado-para-peluquer-a-poste-de-barbero-con-logotipo-de-dise.jpg_q50.jpg' width: 235px; title='Banco Pedagogico'></center>"
                    + "<h3> Actualizacion de datos en Banco-Pedagogico. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Nombre Usuario : "
                    + nombUsu
                    + "</h4>"
                    + "<h4> Documento Usuario : "
                    + clave
                    + " </h4>"
                    + "Ultima Modificacion"
                    + fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " - "
                    + fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear(), "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendClaves(String para, String Nombres, String correoUsu, String clave) {
        final String user = "gaes10ayt@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345ayt*";
        
//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Clave de restauración");

            message.setContent(
                    "<center><img src='https://ae01.alicdn.com/kf/HTB1Ik1GXsnrK1RjSspkq6yuvXXan/LED-letrero-de-ne-n-personalizado-para-peluquer-a-poste-de-barbero-con-logotipo-de-dise.jpg_q50.jpg' width: 235px; title='Recordatorio'></center>"
                    + "<h3> Recordatorio Claves. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + correoUsu
                    + "</h4>"
                    + "<h4> Clave Usuario : "
                    + clave
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendBienvenido(String para, String Nombres, String nombUsu, String clave) {
         final String user = "gaes10ayt@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345ayt*";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Somos AYT BarberShop");

            message.setContent(
                    "<center><img src='https://www.scottbarbershop.com/themes/demo/assets/img/promos/amigo.jpg' width: 235px; title='Bienvenidos'></center>"
                    + "<h3> Holaaa!! "
                    + Nombres
                    + "</h3>"
                    + "Disfruta la promoción "
                    ,"text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void send(String para, String sujeto, String mensaje) {

        final String user = "";//cambiará en consecuencia al servidor utilizado
        final String pass = "";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(sujeto);
            message.setContent(mensaje, "text/html;");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
      public static void sendord(String para, String Descripcion,String cant, String cod,  String Usuario) {

        final String user = "gaes10ayt@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345ayt*";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(Descripcion);
            message.setContent(
                    "<center><img src='https://ae01.alicdn.com/kf/HTB1Ik1GXsnrK1RjSspkq6yuvXXan/LED-letrero-de-ne-n-personalizado-para-peluquer-a-poste-de-barbero-con-logotipo-de-dise.jpg_q50.jpg' width: 235px; title='Bienvenidos'></center>"
                    + "<h3> Buenas tardes. "
                    
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Solicitamos estos productos con sus cantidades : "
                    + Descripcion
                    + "</h4>"
                    + "<h4>Cantidad : "
                    + cant
                    + "</h4>"
                    + "<h4>Codigo de barras : "
                    + cod
                    + "</h4>"        
                    + "<h4> Lo solicita : "
                    + Usuario
                    + " </h4>", "text/html");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
     public static void senServi(String para, String Descripcion,String cant, String cod,  String Usuario) {

        final String user = "gaes10ayt@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345ayt*";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(Descripcion);
            message.setContent(
                    "<center><img src='https://ae01.alicdn.com/kf/HTB1Ik1GXsnrK1RjSspkq6yuvXXan/LED-letrero-de-ne-n-personalizado-para-peluquer-a-poste-de-barbero-con-logotipo-de-dise.jpg_q50.jpg' width: 235px; title='Bienvenidos'></center>"
                    + "<h3> Buenas tardes. "
                    
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Solicitamos estos productos con sus cantidades : "
                    + Descripcion
                    + "</h4>"
                    + "<h4>Cantidad : "
                    + cant
                    + "</h4>"
                    + "<h4>Codigo de barras : "
                    + cod
                    + "</h4>"        
                    + "<h4> Lo solicita : "
                    + Usuario
                    + " </h4>", "text/html");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
}
