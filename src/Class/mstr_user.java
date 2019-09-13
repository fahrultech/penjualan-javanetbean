package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class mstr_user {
    Connection _Cnn;
    DBO getCn = new DBO();
    public String user_id, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat,password;
    public String _Akses = "";
    
    public void Search(String user_id){
        try{
            _Cnn = null;
            _Akses = "";
            _Cnn = getCn.getConnection();
            String SQL = "select * from mstr_user where user_id='"+user_id+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(SQL);
            while(res.next()){
                _Akses = "-";
                this.user_id = res.getString(1);
                this.nama = res.getString(2);
                this.jenis_kelamin = res.getString(3);
                this.tempat_lahir = res.getString(4);
                this.tanggal_lahir = res.getString(5);
                this.alamat = res.getString(6);
                this.password = res.getString(7);
            }
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void SaveData(){
        try{
            String sql = "";
            _Cnn = getCn.getConnection();
            sql = "insert into mstr_user VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.user_id);
            stat.setString(2, this.nama);
            stat.setString(3, this.jenis_kelamin);
            stat.setString(4, this.tempat_lahir);
            stat.setString(5, this.tanggal_lahir);
            stat.setString(6, this.alamat);
            stat.setString(7, this.password);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void SaveOrUpdate(){
        try{
            _Cnn = getCn.getConnection();
            String sql = "";
            if(_Akses.equals("")){
                sql="INSERT INTO mstr_user VALUES(?,?,?,?,?,?,?)";
            }else{
                sql ="update mstr_user set user_id=?,nama=?,jenis_kelamin=?,tempat_lahir=?"
                        + "tanggal_lahir=?,alamat=?,password=? where user_id='"+user_id+"'";
            }
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.user_id);
            stat.setString(2, this.nama);
            stat.setString(3, this.jenis_kelamin);
            stat.setString(4, this.tempat_lahir);
            stat.setString(5, this.tanggal_lahir);
            stat.setString(6, this.alamat);
            stat.setString(7, this.password);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
    public void Delete(String user_id){
        try{
            _Cnn = getCn.getConnection();
            String sql = "delete from mstr_user where user_id = '"+user_id+"'";
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR : " + ex);
        }
    }
}
