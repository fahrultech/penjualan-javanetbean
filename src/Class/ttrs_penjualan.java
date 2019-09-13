package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class ttrs_penjualan {
    Connection _Cnn;
    DBO getCn = new DBO();
    public String faktur;
    public String tanggal;
    public String cust_id;
    public String user_id;
    public String _Akses = "";
    
    public void Search(String faktur){
        try{
            _Akses = "";
            _Cnn = getCn.getConnection();
            String SQL = "select * from ttrs_penjualan where faktur ='"+faktur+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(SQL);
            while(res.next()){
                this.faktur = res.getString(1);
                this.tanggal = res.getString(2);
                this.cust_id = res.getString(3);
                this.user_id = res.getString(4);
            }
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
    
    public void SaveData(){
        try{
            _Cnn = getCn.getConnection();
            String sql = "insert into ttrs_penjualan values(?,?,?,?)";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.faktur);
            stat.setString(2, this.tanggal);
            stat.setString(3, this.cust_id);
            stat.setString(4, this.user_id);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
    
    public void UpdateData(){
        try{
            _Cnn = getCn.getConnection();
            String sql = "update ttrs_penjualan set faktur=?,tanggal=?,cust_id=?,user_id=?"
                    + "where faktur = '"+faktur+"'";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.faktur);
            stat.setString(2, this.tanggal);
            stat.setString(3, this.cust_id);
            stat.setString(4, this.user_id);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
    public void SaveOrUpdate(){
        try{
            _Cnn = getCn.getConnection();
            String sql = "";
            if(_Akses.equals("")){
                sql = "insert into ttrs_penjualan values(?,?,?,?)";
            }else{
                sql="update ttrs_penjualan set faktur=?,tanggal=?,cust_id=?,user_id=? "
                        + "where faktur ='"+faktur+"'";
            }
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.faktur);
            stat.setString(2, this.tanggal);
            stat.setString(3, this.cust_id);
            stat.setString(4, this.user_id);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
    public void Delete(String faktur){
        try{
            _Cnn = getCn.getConnection();
            String sql = "delete from ttrs_penjualan where faktur ='"+faktur+"'";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
}
