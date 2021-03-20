/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.controlador;

import edu.proyectofinal.facade.CrearProductoFacadeLocal;
import edu.proyectofinal.facade.ProveedorFacadeLocal;
import edu.proyectofinal.modelo.CrearProducto;
import edu.proyectofinal.modelo.Proveedor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.PrimeFaces;

/**
 *
 * @author Brayan Algecira
 */
@Named(value = "productosView")
@ViewScoped
public class ProductosView implements Serializable {

    @EJB
    CrearProductoFacadeLocal crearProductoFacadeLocal;
    private ArrayList<CrearProducto> listaCrearProducto = new ArrayList<>();
    private CrearProducto objcrpro = new CrearProducto();

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    private ArrayList<Proveedor> listaproveedor = new ArrayList<>();
    private Proveedor objprov = new Proveedor();

    private CrearProducto objProductoNew = new CrearProducto();

    private Part archivoExcel;
    private String nombreArchivo;

    @Inject
    UsuarioSession usuarioSession;

    public ProductosView() {
    }

    @PostConstruct
    public void listprod() {
        listaCrearProducto.clear();
        listaproveedor.clear();
        listaproveedor.addAll(proveedorFacadeLocal.findAll());
        listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        
        objcrpro.setProveedorIdProveedor(new Proveedor());

    }
    
    public void actuapag(){
        
        listaCrearProducto.clear();
        listaproveedor.clear();
        //listaproveedor.addAll(proveedorFacadeLocal.findAll());
        //listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
        
        
    }
    

    public void registraProd() {
        String mensajeSw = " ";
        try {

            crearProductoFacadeLocal.create(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
            mensajeSw = "Swal.fire({\n"
                    + "  title: 'Quiere registrar el producto?',\n"
                    + "  icon: 'warning',\n"
                    + "  showCancelButton: true,\n"
                    + "  confirmButtonColor: '#3085d6',\n"
                    + "  cancelButtonColor: '#d33',\n"
                    + "  confirmButtonText: 'Si, Registrar!'\n"
                    + "}).then((result) => {\n"
                    + "  if (result.isConfirmed) {\n"
                    + "    Swal.fire(\n"
                    + "      'Registrado!',\n"
                    + "      'Producto registrado con exito.',\n"
                    + "      'success'\n"
                    + "    )\n"
                    + "  }\n"
                    + "})";
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            mensajeSw = "swal('Problemas al registrar el producto' , ' Verifique los campos  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void retornaProducto(CrearProducto objpros) {
        this.objcrpro = objpros;
    }

    public void actualizarProd() {
        String mensajeSw = " ";
        try {
            crearProductoFacadeLocal.edit(objcrpro);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
            mensajeSw = "swal('producto actualizado' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas al actualizar el producto' , ' Verifique los campos  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void eliminarprod(CrearProducto prorem) {
        String mensajeSw = " ";
        try {
            crearProductoFacadeLocal.remove(prorem);
            listaCrearProducto.remove(prorem);
            listaCrearProducto.clear();
            listaCrearProducto.addAll(crearProductoFacadeLocal.findAll());
            mensajeSw = "swal('Producto removido' , ' con exito ', 'success')";
        } catch (Exception e) {
            mensajeSw = "swal('Problemas al eliminar el producto' , ' El producto tiene ventas registradas  ', 'error')";
        }
        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                CrearProducto newP = new CrearProducto();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (filasContador) {
                        case 0:
                            newP.setReferencia(hssfCell.toString());
                            filasContador++;
                            break;
                        case 1:
                            newP.setCodigoBarras(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setDescripcion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 3:
                            newP.setCantidad(hssfCell.toString());
                            filasContador++;
                            break;
                        case 4:
                            newP.setPrecioProveedor(hssfCell.toString());
                            filasContador++;
                            break;
                        case 5:
                            newP.setPrecioPublico(hssfCell.toString());
                            filasContador++;
                            break;
                        case 6:
                            newP.setFechaRegistro(hssfCell.toString());
                            filasContador++;
                            break;
                        case 7:
                            Proveedor nueva = proveedorFacadeLocal.find((int) Math.floor(hssfCell.getNumericCellValue()));
                            newP.setProveedorIdProveedor(nueva);
                            crearProductoFacadeLocal.create(newP);
                            filasContador = 0;
                            break;

                    }

                }
            }

        } catch (Exception e) {
            System.out.println("edu.proyectofinal.controlador.ProductosView.insertarXLS()" + e.getMessage());
        }
    }

    public void subeExcel() throws IOException {
        String mensajeSw = "";
        if (archivoExcel != null) {
            if (archivoExcel.getSize() < 4000000) {
                if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(archivoExcel.getContentType())) {
                    InputStream input = archivoExcel.getInputStream();
                    List cellData = new ArrayList();
                    XSSFWorkbook workBook = new XSSFWorkbook(input);
                    XSSFSheet hssfSheet = workBook.getSheetAt(0);
                    Iterator rowIterator = hssfSheet.rowIterator();
                    rowIterator.next();
                    while (rowIterator.hasNext()) {
                        XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                        Iterator iterator = hssfRow.cellIterator();
                        List cellTemp = new ArrayList();
                        while (iterator.hasNext()) {
                            XSSFCell hssfCell = (XSSFCell) iterator.next();
                            cellTemp.add(hssfCell);
                        }
                        cellData.add(cellTemp);
                    }
                    insertarXLS(cellData);
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    context.redirect("index.xhtml");
                } else {
                    mensajeSw = "swal('El archivo' , ' no es una XLSX ', 'error')";
                }
            } else {
                mensajeSw = "swal('El archivo' , ' es muy grande  ', 'error')";
            }
        } else {
            mensajeSw = "swal('No puede cargar ' , ' EL  archivo  ', 'error')";
        }

        PrimeFaces.current().executeScript(mensajeSw);
    }

    public void reporteProductos() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("Usuario", usuarioSession.getUsuLogin().getNombres() + " " + usuarioSession.getUsuLogin().getApellidos());
            parametro.put("imgUsuario", context.getRealPath("/images/AYT.png"));
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/versionbarber", "root", "");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/classes/edu/proyectofinal/reports/listaproductos.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Lista De Productos.pdf");
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

    public ArrayList<Proveedor> getListaproveedor() {
        return listaproveedor;
    }

    public void setListaproveedor(ArrayList<Proveedor> listaproveedor) {
        this.listaproveedor = listaproveedor;
    }

    public Proveedor getObjprov() {
        return objprov;
    }

    public void setObjprov(Proveedor objprov) {
        this.objprov = objprov;
    }

    public ArrayList<CrearProducto> getListaCrearProducto() {
        return listaCrearProducto;
    }

    public void setListaCrearProducto(ArrayList<CrearProducto> listaCrearProducto) {
        this.listaCrearProducto = listaCrearProducto;
    }

    public CrearProducto getObjcrpro() {
        return objcrpro;
    }

    public void setObjcrpro(CrearProducto objcrpro) {
        this.objcrpro = objcrpro;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public CrearProducto getObjProductoNew() {
        return objProductoNew;
    }

    public void setObjProductoNew(CrearProducto objProductoNew) {
        this.objProductoNew = objProductoNew;
    }

}
