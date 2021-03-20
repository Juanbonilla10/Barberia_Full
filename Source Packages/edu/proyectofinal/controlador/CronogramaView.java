/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CategoriaServicioFacadeLocal;
import edu.proyectofinal.facade.CronogramaFacadeLocal;
import edu.proyectofinal.facade.HorariosCronogramaFacade;
import edu.proyectofinal.facade.HorariosCronogramaFacadeLocal;
import edu.proyectofinal.facade.UsuariosFacadeLocal;
import edu.proyectofinal.modelo.CategoriaServicio;
import edu.proyectofinal.modelo.Cronograma;
import edu.proyectofinal.modelo.HorariosCronograma;
import edu.proyectofinal.modelo.Usuarios;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;

/**
 *
 * @author soporte
 */
@Named(value = "cronogramaView")
@ViewScoped
public class CronogramaView implements Serializable {

    private Integer idcronogram;
    private int idrol = 3;
    private String disponibles="2";

    @EJB
    CronogramaFacadeLocal cronogramaFacadeLocal;
    private Cronograma cronograma = new Cronograma();
    private ArrayList<Cronograma> listacronograma = new ArrayList<>();

    @EJB
    UsuariosFacadeLocal usuariosFacadeLocal;
    private Usuarios usuarios = new Usuarios();
    private ArrayList<Usuarios> listausuarios = new ArrayList<>();

    @EJB
    CategoriaServicioFacadeLocal categoriaServicioFacadeLocal;
    private CategoriaServicio categoriaServicio = new CategoriaServicio();
    private ArrayList<CategoriaServicio> listacategoria = new ArrayList<>();
    
    @EJB
    HorariosCronogramaFacadeLocal horariosCronogramaFacadeLocal;
    private HorariosCronograma horariosCronograma = new HorariosCronograma();
    private ArrayList<HorariosCronograma> listahoras = new ArrayList<>();

    @Inject
    UsuarioSession usuarioSession;

    private Part archivoExcel;

    /**
     * Creates a new instance of CronogramaView
     */
    public CronogramaView() {
    }

    @PostConstruct
    public void cargarDatos() {

        try {
            listahoras.addAll(horariosCronogramaFacadeLocal.listardisponibles(disponibles));
            listacronograma.addAll(cronogramaFacadeLocal.findAll());
            listacategoria.addAll(categoriaServicioFacadeLocal.findAll());
            listausuarios.addAll(usuariosFacadeLocal.lista(3)); //FUNCION RETORNA UNA LISTA DE SOLO PERSONAS DE UN ROL//
            cronograma.setUsuariosidUsuarios(new Usuarios());
            cronograma.setCategoriaservicioidCategoriaServicio(new CategoriaServicio());
        } catch (Exception e) {
            System.out.println("Error al cargar PostConstruct" + e.getMessage());
        }

    }

    public void registrarDatos() {
        
        String mensajeSw = "";
        
        try {
                                 //ACTUALIZA ESTADO DE LA CITA CUANDO SE CREA//
            horariosCronogramaFacadeLocal.actualizaEstadoCita(cronograma.getTurno());
            cronograma.setUsuariosidUsuarios(usuariosFacadeLocal.find(cronograma.getUsuariosidUsuarios().getIdUsuarios()));
            cronograma.setCategoriaservicioidCategoriaServicio(categoriaServicioFacadeLocal.find(cronograma.getCategoriaservicioidCategoriaServicio().getIdCategoriaServicio()));
            cronogramaFacadeLocal.create(cronograma);
            listacronograma.clear();
            listacronograma.addAll(cronogramaFacadeLocal.findAll());
            listahoras.clear();
            listahoras.addAll(horariosCronogramaFacadeLocal.listardisponibles(disponibles));
                              
            
            System.out.println("variable hora:" + horariosCronograma.getHora());
            
             mensajeSw = "swal('¡Cronograma creado con exito!','','success')";
            


        }catch (Exception e) {
            System.out.println("Error al registrar" + e.getMessage());
            mensajeSw = "swal(¡Error al crear cronograma!','','warning')";
        }
        
        PrimeFaces.current().executeScript(mensajeSw);

    }

