/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DATA.ConfiguracaoDAO;
import DATA.FotoDAO;
import MODEL.Configuracao;
import MODEL.FotosPublicacao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Jonatas Teodoro
 */
@WebServlet(name = "BuscarFoto", urlPatterns = {"/BuscarFoto"})
public class BuscarFoto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            ConfiguracaoDAO confDAO = new ConfiguracaoDAO();
            Configuracao conf = confDAO.buscarConfiguracao();
            
            FotosPublicacao obj = new FotosPublicacao();
            
            FotoDAO dao = new FotoDAO();
            
            int idFoto = Integer.parseInt(request.getParameter("idAtual"));
            
            while (obj.getId() == 0) {
                
                if (idFoto == 0) {
                    dao = new FotoDAO();
                    obj = dao.buscarNaoMostradas();
                    if (obj.getId() == 0) {
                        dao = new FotoDAO();
                        obj = dao.buscarUltimaNaoPublicada();
                    }
                } else {
                    dao = new FotoDAO();
                    obj = dao.buscarProximas(idFoto);
                    idFoto = obj.getId();
                }
                
            }
            
            dao = new FotoDAO();
            dao.mudarStatusPublicada(obj.getId());
            
            String json = "{\"login\": [{\"url\": \"" + obj.getNomeFoto() + "\",\"id\": \"" + obj.getId() + "\",\"comentario\": \"" + obj.getComentario() + "\", \"nome\": \""+obj.getUsuario().getNome()+"\"}]}";
            
            String origem = conf.getLocalImagemPublicacao() + File.separator + obj.getNomeFoto();
            String destino = request.getServletContext().getRealPath("/fotos/" + obj.getNomeFoto()).replace("\\build", "");
            
            File origemFile = new File(origem);
            File destinoFile = new File(destino);
            if (!verificarExistenciaPasta(destinoFile)) {
                copia(origemFile, destinoFile);
                Thread.sleep(5000);
            }
            
            HttpServletResponse f = new HttpServletResponseWrapper(response);
            f.getWriter().print(json);
            
        } catch (Exception ex) {
            System.out.println("Erro ao buscar foto!Erro: " + ex.getMessage());
        }
    }
    
    public void copia(File fonte, File destino) throws IOException {
        InputStream in = new FileInputStream(fonte);
        OutputStream out = new FileOutputStream(destino);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
    
    public boolean verificarExistenciaPasta(File pasta) {
        if (pasta.exists()) {
            return true;
        } else {
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
