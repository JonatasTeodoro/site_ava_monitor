/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import MODEL.FotosPublicacao;
import MODEL.Usuario;
import UTIL.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jonatas Teodoro
 */
public class FotoDAO extends Conexao {

    public FotoDAO() throws Exception {
    }

    public FotosPublicacao buscarNaoMostradas() {
        FotosPublicacao obj = new FotosPublicacao();
        try {
            String sql = "select * from fotos_publicacao ft, usuario us where ft.id_usuario = us.id_usuario and foi_publicada=true order by id_foto desc limit 5";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new FotosPublicacao();
                obj.setId(rs.getInt("id_foto"));
                obj.setComentario(rs.getString("comentario"));
                obj.setNomeFoto(rs.getString("nome_foto"));

                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNome(rs.getString("nome"));

                obj.setUsuario(usu);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao buscarNaoMostradas!Erro: " + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public FotosPublicacao buscarUltimaNaoPublicada() {
        FotosPublicacao obj = new FotosPublicacao();
        try {
            String sql = "select * from fotos_publicacao ft, usuario us where ft.id_usuario = us.id_usuario and ft.foi_publicada=false order by id_foto limit 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new FotosPublicacao();
                obj.setId(rs.getInt("id_foto"));
                obj.setComentario(rs.getString("comentario"));
                obj.setNomeFoto(rs.getString("nome_foto"));

                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNome(rs.getString("nome"));

                obj.setUsuario(usu);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao buscarNaoMostradas!Erro: " + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public FotosPublicacao buscarProximas(int id) {
        FotosPublicacao obj = new FotosPublicacao();
        try {
            String sql = "select * from fotos_publicacao ft, usuario us where ft.id_usuario = us.id_usuario and ft.id_foto>? order by id_foto limit 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new FotosPublicacao();
                obj.setId(rs.getInt("id_foto"));
                obj.setComentario(rs.getString("comentario"));
                obj.setNomeFoto(rs.getString("nome_foto"));

                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNome(rs.getString("nome"));

                obj.setUsuario(usu);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao buscarProximas!Erro: " + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean mudarStatusPublicada(int id) {
        boolean retorno = false;
        try {
            String sql = "update fotos_publicacao set foi_publicada=? where id_foto=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, id);

            retorno = ps.executeUpdate() > 0;

        } catch (Exception ex) {
            System.out.println("Erro ao mudarStatusPublicada!Erro: " + ex.getMessage());
        } finally {
            fechaConexao();
            return retorno;
        }
    }

}
