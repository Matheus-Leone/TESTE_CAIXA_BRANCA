package login; // Pacote onde a classe está.

// Importações necessárias para JDBC (conexão com banco de dados).
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

// Classe pública User — representa um usuário.
public class User {

    // Método público que deveria abrir e retornar uma conexão com o banco de dados.
    public Connection conectarBD (){
        try{
            // Tenta carregar o driver JDBC do MySQL. O nome do driver está incorreto e isso causaria erro.
            Class.forName("com.mysql.Driver.Manager").newInstance();

            // String de conexão contendo endereço do banco, nome do schema, 
            // O usuário e senha diretamente no código é uma má prática
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Tenta abrir a conexão usando o DriverManager. Vai dar erro pois a variável não foi declarada dentro do método
            conn = DriverManager.getConnection(url);

        } catch (Exception e) { 
            // Bloco catch vazio, então o erro será ignorado completamente.
            
        } 
    } return  conn; }  
    // O "return conn;" está FORA do bloco do método. As chaves estão mal-posicionadas dando erro na compilação

    public String nome ="";    
    // Campo público que armazena o nome do usuário encontrado no banco.

    public boolean result = false; 
    // Campo público indicando o resultado da verificação.

    // Método que verifica se o usuário existe no banco através de login e senha.
    public boolean verificarUsuario (String login, String senha) {

        String sql = ""; 
        // Variável que armazenará a instrução SQL montada manualmente. Deveria ser privada para ter mais segurança e por se tratar de dados sensíveis

        Connection conn = conectarBD(); // Chama o método de conexão. Se houver erro, 'conn' poderá ser null.
       

        // INSTRUÇÃO SQL
        sql += "select nome from usuarios";
        // Monta a consulta inicial, mas SEM colocar espaço ao final — causará erro de sintaxe.

        sql += "where login = " + "'" + login + "'";
        // Adiciona condição WHERE com formatação errada

        sql += " and senha = "  + "'" + senha + "'";
        // Adiciona condição de senha

        try{  
            // Tentativa de execução da consulta.

            Statement st = conn.createStatement();  
            // Cria objeto Statement para enviar comandos SQL ao banco.

            Statement rs = st.executeQuery(sql); //Pode ser que o programa não funcione por conta deste código, pois o ExecuteQuery irá retornar o ResultSet, não o Statement
   
            if (rs.next()){
                // Verifica se o ResultSet trouxe algum resultado.
                // Caso exista um registro, o login é válido.

                result = true;
                // Marca que o usuário foi encontrado.

                nome = rs.getString("nome");
                // Pega o campo "nome" da tabela e armazena no atributo público.
            }

        } catch (Exception e) {}
        // Captura qualquer exceção, mas ignora completamente por estar vazia

        return result;
        // Retorna 'true' se encontrou o usuário, 'false' se não encontrou.
    }

}