    public void eliminarCrono() {
        String mensajeSw = "";
        try {
            cronogramaFacadeLocal.eliminarDatos(idcronogram);
            listacronograma.clear();
            listacronograma.addAll(cronogramaFacadeLocal.findAll());
            mensajeSw = "swal('¡Cronograma creado con exito!','','success')";
        } catch (Exception e) {
            System.out.println("Error al eliminar cronograma" + e.getMessage());
            mensajeSw = "swal('¡Error al crear cronograma!','','warning')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void retornarDatos(Cronograma objcronm) {
        this.cronograma = objcronm;
    }

    public void actualizarDatos() {
        
        String mensajeSw = "";
        
        try {
            //ACTUALIZA DATSO DE LAS CITAS
            cronograma.setUsuariosidUsuarios(usuariosFacadeLocal.find(cronograma.getUsuariosidUsuarios().getIdUsuarios()));
            cronograma.setCategoriaservicioidCategoriaServicio(categoriaServicioFacadeLocal.find(cronograma.getCategoriaservicioidCategoriaServicio().getIdCategoriaServicio()));
            cronogramaFacadeLocal.edit(cronograma);
            listacronograma.clear();
            listacronograma.addAll(cronogramaFacadeLocal.findAll());
            mensajeSw = "swal(¡Cronograma actualizado con exito!','','success')";
        } catch (Exception e) {

            System.out.println("Error al actulizar los datos" + e.getMessage());
            
            mensajeSw = "swal(¡Error al actualizar cronograma!','','warning')";
            
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    //INSERTAR ARCHIVO EXCEL//
//    public void insertarXLS(List cellDataList) {
//        try {
//            int filasContador = 0;
//            for (int i = 0; i < cellDataList.size(); i++) {
//                List cellTemp = (List) cellDataList.get(i);
//                Cronograma newP = new Cronograma();
//                for (int j = 0; j < cellTemp.size(); j++) {
//                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
//                    switch (filasContador) {
//                        case 0:
//                            newP.setSerial(hssfCell.toString());
//                            filasContador++;
//                            break;
//                        case 1:
//                            newP.setNombre(hssfCell.toString());
//                            filasContador++;
//                            break;
//                        case 2:
//                            newP.setImagenRuta(hssfCell.toString());
//                            filasContador++;
//                            break;
//                        case 3:
//                            newP.setCantidad(hssfCell.toString());
//                            filasContador++;
//                            break;
//                        case 4:
//                            newP.setValorCompra(hssfCell.getNumericCellValue());
//                            filasContador++;
//                            break;
//                        case 5:
//                            newP.setValorVenta(hssfCell.getNumericCellValue());
//                            filasContador++;
//                            break;
//                        case 6:
//                            Categoria nueva = categoriaFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
//                            newP.setFkCategoria(nueva);
//                            productoFacadeLocal.create(newP);
//                            filasContador = 0;
//                            break;
//                    }
//
//                }
//            }
//
//        } catch (Exception e) {
//        }
//    }
//    //CREAR EXCEL//  
//    public void subeExcel() throws IOException {
//        String mensajeSw = "";
//        if (archivoExcel != null) {
//            if (archivoExcel.getSize() < 4000000) {
//                if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(archivoExcel.getContentType())) {
//                    InputStream input = archivoExcel.getInputStream();
//                    List cellData = new ArrayList();
//                    try {
//                        XSSFWorkbook workBook = new XSSFWorkbook(input);
//                        XSSFSheet hssfSheet = workBook.getSheetAt(0);
//                        Iterator rowIterator = hssfSheet.rowIterator();
//                        rowIterator.next();
//
//                        while (rowIterator.hasNext()) {
//                            XSSFRow hssfRow = (XSSFRow) rowIterator.next();
//                            Iterator iterator = hssfRow.cellIterator();
//                            List cellTemp = new ArrayList();
//                            while (iterator.hasNext()) {
//                                XSSFCell hssfCell = (XSSFCell) iterator.next();
//                                cellTemp.add(hssfCell);
//                            }
//                            cellData.add(cellTemp);
//                        }
//                        insertarXLS(cellData);
//                    } catch (Exception e) {
//                        PrimeFaces.current().executeScript("swal('Problemas ingresando el archivo' , 'error');");
//                    }
//                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//                    context.redirect("index.xhtml");
//                } else {
//                    mensajeSw = "swal('El archivo' , ' no es una XLSX ', 'error')";
//                }
//            } else {
//                mensajeSw = "swal('El archivo' , ' es muy grande  ', 'error')";
//            }
//        } else {
//            mensajeSw = "swal('No puede cargar ' , ' EL  archivo  ', 'error')";
//        }
//
//        PrimeFaces.current().executeScript(mensajeSw);
//    }
    public void reporteCronograma() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("Elaborado", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("Imagen", context.getRealPath("/images/AYT.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/Cronograma.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Cronograma.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("edu.webapp1966781a.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }

    public ArrayList<Cronograma> getListacronograma() {
        return listacronograma;
    }

    public void setListacronograma(ArrayList<Cronograma> listacronograma) {
        this.listacronograma = listacronograma;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuarios> getListausuarios() {
        return listausuarios;
    }

    public void setListausuarios(ArrayList<Usuarios> listausuarios) {
        this.listausuarios = listausuarios;
    }

    public CategoriaServicio getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    public ArrayList<CategoriaServicio> getListacategoria() {
        return listacategoria;
    }

    public void setListacategoria(ArrayList<CategoriaServicio> listacategoria) {
        this.listacategoria = listacategoria;
    }

    public Integer getIdcronogram() {
        return idcronogram;
    }

    public void setIdcronogram(Integer idcronogram) {
        this.idcronogram = idcronogram;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public HorariosCronograma getHorariosCronograma() {
        return horariosCronograma;
    }

    public void setHorariosCronograma(HorariosCronograma horariosCronograma) {
        this.horariosCronograma = horariosCronograma;
    }

    public ArrayList<HorariosCronograma> getListahoras() {
        return listahoras;
    }

    public void setListahoras(ArrayList<HorariosCronograma> listahoras) {
        this.listahoras = listahoras;
    }

    public String getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(String disponibles) {
        this.disponibles = disponibles;
    }

}
