package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class mstr_product {
    Connection _Cnn;
    DBO getCn = new DBO();
    
    public String prd_id;
    public String nama;
    public String satuan;
    public Double harga;
    public String _Akses = "";
    
    public void Search(String prd_id){
        try{
            _Akses ="";
            _Cnn = getCn.getConnection();
            String SQL = "select * from mstr_product where prd_id='"+prd_id+"'";
            Statement stat = _Cnn.createStatement();
            ResultSet res = stat.executeQuery(SQL);
            
            while(res.next()){
                _Akses = "-";
                this.prd_id = res.getString(1);
                this.nama = res.getString(2);
                this.satuan = res.getString(3);
                this.harga = res.getDouble(4);
            }
            stat.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
    
    public void SaveData(){
        try{
           String sql="";
           _Cnn = getCn.getConnection();
           sql = "INSERT INTO mstr_produk VALUES(?,?,?,?)";
           PreparedStatement stat = _Cnn.prepareStatement(sql);
           stat.setString(1, this.prd_id);
           stat.setString(2, this.nama);
           stat.setString(3, this.satuan);
           stat.setDouble(4, this.harga );
           stat.executeUpdate();
           stat.close();
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
    public void UpdateData(){
        try{
           String sql="";
           _Cnn = getCn.getConnection();
           sql = "update mstr_product set prd_id=?, nama=?, satuan=?, harga=? "
                   + "where prd_id='"+prd_id+"'";
           PreparedStatement stat = _Cnn.prepareStatement(sql);
           stat.executeUpdate();
           stat.close();
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
    
    public void SaveOrUpdate(){
        try{
            String sql="";
            _Cnn = getCn.getConnection();
            if(_Akses.equals("")){
                sql="INSERT INTO mstr_produk VALUES(?,?,?,?)";
            }else{
                sql="UPDATE mstr_produk SET prd_id=?,nama=?,satuan=?,harga=? "
                        + "WHERE prd_id='"+prd_id+"'";
            }
            PreparedStatement stat = _Cnn.prepareStatement(sql);
            stat.setString(1, this.prd_id);
            stat.setString(2, this.nama);
            stat.setString(3, satuan);
            stat.setDouble(4, harga);
            stat.executeUpdate();
            stat.close();
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
    
    public void Delete(String prd_id){
        try{
          _Cnn = getCn.getConnection();
          String sql = "delete from mstr_produk where prd_id='"+prd_id+"'";
          PreparedStatement stat = _Cnn.prepareStatement(sql);
          stat.executeUpdate();
          stat.close();
        }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "ERROR :" +ex);
        }
    }
}
