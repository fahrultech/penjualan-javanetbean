package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class mstr_customer {
    Connection _Cnn;
    DBO getCn = new DBO();
    
    public String cust_id;
    public String nama;
    public String alamat;
    public String telepon;
    public String _Akses = "";
    
    public void Search(String cust_id){
        try{
            _Akses = "";
            _Cnn = getCn.getConnection();
            String SQL = "select * from mstr_customer where cust_id='"+ cust_id +"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(SQL);
            
            while(res.next()){
                _Akses = "-";
                this.cust_id = res.getString(1);
                this.nama = res.getString(2);
                this.alamat = res.getString(3);
                this.telepon = res.getString(4);
            }
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void saveData(){
        try{
            String sql = "";
            _Cnn = getCn.getConnection();
            sql ="INSERT INTO mstr_customer VALUES(?,?,?,?)";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.cust_id);
            stat.setString(2, this.nama);
            stat.setString(3, this.alamat);
            stat.setString(4, this.telepon);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : "+ex );
        }
    }
    
    public void UpdateData(){
        try{
            String sql = "";
            _Cnn = getCn.getConnection();
            sql = "UPDATE mstr_customer SET cust_id=?, nama=?,alamat=?,telepon=? "
                    + "WHERE cust_id='"+cust_id+"'";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.cust_id);
            stat.setString(2, this.nama);
            stat.setString(3, this.alamat);
            stat.setString(4, this.telepon);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR :" + ex);
        }
    }
    
    public void SaveOrUpdate(){
        try{
            String sql="";
            _Cnn = getCn.getConnection();
            if(_Akses.equals("")){
                sql = "INSERT INTO mstr_customer VALUES(?,?,?,?)";
            }else{
                sql = "UPDATE mstr_customer SET cust_id=?, nama=?, alamat=?, telepon=? "
                        + "WHERE cust_id ='"+cust_id+"'";
            }
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.cust_id);
            stat.setString(2, this.nama);
            stat.setString(3, this.alamat);
            stat.setString(4, this.telepon);
            stat.executeUpdate();
            stat.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
    
    public void Delete(String cust_id){
        try{
            _Cnn = getCn.getConnection();
            String sql = "DELETE FROM mstr_customer WHERE cust_id='"+cust_id+"'";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"ERROR : " + ex);
        }
    }
}
