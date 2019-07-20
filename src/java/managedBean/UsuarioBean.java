/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import Dao.ClienteDao;
import Dao.MascotaDao;
import Dao.PersonalDao;
import Dao.UsuarioDao;
import entidades.Cliente;
import entidades.Personal;
import entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author MARTIN
 */
@ManagedBean(name = "usuarioBean") 
@ViewScoped
public class UsuarioBean implements Serializable{
 private Usuario usuario;
    private Personal personal;
    private Cliente cliente;
    
    private ArrayList listaCliente;
    private ArrayList listarPersonal;
    private boolean banderaSelect = false;

    private int idPersonal;
    private int idCliente;
    
    public void ListarCombos(){
        PersonalDao personaldao= new PersonalDao();
        ClienteDao clientedao= new ClienteDao();
        listarPersonal= personaldao.listarPersonal();
        listaCliente= clientedao.listarCliente();
    }
    
    public UsuarioBean() {
        this.usuario = new Usuario();
        listaCliente=new ArrayList();
        listarPersonal = new ArrayList();
        personal = new Personal();
        cliente = new Cliente();
        
        ListarCombos();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String guardarUsuario() {
       

            UsuarioDao usuarioDao = new UsuarioDao();
            cliente.setIdCliente(idCliente);
            personal.setIdPersonal(idPersonal);
            usuario.setPersonal(personal);
            usuario.setCliente(cliente);
            
          usuarioDao.guardarUsuario(usuario);
          
        return "/RegistrarUsuario";
    }

    public String actualizarUsuario() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            boolean respuesta = usuarioDao.actualizarUsuario(usuario);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/IUsuario";

    }

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();
        lista = usuarioDao.listarUsuario();
        return lista;
    }

    public String eliminar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        boolean respuesta = usuarioDao.eliminarUsuario(usuario);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/IUsuario";
    }
    
    public String limpiar() {
        return "/IUsuario";
    }
    
    public void selectBandera(){
        banderaSelect = true;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ArrayList getListarPersonal() {
        return listarPersonal;
    }

    public void setListarPersonal(ArrayList listaPersonal) {
        this.listarPersonal = listaPersonal;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
