import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Projeto {

    private String nome;
    private double duracao;
    private int codigo;
    private String areaPesquisa;
    private String status;
    private GrandeAreaConhecimento conhecimento;

    public Projeto(String nome, double duracao, int codigo, String areaPesquisa) {
        this.nome = nome;
        this.duracao = duracao;
        this.codigo = codigo;
        this.areaPesquisa = areaPesquisa;
        this.status = status;
        this.conhecimento = conhecimento;
    }

    public String getNome() {
        return nome;
    }

    public double getDuracao() {
        return duracao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getAreaPesquisa() {
        return areaPesquisa;

    }

    public String getStatus() {
        if (status == null) {
            status = "Pendente";
        }
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setAreaPesquisa(String areaPesquisa) {
        this.areaPesquisa = areaPesquisa;
    }


    public String toString() {
        return "Projeto : " + nome + "\nDuracao : " + duracao + "hs  " +
                "\nCodigo : " + codigo + "\nArea de Pesquisa : " + areaPesquisa + "\nStatus: " + this.getStatus() +
                "\nGrande área ID: " + this.conhecimento.getId() + " Descrição: " + this.conhecimento.getDescricao() +
                "\nArea ID: " + this.conhecimento.getId() + " Descrição: " + this.conhecimento.getDescricao();
        //"\nGrande área: " + conhecimento.getId() + " Desc: "
    }

    public void setStatus(String status) {
//tamanho da mensagem e no maximo 144
        if (status.length() <= 144) {
            this.status = status;
        } else {
//trunca se for maior
            this.status = status.substring(0, 144);
        }
    }

    public void listar(Connection conn) {
        String sqlSelect =
                "SELECT Nome , Duracao FROM projetos WHERE codigo_interno = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setInt(1, getCodigo());
            rs = stm.executeQuery();
            if (rs.next()) {
                this.setNome(rs.getString(1));
                this.setDuracao(rs.getDouble(2));

            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    /*public void excluir(Connection conn) {
        String sqlDelete = "DELETE FROM Projetos WHERE codigo_interno = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlDelete);
            stm.setInt(1, getCodigo());

            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
*/
    public void incluir(Connection conn) {
        String sqlInsert =
                "INSERT INTO Projetos(NOME,DURACAO,AVALIADOR_CPF,PESQUISADOR_CPF,CODIGO_INTERNO,\n" +
                        "AREA_DE_PESQUISA,ORCAMENTO,Resposta_RESULTADO,AREA_DE_PESQUISA_CODIGO,Area_De_Conhecimento_ID,DATA_DE_ENTREGA,DATA_DE_RESPOSTA) VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlInsert);
            stm.setString(1, getNome());
            stm.setDouble(2, getDuracao());
            stm.setString(3, "56664253");
            stm.setString(4, "324896158");
            stm.setInt(5, getCodigo());
            stm.setString(6, getAreaPesquisa());
            stm.setDouble(7, Double.parseDouble("12"));
            stm.setString(8, getStatus());
            stm.setInt(9, Integer.parseInt("12"));
            stm.setInt(10, Integer.parseInt("3"));
            stm.setString(11, LocalDateTime.now().toString());
            stm.setString(12, LocalDateTime.now().toString());

            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
}
