
import DataBase.PLO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





public class Database {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private CallableStatement cs;
    

    public Database()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/SPES","root","issb127979");
            st = (Statement) con.createStatement();
            if(con!=null)
            {
                System.out.println("Connected!!");
            }
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error found");
        }
    }
    
    ArrayList<UserInfo> users = new ArrayList<UserInfo>();
    
    public void Get_ALL_Users() throws SQLException
    {
        String sql = "SELECT * FROM users;";
        rs = st.executeQuery(sql); 
        while(rs.next())
        {
              users.add(new UserInfo(rs.getString("username"),rs.getString("password")));
        }   
        
    }
        
}
