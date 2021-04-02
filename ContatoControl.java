
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ContatoControl {

    PreparedStatement pstm;
    ResultSet rs;
    
    String consultaRegistro = "select * from contatos where nome like ? order by nome";
    String gravaRegistro = "insert into contatos (nome, aniversario, telefone, celular, email, endereco, bairro, sexo)"
            + "values(?,?,?,?,?,?,?,?)";

    
    
    public List<ContatoBean> listarContatos (String nome) { 
        List<ContatoBean> contatos = new ArrayList();
        try {
            ConexaoBD con = new ConexaoBD();
            pstm = con.conecta().prepareStatement(consultaRegistro);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            ContatoBean cont;
            while (rs.next()){
                cont = new ContatoBean();
                cont.setCodigo(rs.getInt("codigo"));
                cont.setNome(rs.getString("nome"));
                cont.setAniversario(rs.getString("aniversario"));
                cont.setTelefone(rs.getString("telefone"));
                cont.setCelular(rs.getString("celular"));
                cont.setEmail(rs.getString("email"));
                cont.setEndereco(rs.getString("endereco"));
                cont.setBairro(rs.getString("bairro"));
                cont.setSexo(rs.getString("sexo"));
                contatos.add(cont);
                con.desonecta();
         } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Existe Registro!");
        }
        return contatos;

    }
    

    public void gravarRegistro(ContatoBean cont) {
        ConexaoBD con = new ConexaoBD();
        try {
            pstm = con.conecta().prepareStatement(gravaRegistro);
            pstm.setString(1, cont.getNome());
            pstm.setString(2, cont.getAniversario());
            pstm.setString(3, cont.getTelefone());
            pstm.setString(4, cont.getCelular());
            pstm.setString(5, cont.getEmail());
            pstm.setString(6, cont.getEndereco());
            pstm.setString(7, cont.getBairro());
            pstm.setString(8, cont.getSexo());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso!");
            con.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Gravar o Registro!");
        }


    }



 }
